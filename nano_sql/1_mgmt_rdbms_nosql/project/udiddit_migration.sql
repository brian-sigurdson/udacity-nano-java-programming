-- the following code will migrate the data from the old tables into the new tables

-------------------------------------------------------------------------------------------------------
-- populate users table
-- TRUNCATE TABLE users CASCADE;
-- get usernames from bad_comments.username
INSERT INTO users (username)
SELECT DISTINCT trim(bc.username)
FROM bad_comments bc
LEFT OUTER JOIN users u ON trim(u.username) = trim(bc.username)
WHERE trim(u.username) IS NULL;

-- get usernames from bad_posts.username
INSERT INTO users (username)
SELECT DISTINCT trim(bp.username)
FROM bad_posts bp
LEFT OUTER JOIN users u ON trim(u.username) = trim(bp.username)
WHERE trim(u.username) IS NULL;

-- get usernames from bad_posts.upvotes
INSERT INTO users (username)
SELECT DISTINCT trim(regexp_split_to_table(bp.upvotes ,','))
FROM bad_posts bp
LEFT OUTER JOIN users u ON trim(u.username) = trim(bp.username)
WHERE trim(u.username) IS NULL;

-- get usernames from bad_posts.downvotes
INSERT INTO users (username)
SELECT DISTINCT trim(regexp_split_to_table(bp.downvotes ,','))
FROM bad_posts bp
LEFT OUTER JOIN users u ON trim(u.username) = trim(bp.username)
WHERE trim(u.username) IS NULL;

-------------------------------------------------------------------------------------------------------
-- populate topics table
-- TRUNCATE TABLE topics CASCADE;
INSERT INTO topics (name)
SELECT DISTINCT trim(bp.topic)
FROM bad_posts bp
JOIN users u ON trim(bp.username) = trim(u.username);

-- to remove the DEFAULT NULL setting from the column, to ensure that future entries require a valid user id
ALTER TABLE topics ALTER COLUMN user_id DROP DEFAULT;

-------------------------------------------------------------------------------------------------------
-- populate posts table


-------------------------------------------------------------------------------------------------------
-- populate comments table


-------------------------------------------------------------------------------------------------------
-- populate votes table