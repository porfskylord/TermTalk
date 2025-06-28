CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(16) UNIQUE NOT NULL,     -- For chatting/search
    username VARCHAR(50) UNIQUE NOT NULL,    -- For login
    password_hash TEXT NOT NULL,
    is_online BOOLEAN DEFAULT FALSE,
    last_seen TIMESTAMP
);

CREATE TABLE messages (
    id SERIAL PRIMARY KEY,
    sender_id VARCHAR(16),                   -- refers to users.user_id
    receiver_id VARCHAR(16),                 -- refers to users.user_id
    content TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    delivered BOOLEAN DEFAULT FALSE
);

