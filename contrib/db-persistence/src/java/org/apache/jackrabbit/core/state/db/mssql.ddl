create table ${schemaObjectPrefix}NODE (NODE_ID char(36) not null, NODE_DATA varbinary not null)
create unique index ${schemaObjectPrefix}NODE_IDX on ${schemaObjectPrefix}NODE (NODE_ID)
create table ${schemaObjectPrefix}PROP (PROP_ID varchar not null, PROP_DATA varbinary not null)
create unique index ${schemaObjectPrefix}PROP_IDX on ${schemaObjectPrefix}PROP (PROP_ID)
create table ${schemaObjectPrefix}REFS (NODE_ID char(36) not null, REFS_DATA varbinary not null)
create unique index ${schemaObjectPrefix}REFS_IDX on ${schemaObjectPrefix}REFS (NODE_ID)
