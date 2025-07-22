CREATE TABLE IF NOT EXISTS prefix_names (id   SERIAL PRIMARY KEY,
                                         name VARCHAR(100) NOT NULL);

CREATE TABLE IF NOT EXISTS stems_names (id   SERIAL PRIMARY KEY,
                                        name VARCHAR(100) NOT NULL);

CREATE TABLE IF NOT EXISTS suffix_names (id   SERIAL PRIMARY KEY,
                                         name VARCHAR(100) NOT NULL);

CREATE TABLE IF NOT EXISTS organization_names (id SERIAL PRIMARY KEY,
                                               name VARCHAR(255) NOT NULL UNIQUE);