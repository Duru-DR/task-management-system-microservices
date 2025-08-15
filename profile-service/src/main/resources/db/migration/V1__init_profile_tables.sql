CREATE TABLE profiles (
                          id BIGSERIAL PRIMARY KEY,
                          user_id BIGINT NOT NULL UNIQUE,
                          username VARCHAR(50) NOT NULL,
                          full_name VARCHAR(100),
                          bio VARCHAR(500),
                          avatar_url VARCHAR(255),
                          location VARCHAR(100),
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Optional: Index for username lookups
CREATE INDEX idx_profiles_username ON profiles(username);

-- Optional: Index for user_id lookups (even though unique already creates one)
CREATE INDEX idx_profiles_user_id ON profiles(user_id);
