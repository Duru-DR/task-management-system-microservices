CREATE TABLE tasks (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       status VARCHAR(50) NOT NULL,
                       priority VARCHAR(50) NOT NULL,
                       project_id BIGINT NOT NULL,
                       created_by BIGINT NOT NULL,
                       assigned_to BIGINT,
                       due_date TIMESTAMP,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE task_comments (
                               id BIGSERIAL PRIMARY KEY,
                               task_id BIGINT NOT NULL REFERENCES tasks(id) ON DELETE CASCADE,
                               created_by BIGINT NOT NULL,
                               content TEXT NOT NULL,
                               created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
