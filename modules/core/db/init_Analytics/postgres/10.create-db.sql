-- begin CLINIC_VISIT_ANALYSIS
create table CLINIC_VISIT_ANALYSIS (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    MONTH_ varchar(255),
    ANALYSIS_COUNT integer,
    --
    primary key (ID)
)^
-- end CLINIC_VISIT_ANALYSIS
