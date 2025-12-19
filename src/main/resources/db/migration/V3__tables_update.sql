ALTER TABLE post
    ADD COLUMN id_user UUID,
    ADD CONSTRAINT fk_post_user
        FOREIGN KEY (id_user)
            REFERENCES "user"(id);

ALTER TABLE comment
    ADD COLUMN created_at TIMESTAMPTZ NOT NULL DEFAULT now();

ALTER TABLE post
    ADD COLUMN created_at TIMESTAMPTZ NOT NULL DEFAULT now();