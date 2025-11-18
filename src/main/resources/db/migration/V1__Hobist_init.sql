--importing uuid
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

--creating enum
CREATE TYPE status AS ENUM (
    'pending',
    'accepted',
    'blocked'
);

--creating tables

--because of data migration id of country and city is int
create table country(
    id int primary key ,
    name varchar(255) not null
);

create table city(
    id int primary key ,
    name varchar(255) not null,
    id_country int not null references country(id)
  );

create table "user"(
    id uuid primary key default uuid_generate_v4(),
    name varchar(255) not null,
    surname varchar(255) not null,
    e_mail varchar(255) unique not null,
    password varchar(255) not null,
    id_country int references country(id),
    id_city int references city(id),
    number_of_posts int,--incremented/deincremented via app methods
    number_of_friends int,--incremented/deincremented via app methods
    user_page_description varchar(1000),
    profile_image_raw_data bytea
);

create table hobby(
    id uuid primary key default uuid_generate_v4(),
    name varchar(255) not null
);

create table hobby_user(
    id_user uuid not null references "user"(id),
    id_hobby uuid not null references hobby(id),
    primary key (id_user,id_hobby)
);

--for this table make sure that id's when swaped are considerate same with application methods.
create table friendship(
    id uuid primary key default uuid_generate_v4(),
    id_user1 uuid not null references "user"(id),
    id_user2 uuid not null references "user"(id),
    status status not null default 'pending', --enum
    date_of_befriending date,
    CONSTRAINT one_friendship UNIQUE (id_user1, id_user2),
    CONSTRAINT no_self_friendship CHECK (id_user1 <> id_user2)
);

create table message(
    id uuid primary key default uuid_generate_v4(),
    id_friendship uuid not null references friendship(id),-- friendship could be also viewd as chat
    id_user uuid not null references "user"(id),
    message varchar(255),
    raw_data_name varchar(255),
    raw_data bytea,
    time_sent TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

create table post(
    id uuid primary key default uuid_generate_v4(),
    message varchar(500),
    image_raw_data bytea,
    like_number int default 0
);

create table comment(
    id uuid primary key default uuid_generate_v4(),
    id_post uuid not null references post(id),
    message varchar(255),
    like_number int default 0
);

create table content_like(
    id uuid primary key default uuid_generate_v4(),
    id_post uuid references post(id),
    id_comment uuid references comment(id),
    id_user uuid not null references "user"(id),
    unique (id_post,id_user,id_comment),
    CONSTRAINT only_one CHECK (
        (id_post IS NOT NULL AND id_comment IS NULL) OR
        (id_post IS NULL AND id_comment IS NOT NULL)
        )
);