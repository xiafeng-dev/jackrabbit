/*
 * Copyright 2004-2005 The Apache Software Foundation or its licensors,
 *                     as applicable.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jackrabbit.taglib.template;

import javax.jcr.Item;
import javax.servlet.jsp.PageContext;

/**
 * TemplateEngine implementations write nodes and properties with the given
 * template.
 * 
 * @author <a href="mailto:edgarpoce@gmail.com">Edgar Poce </a>
 */
public interface TemplateEngine
{
    /**
     * Template ID
     * 
     * @param id
     */
    void setTemplate(String id);

    /**
     * Write the given node
     * 
     * @param page
     *            context
     * @param item
     */
    void write(PageContext ctx, Item item);

}