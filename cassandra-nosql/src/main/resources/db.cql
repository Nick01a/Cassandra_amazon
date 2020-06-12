CREATE KEYSPACE cassandra WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1};

USE cassandra;

-- Task 1 - 2;
CREATE TABLE product_reviews  (
    review_id text,
    product_id text,
    star_rating int,
    helpful_votes int,
    total_votes int,
    vine boolean,
    verified_purchase boolean,
    review_headline text,
    review_body	text,
    review_date date,
    PRIMARY KEY ((product_id), star_rating, review_id)
);

-- Task 3;
CREATE TABLE customer_reviews  (
    customer_id int,
    review_id text,
    star_rating int,
    helpful_votes int,
    total_votes int,
    vine boolean,
    verified_purchase boolean,
    review_headline text,
    review_body	text,
    review_date date,
    PRIMARY KEY (customer_id, star_rating, verified_purchase, review_date)
);

--Task 4,5,7,8;
CREATE TABLE reviews_by_date_star  (
    review_date date,
    review_id text,
    product_parent text,
    product_category text,
    product_title text,
    product_id text,
    verified_purchase boolean,
    star_rating tinyint,
    PRIMARY KEY ((product_id), verified_purchase, review_date, star_rating)
);

--Task 6
CREATE TABLE best_products  (
    product_parent text,
    product_category text,
    product_title text,
    product_id text,
    verified_purchase boolean,
    PRIMARY KEY ((product_id, verified_purchase))
);

