-- file: udiddit_migration.sql

-- the following code will migrate the data from the old tables into the new tables

-------------------------------------------------------------------------------------------------------
-- populate users table
-- get usernames from bad_comments.username
INSERT INTO users (username)
SELECT DISTINCT left(trim(bc.username),25)
FROM bad_comments bc
LEFT OUTER JOIN users u ON left(trim(bc.username),25) = u.username
WHERE trim(u.username) IS NULL;

-- get usernames from bad_posts.username
INSERT INTO users (username)
SELECT DISTINCT left(trim(bp.username),25)
FROM bad_posts bp
LEFT OUTER JOIN users u ON left(trim(bp.username),25) = u.username
WHERE trim(u.username) IS NULL;

-- get usernames from bad_posts.upvotes
INSERT INTO users (username)
WITH split_table AS (
    SELECT DISTINCT left(trim(regexp_split_to_table(bp.upvotes ,',')),25) username
    FROM bad_posts bp
)
SELECT st.username
FROM split_table st
LEFT OUTER JOIN users u ON st.username = u.username
WHERE trim(u.username) IS NULL;

-- get usernames from bad_posts.downvotes
INSERT INTO users (username)
WITH split_table AS (
    SELECT DISTINCT left(trim(regexp_split_to_table(bp.downvotes ,',')),25) username
    FROM bad_posts bp
)
SELECT st.username
FROM split_table st
LEFT OUTER JOIN users u ON st.username = u.username
WHERE trim(u.username) IS NULL;

-------------------------------------------------------------------------------------------------------
-- populate topics table
INSERT INTO topics (name)
SELECT DISTINCT left(trim(bp.topic),30)
FROM bad_posts bp
JOIN users u ON left(trim(bp.username),25) = u.username;

-------------------------------------------------------------------------------------------------------
-- populate posts table
INSERT INTO posts (id, title, url, text_content, topic_id, user_id)
SELECT bp.id, left(trim(bp.title), 100) title, trim(bp.url) url, trim(bp.text_content) text_content, t.id topic_id, u.id user_id
FROM bad_posts bp
JOIN users u on left(trim(bp.username),25) = u.username
JOIN topics t on t.name = left(trim(bp.topic),30);

-------------------------------------------------------------------------------------------------------
-- populate comments table
INSERT INTO comments (comment_text, post_id, user_id)
SELECT left(trim(bc.text_content), 100) text_content, bc.post_id post_id, u.id user_id
FROM bad_comments bc
JOIN users u on left(trim(bc.username),25) = u.username;

-------------------------------------------------------------------------------------------------------
-- populate votes table
-- upvotes
INSERT INTO votes (vote, post_id, user_id)
WITH upvoter_names AS
         (
            SELECT bp1.id AS post_id, left(trim(regexp_split_to_table(bp1.upvotes, ',')), 25) AS username
            FROM bad_posts bp1
         )
SELECT 1 AS vote, uvn.post_id AS post_id, u.id AS user_id
FROM users u
JOIN upvoter_names uvn ON uvn.username = u.username;

-- downvotes
INSERT INTO votes (vote, post_id, user_id)
WITH downvoter_names AS
         (
            SELECT bp1.id AS post_id, left(trim(regexp_split_to_table(bp1.downvotes, ',')), 25) AS username
            FROM bad_posts bp1
         )
SELECT -1 AS vote, dvn.post_id AS post_id, u.id AS user_id
FROM users u
JOIN downvoter_names dvn ON dvn.username = u.username;