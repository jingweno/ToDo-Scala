# Tasks schema
 
# --- !Ups

CREATE SEQUENCE s_tasks_id;

CREATE TABLE tasks (
    id bigint NOT NULL DEFAULT nextval('s_tasks_id'),
    label varchar(255)
);
 
# --- !Downs
 
DROP TABLE task;

DROP SEQUENCE s_tasks_id;