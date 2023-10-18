ALTER TABLE currency_pairs
    ADD created TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE currency_pairs
    ADD updated TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE currency_pairs
    ALTER COLUMN created SET NOT NULL;

ALTER TABLE currency_pairs
    ALTER COLUMN updated SET NOT NULL;