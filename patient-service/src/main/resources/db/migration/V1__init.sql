CREATE TABLE patient
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    title      VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    dob        VARCHAR(255) NOT NULL,
    phone      VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    street_number     INT          NOT NULL,
    street     VARCHAR(255) NOT NULL,
    city       VARCHAR(255) NOT NULL,
    county     VARCHAR(255) NOT NULL,
    postcode   VARCHAR(255) NOT NULL,
    country    VARCHAR(255) NOT NULL,
    weight     INT          NOT NULL,
    height     DOUBLE       NOT NULL,
    ward_id    INT          NOT NULL
);

