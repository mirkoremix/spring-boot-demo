BEGIN;

CREATE TABLE "author" (
  "id" integer PRIMARY KEY,
  "first_name" varchar,
  "last_name" varchar,
  "email" varchar UNIQUE,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "course" (
  "id" integer PRIMARY KEY,
  "title" varchar,
  "description" varchar,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "author_course" (
  "author_id" integer,
  "course_id" integer
);

CREATE TABLE "section" (
  "id" integer PRIMARY KEY,
  "course_id" integer,
  "name" varchar,
  "section_order" varchar,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "lecture" (
  "id" integer PRIMARY KEY,
  "section_id" integer,
  "resource_id" integer,
  "name" varchar,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "resource" (
  "id" integer PRIMARY KEY,
  "size" integer,
  "name" varchar,
  "url" varchar,
  "type" varchar,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE SEQUENCE IF NOT EXISTS author_seq;
ALTER TABLE author  ALTER COLUMN id SET DEFAULT nextval('author_seq');

CREATE SEQUENCE IF NOT EXISTS course_seq;
ALTER TABLE course  ALTER COLUMN id SET DEFAULT nextval('course_seq');

CREATE SEQUENCE IF NOT EXISTS section_seq;
ALTER TABLE "section" ALTER COLUMN id SET DEFAULT nextval('section_seq');

CREATE SEQUENCE IF NOT EXISTS lecture_seq;
ALTER TABLE lecture  ALTER COLUMN id SET DEFAULT nextval('lecture_seq');

CREATE SEQUENCE IF NOT EXISTS resource_seq;
ALTER TABLE resource ALTER COLUMN id SET DEFAULT nextval('resource_seq');

ALTER TABLE "lecture" ADD FOREIGN KEY ("resource_id") REFERENCES "resource" ("id");

ALTER TABLE public.author_course ADD CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES public.author(id);

ALTER TABLE public.author_course ADD CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES public.course(id);

ALTER TABLE "section" ADD FOREIGN KEY ("course_id") REFERENCES "course" ("id");

ALTER TABLE "lecture" ADD FOREIGN KEY ("section_id") REFERENCES "section" ("id");

COMMIT;