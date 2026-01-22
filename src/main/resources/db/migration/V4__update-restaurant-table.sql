CREATE TYPE status_table AS ENUM (
    'AVAILABLE',
    'RESERVED',
    'INACTIVE'
);

ALTER TABLE restaurant_tables
    ADD COLUMN status_table status_table NOT NULL DEFAULT 'AVAILABLE';

ALTER TABLE restaurant_tables
    ADD CONSTRAINT uk_restaurant_tables_table_number
        UNIQUE (table_number);