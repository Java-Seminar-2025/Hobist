ALTER TABLE post
    ADD COLUMN id_user UUID,
    ADD CONSTRAINT fk_post_user
        FOREIGN KEY (id_user)
            REFERENCES "user"(id);

ALTER TABLE comment
    ADD COLUMN created_at TIMESTAMPTZ NOT NULL DEFAULT now();


ALTER TABLE post
    ADD COLUMN created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    ALTER COLUMN image_raw_data TYPE VARCHAR(255);

ALTER TABLE post
    RENAME COLUMN image_raw_data TO image;


ALTER TABLE message
    ALTER COLUMN raw_data TYPE VARCHAR(255);

ALTER TABLE message
    RENAME COLUMN raw_data TO file;

ALTER TABLE message
    RENAME COLUMN raw_data_name TO file_name;


ALTER TABLE "user"
    ALTER COLUMN profile_image_raw_data TYPE VARCHAR(255);

ALTER TABLE "user"
    RENAME COLUMN profile_image_raw_data  TO profile_image;
