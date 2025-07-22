-- SQL-скрипт для создания таблиц

-- Таблица: ИМЕНА
CREATE TABLE IF NOT EXISTS first_names (id SERIAL PRIMARY KEY,
                                        name VARCHAR(100) NOT NULL);

-- Таблица: ФАМИЛИИ
CREATE TABLE IF NOT EXISTS last_names (id SERIAL PRIMARY KEY,
                                       name VARCHAR(100) NOT NULL);

-- Таблица: ОТЧЕСТВА
CREATE TABLE IF NOT EXISTS patronymics (id SERIAL PRIMARY KEY,
                                        name VARCHAR(100) NOT NULL);

-- Пример наполнения данными

-- Имена
INSERT INTO first_names (name) VALUES
                                   ('Александр'),
                                   ('Михаил'),
                                   ('Дмитрий'),
                                   ('Сергей'),
                                   ('Иван');

-- Фамилии
INSERT INTO last_names (name) VALUES
                                  ('Иванов'),
                                  ('Петров'),
                                  ('Сидоров'),
                                  ('Смирнов'),
                                  ('Кузнецов');

-- Отчества
INSERT INTO patronymics (name) VALUES
                                   ('Александрович'),
                                   ('Михайлович'),
                                   ('Сергеевич'),
                                   ('Иванович'),
                                   ('Петрович');

-- Таблица регионов
CREATE TABLE IF NOT EXISTS regions (code VARCHAR(2) PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL);

-- Таблица сгенерированных физ лиц
CREATE TABLE persons (id SERIAL PRIMARY KEY,
                      first_name VARCHAR(100),
                      middle_name VARCHAR(100),
                      last_name VARCHAR(100),
                      snils VARCHAR(20),
                      inn VARCHAR(20),
                      passport VARCHAR(20));