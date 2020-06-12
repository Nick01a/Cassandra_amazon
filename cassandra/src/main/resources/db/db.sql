create table if not exists customer
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

create table if not exists product
(
    id               varchar(255) not null
        primary key,
    product_category varchar(255) null,
    product_parent   varchar(255) null,
    product_title    varchar(255) null
);

create table if not exists review
(
    id                varchar(255) not null
        primary key,
    body              longtext     null,
    date              date         null,
    headline          varchar(255) null,
    helpful_votes     int          null,
    verified_purchase varchar(255) null,
    star_rating       int          null,
    total_votes       int          null,
    vine              varchar(255) null,
    customer_id       bigint       null,
    product_id        varchar(255) null,
    constraint FKgce54o0p6uugoc2tev4awewly
        foreign key (customer_id) references customer (id),
    constraint FKiyof1sindb9qiqr9o8npj8klt
        foreign key (product_id) references product (id)
);

