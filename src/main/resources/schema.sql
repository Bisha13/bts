DROP TABLE IF EXISTS users;

CREATE TABLE users (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              name VARCHAR(250) NOT NULL
);

CREATE TABLE projects (
                       id INT AUTO_INCREMENT  PRIMARY KEY,
                       name VARCHAR(250) NOT NULL
);

CREATE TABLE projects (
                          id INT AUTO_INCREMENT  PRIMARY KEY,
                          theme VARCHAR(250) NOT NULL,
                          project_id INT NOT NULL,
                          type VARCHAR(250),
                          priority VARCHAR(250),
                          executor_id INT NOT NULL,
                          description TEXT
);