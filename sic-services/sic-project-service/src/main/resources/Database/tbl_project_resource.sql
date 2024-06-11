CREATE TABLE public.tbl_project_resource
(
    id           bigserial NOT NULL,
    project_id   bigserial NOT NULL,
    resource_id  varchar   NOT NULL,
    file_url     varchar   NOT NULL,
    created_date date      NOT NULL,
    file_key     varchar   NOT NULL,
    CONSTRAINT tbl_project_resource_pk PRIMARY KEY (id),
    CONSTRAINT tbl_project_resource_fk FOREIGN KEY (project_id) REFERENCES tbl_project (id)
);

COMMENT
ON COLUMN public.tbl_project_resource.id IS 'Table Id, PK, auto increment';
COMMENT
ON COLUMN public.tbl_project_resource.project_id IS 'Project Id, Table FK, references from tbl_project';
COMMENT
ON COLUMN public.tbl_project_resource.file_url IS 'File url';
COMMENT
ON COLUMN public.tbl_project_resource.created_date IS 'Created date of file';
COMMENT
ON COLUMN public.tbl_project_resource.file_key IS 'Key of file';


