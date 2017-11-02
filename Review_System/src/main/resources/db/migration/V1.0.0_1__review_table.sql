create table review (

    review_id varchar(255) not null,
    review_status varchar(255) not null,

    subject varchar(255) not null,
    content varchar(255) not null,
    rating integer not null,

    created_date timestamp,
    last_modified_date timestamp,

    primary key (review_id)
);