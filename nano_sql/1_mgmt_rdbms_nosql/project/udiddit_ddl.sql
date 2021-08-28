-- GUIDELINE #4
-- Your new database schema will be composed of five (5) tables that should have an auto-incrementing id as their primary key.

-- users
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(25) UNIQUE CHECK ( LENGTH("username") > 0 ),
    last_login DATE
);

-- topics
CREATE TABLE topics (
    id SERIAL PRIMARY KEY,
    topic_name VARCHAR(30) UNIQUE CHECK ( LENGTH("topic_name") > 0 ),
    description VARCHAR(500),
    -- follow similar instructions for other tables, for what to do when a user is deleted.
    username VARCHAR(25) REFERENCES users (username) ON DELETE SET NULL
);

-- posts
CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) UNIQUE CHECK ( LENGTH("title") > 0 ),
    topic_id INTEGER REFERENCES topics (id) ON DELETE CASCADE,
    username VARCHAR(25) REFERENCES users (username) ON DELETE SET NULL
);

-- comments
CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    comment_text VARCHAR(100) UNIQUE CHECK ( LENGTH("comment_text") > 0 ),
    post_id INTEGER REFERENCES posts (id) ON DELETE CASCADE,
    username VARCHAR(25) REFERENCES users (username) ON DELETE SET NULL
);

-- votes
CREATE TABLE votes (
    id SERIAL PRIMARY KEY,
    -- only -1 and 1 are acceptable values
    vote SMALLINT CHECK ( vote IN (-1, 1) ),
    post_id INTEGER REFERENCES posts (id) ON DELETE CASCADE,
    username VARCHAR(25) REFERENCES users (username) ON DELETE SET NULL,
    UNIQUE (post_id, username)
);


