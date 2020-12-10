CREATE SEQUENCE hibernate_sequence START 1;

create table user_owner
(
    id         bigint not null
        constraint user_todo_pkey
            primary key,
    first_name varchar(255),
    last_name  varchar(255)
);

alter table user_owner
    owner to postgres;

create table task
(
    id            bigint not null
        constraint task_pkey
            primary key,
    archive       boolean,
    created_time  timestamp,
    done_time     timestamp,
    deadline      timestamp,
    archive_date_time     timestamp,
    description   varchar(255),
    title          varchar(255),
    status        integer,
    tracking_time timestamp,
     user_id_id   bigint  references user_owner (id)
);

alter table task
    owner to postgres;



