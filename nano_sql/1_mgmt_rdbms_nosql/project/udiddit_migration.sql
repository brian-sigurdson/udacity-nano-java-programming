-- the following code will migrate the data from the old tables into the new tables

-------------------------------------------------------------------------------------------------------
-- populate users table
TRUNCATE TABLE users CASCADE;

-- from bad_comments.username
INSERT INTO users (username)
SELECT DISTINCT trim(username)
FROM bad_comments
WHERE NOT EXISTS( SELECT username FROM users );

-- from bad_posts.username
INSERT INTO users (username)
SELECT DISTINCT trim(username)
FROM bad_posts
WHERE NOT EXISTS( SELECT username FROM users );

-- from bad_posts.upvotes
INSERT INTO users (username)
SELECT DISTINCT trim(splitted_names)
FROM bad_posts bp, regexp_split_to_table(bp.upvotes ,',') splitted_names
WHERE NOT EXISTS( SELECT username FROM users );

-- from bad_posts.downvotes
INSERT INTO users (username)
select distinct trim(splitted_names)
from bad_posts bp, regexp_split_to_table(bp.downvotes ,',') splitted_names
WHERE NOT EXISTS( SELECT username FROM users );

-------------------------------------------------------------------------------------------------------
-- populate topics table


-------------------------------------------------------------------------------------------------------
-- populate posts table


-------------------------------------------------------------------------------------------------------
-- populate comments table


-------------------------------------------------------------------------------------------------------
-- populate votes table