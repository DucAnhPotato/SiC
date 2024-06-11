CREATE TABLE public.tbl_project
(
    id                   bigserial   NOT NULL,
    starter_id           bigserial   NOT NULL,
    name                 varchar(60) NOT NULL,
    target               numeric     NOT NULL,
    address              varchar(60) NOT NULL,
    start_date           date        NOT NULL,
    end_date             date        NOT NULL,
    detail               varchar     NOT NULL,
    number_of_supporters integer     NOT NULL DEFAULT 0,
    CONSTRAINT tbl_project_pk PRIMARY KEY (id),
);

COMMENT
ON COLUMN public.tbl_project.id IS 'Table Id, PK, auto increment';
COMMENT
ON COLUMN public.tbl_project.starter_id IS 'Stater Id of the project starter';
COMMENT
ON COLUMN public.tbl_project.name IS 'Project name';
COMMENT
ON COLUMN public.tbl_project.target IS 'Project target of donation';
COMMENT
ON COLUMN public.tbl_project.address IS 'Project address';
COMMENT
ON COLUMN public.tbl_project.start_date IS 'Project start date of donation';
COMMENT
ON COLUMN public.tbl_project.end_date IS 'Project end date of donation';
COMMENT
ON COLUMN public.tbl_project.detail IS 'Project description';
COMMENT
ON COLUMN public.tbl_project.number_of_supporters IS 'Number of supporters of the Project';
