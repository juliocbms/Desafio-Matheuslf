
CREATE TYPE task_status AS ENUM (
    'TODO',
    'DOING',
    'DONE'
);


CREATE TYPE task_priority AS ENUM (
    'LOW',
    'MEDIUM',
    'HIGH'
);


CREATE TABLE tb_project (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    start_date DATE,
    end_date DATE
);


CREATE TABLE tb_task (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    status task_status NOT NULL DEFAULT 'TODO',
    priority task_priority NOT NULL DEFAULT 'LOW',
    due_date DATE,
    project_id BIGINT NOT NULL,


    CONSTRAINT fk_project
        FOREIGN KEY(project_id)
        REFERENCES tb_project(id)
        ON DELETE CASCADE
);


CREATE INDEX idx_task_project_id ON tb_task(project_id);