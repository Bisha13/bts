DROP ALL OBJECTS;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id INT AUTO_INCREMENT  PRIMARY KEY,
                       name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS projects;

CREATE TABLE projects (
                          id INT AUTO_INCREMENT  PRIMARY KEY,
                          name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS tasks;

CREATE TABLE tasks (
                       id INT AUTO_INCREMENT  PRIMARY KEY,
                       theme VARCHAR(250) NOT NULL,
                       project_id INT NOT NULL,
                       type VARCHAR(250),
                       priority VARCHAR(250),
                       executor_id INT NOT NULL,
                       description TEXT,
                       foreign key (project_id) references projects(id),
                       foreign key (executor_id) references users(id)
);