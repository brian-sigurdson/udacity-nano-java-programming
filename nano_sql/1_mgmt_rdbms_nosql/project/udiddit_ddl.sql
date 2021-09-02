-- GUIDELINE #4
-- Your new database schema will be composed of five (5) tables that should have an auto-incrementing id as their primary key.

-------------------------------------------------------------------------------------------------------
-- drop the tables, if exist
DROP TABLE IF EXISTS votes CASCADE;
DROP TABLE IF EXISTS comments CASCADE;
DROP TABLE IF EXISTS posts CASCADE;
DROP TABLE IF EXISTS topics CASCADE;
DROP TABLE IF EXISTS users CASCADE;
-------------------------------------------------------------------------------------------------------
-- users
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(25) UNIQUE CHECK ( LENGTH("username") > 0 ),
    last_login DATE DEFAULT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE INDEX users_last_login_date_idx ON users (last_login);
CREATE INDEX users_username_idx ON users (username);

-------------------------------------------------------------------------------------------------------
-- topics
CREATE TABLE topics (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) UNIQUE CHECK ( LENGTH("name") > 0 ),
    description VARCHAR(500) DEFAULT NULL,
    -- According to this comment https://knowledge.udacity.com/questions/340724 a user_id is not required.
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE INDEX topics_topic_name_idx ON topics (name);

-------------------------------------------------------------------------------------------------------
-- posts
CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) CHECK ( LENGTH("title") > 0 ),
    url VARCHAR(4000) DEFAULT NULL,
    text_content TEXT DEFAULT NULL,
    topic_id INTEGER REFERENCES topics (id) ON DELETE CASCADE,
    user_id INTEGER REFERENCES users (id) ON DELETE SET NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    -- "Posts should contain either a URL or a text content, but not both."
    CONSTRAINT posts_url_text_content_check CHECK (
        -- url and text_content cannot both be null
        NOT ( url IS NULL AND text_content IS NULL) AND
        -- url and text_content cannot both be non-null
        NOT ( LENGTH("url") > 0 AND LENGTH("text_content") > 0)
    )
);

CREATE INDEX posts_title_idx ON posts (title);
CREATE INDEX posts_user_id_idx ON posts (user_id);
CREATE INDEX posts_topic_id_idx ON posts (topic_id);
CREATE INDEX posts_url_idx ON posts (url);
CREATE INDEX posts_post_id_created_at_idx ON posts (id, created_at);
CREATE INDEX posts_user_id_created_at_idx ON posts (user_id, created_at);

-------------------------------------------------------------------------------------------------------
-- comments
CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    comment_text VARCHAR(100) CHECK ( LENGTH("comment_text") > 0 ),
    post_id INTEGER REFERENCES posts (id) ON DELETE CASCADE,
    user_id INTEGER REFERENCES users (id) ON DELETE SET NULL,
    parent_comment_id INTEGER REFERENCES comments (id) ON DELETE CASCADE DEFAULT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE INDEX comments_comment_text_idx ON comments (comment_text);
CREATE INDEX comments_parent_comment_id_idx ON comments (parent_comment_id);
CREATE INDEX comments_user_id_created_at_idx ON comments (user_id, created_at);

-------------------------------------------------------------------------------------------------------
-- votes
CREATE TABLE votes (
    id SERIAL PRIMARY KEY,
    -- only -1 and 1 are acceptable values
    vote SMALLINT CHECK ( vote IN (-1, 1) ),
    post_id INTEGER REFERENCES posts (id) ON DELETE CASCADE,
    user_id INTEGER REFERENCES users (id) ON DELETE SET NULL,
    CONSTRAINT votes_post_id_user_id_key UNIQUE (post_id, user_id),
    created_at TIMESTAMP DEFAULT NOW()
);

