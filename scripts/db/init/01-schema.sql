CREATE SCHEMA IF NOT EXISTS book AUTHORIZATION postgres;
GRANT ALL ON SCHEMA book TO postgres;
ALTER ROLE postgres SET search_path TO book, public;