CREATE TABLE notifications (
                               id BIGSERIAL PRIMARY KEY,
                               user_id BIGINT NOT NULL,
                               type VARCHAR(50) NOT NULL,
                               message TEXT,
                               read BOOLEAN NOT NULL DEFAULT FALSE,
                               reference_id BIGINT,
                               created_at TIMESTAMP NOT NULL
);

-- Index for fast retrieval of user notifications
CREATE INDEX idx_notifications_user_id ON notifications(user_id);
CREATE INDEX idx_notifications_user_read ON notifications(user_id, read);
