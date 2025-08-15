CREATE TABLE projects (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          owner_id BIGINT NOT NULL,
                          status VARCHAR(50) NOT NULL,
                          start_date TIMESTAMP,
                          end_date TIMESTAMP,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP
);

CREATE TABLE project_members (
                                 id BIGSERIAL PRIMARY KEY,
                                 project_id BIGINT NOT NULL REFERENCES projects(id) ON DELETE CASCADE,
                                 user_id BIGINT NOT NULL,
                                 role VARCHAR(50) NOT NULL,
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
