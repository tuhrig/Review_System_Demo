create table statistic (

    statistic_id varchar(255) not null,
    review_status varchar(255) not null,

    created_date timestamp,
    last_modified_date timestamp,

    primary key (statistic_id)
);