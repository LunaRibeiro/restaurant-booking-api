ALTER TABLE users
    ALTER COLUMN created_at DROP NOT NULL;

ALTER TABLE restaurant_tables
    ALTER COLUMN created_at DROP NOT NULL;

ALTER TABLE reservations
    ALTER COLUMN created_at DROP NOT NULL;

ALTER TABLE users
    ALTER COLUMN created_at SET DEFAULT now();

ALTER TABLE restaurant_tables
    ALTER COLUMN created_at SET DEFAULT now();

ALTER TABLE reservations
    ALTER COLUMN created_at SET DEFAULT now();




