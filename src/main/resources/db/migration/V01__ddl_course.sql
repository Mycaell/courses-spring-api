CREATE SCHEMA course;

CREATE TABLE course.course
(
    id       SERIAL,
    name     VARCHAR(128) NOT NULL,
    category VARCHAR(10)  NOT NULL,
    status   VARCHAR(10)  NOT NULL,
    CONSTRAINT pk_course PRIMARY KEY (id)
);