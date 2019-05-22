create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

create table oauth_client_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create table oauth_access_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BYTEA,
  refresh_token VARCHAR(255)
);

create table oauth_refresh_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication BYTEA
);

create table oauth_code (
  code VARCHAR(255), authentication BYTEA
);

create table oauth_approvals (
	userId VARCHAR(255),
	clientId VARCHAR(255),
	scope VARCHAR(255),
	status VARCHAR(10),
	expiresAt TIMESTAMP(0),
	lastModifiedAt TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity, autoapprove)
VALUES ('project_client', 'api-resource', '{bcrypt}$2a$10$1LET485G2Dc/vB.5jGKpCuNQBmcLFro3RoKb36v.C5MflNQVJVBkC', 'read,write', 'password,refresh_token', '', '43200', '2592000', 'false');

-- Table: public.role

-- DROP TABLE public.role;

CREATE SEQUENCE public.role_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
-- Sequence: public.userdb_id_seq

-- DROP SEQUENCE public.userdb_id_seq;

CREATE SEQUENCE public.userdb_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;


CREATE TABLE role
(
  id bigint NOT NULL DEFAULT nextval('role_id_seq'::regclass),
  role character varying(255),
  CONSTRAINT role_pkey PRIMARY KEY (id)
);


-- Table: public.userdb

-- DROP TABLE public.userdb;

CREATE TABLE userdb
(
  id bigint NOT NULL DEFAULT nextval('userdb_id_seq'::regclass),
  active boolean NOT NULL,
  created timestamp without time zone,
  email character varying(255),
  password character varying(255),
  CONSTRAINT userdb_pkey PRIMARY KEY (id)
);


CREATE TABLE user_role
(
  fk_user bigint NOT NULL,
  fk_role bigint NOT NULL,
  CONSTRAINT fkgbu0pflljt9n66vaqmpdh304n FOREIGN KEY (fk_role)
      REFERENCES public.role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fky8u1slclhdcguyubfx7aqdv2 FOREIGN KEY (fk_user)
      REFERENCES public.userdb (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
