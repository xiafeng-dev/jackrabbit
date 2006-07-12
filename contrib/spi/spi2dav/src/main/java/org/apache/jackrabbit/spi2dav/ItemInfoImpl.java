/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jackrabbit.spi2dav;

import org.apache.jackrabbit.webdav.MultiStatusResponse;
import org.apache.jackrabbit.webdav.DavServletResponse;
import org.apache.jackrabbit.webdav.jcr.ItemResourceConstants;
import org.apache.jackrabbit.webdav.property.DavProperty;
import org.apache.jackrabbit.webdav.property.HrefProperty;
import org.apache.jackrabbit.webdav.property.DavPropertySet;
import org.apache.jackrabbit.name.NameException;
import org.apache.jackrabbit.name.QName;
import org.apache.jackrabbit.spi.ItemInfo;
import org.apache.jackrabbit.spi.NodeId;
import org.apache.jackrabbit.spi.SessionInfo;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.jcr.RepositoryException;

/**
 * <code>ItemInfoImpl</code>...
 */
abstract class ItemInfoImpl implements ItemInfo {

    private static Logger log = LoggerFactory.getLogger(ItemInfoImpl.class);

    private final QName name;
    private final NodeId parentId;

    public ItemInfoImpl(MultiStatusResponse response, URIResolver uriResolver, SessionInfo sessionInfo) throws RepositoryException {

        DavPropertySet propSet = response.getProperties(DavServletResponse.SC_OK);
        DavProperty nameProp = propSet.get(ItemResourceConstants.JCR_NAME);
	if (nameProp != null && nameProp.getValue() != null) {
            // not root node
            // TODO: jcrName is transported from jackrabbit-webdav impl
            String jcrName = nameProp.getValue().toString();
            try {
                name = uriResolver.getQName(jcrName);
            } catch (NameException e) {
                throw new RepositoryException("Unable to build ItemInfo object, invalid name found: " + jcrName);
            }
	} else {
            // root
            name = QName.ROOT;
        }
        // set the parent id unless its the root item
        if (propSet.contains(ItemResourceConstants.JCR_PARENT)) {
            HrefProperty parentProp = new HrefProperty(propSet.get(ItemResourceConstants.JCR_PARENT));
            String parentHref = parentProp.getHrefs().get(0).toString();
            parentId = uriResolver.getNodeId(parentHref, sessionInfo);
        } else {
            parentId = null;
        }
    }

    public NodeId getParentId() {
        return parentId;
    }

    public QName getQName() {
        return name;
    }

    public abstract boolean denotesNode();
}