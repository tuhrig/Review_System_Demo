create table message (

    id varchar(255) not null,
    type varchar(255) not null,
    content varchar(2048) not null,
    listener varchar(255) not null,
    execution_count integer not null,
    success BOOLEAN not null,
    error_message varchar(1024),
    created_date timestamp,
    last_modified_date timestamp,

    primary key (id)
);