-- V2__remove_user_id_from_profiles.sql
ALTER TABLE profiles
DROP CONSTRAINT IF EXISTS profiles_user_id_key,
    DROP COLUMN IF EXISTS user_id;

DROP INDEX IF EXISTS idx_profiles_user_id;
