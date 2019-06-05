--
-- PostgreSQL database dump
--

-- Dumped from database version 11.3 (Ubuntu 11.3-1.pgdg18.04+1)
-- Dumped by pg_dump version 11.3 (Ubuntu 11.3-1.pgdg18.04+1)

-- Started on 2019-06-05 11:43:06 +07

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 216 (class 1259 OID 17704)
-- Name: store; Type: TABLE; Schema: public; Owner: postgres
--
--DROPTABLE BEFORE CREATE
DROP TABLE IF EXISTS public.store;


-- create table 
CREATE TABLE public.store (
    store_id integer NOT NULL,
    name character varying(250),
    is_active boolean,
    is_deleted boolean,
    phone character varying(50),
    utilities character varying(20),
    address character varying(255),
    latitude double precision,
    longitude double precision
);


ALTER TABLE public.store OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 17702)
-- Name: store_store_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.store_store_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.store_store_id_seq OWNER TO postgres;

--
-- TOC entry 3041 (class 0 OID 0)
-- Dependencies: 215
-- Name: store_store_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.store_store_id_seq OWNED BY public.store.store_id;


--
-- TOC entry 2910 (class 2604 OID 17707)
-- Name: store store_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.store ALTER COLUMN store_id SET DEFAULT nextval('public.store_store_id_seq'::regclass);


--
-- TOC entry 3035 (class 0 OID 17704)
-- Dependencies: 216
-- Data for Name: store; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.store(store_id,name,address,phone,utilities,is_active,is_deleted,latitude,longitude) 
VALUES ('1','Circle K','33 Hoàng Hoa Thám, Phường 11, Quận Tân Bình, Tp HCM, Việt Nam','+84 28 3526 1003','1,2,3,4,5,6',true,false,10.7992202,106.6471282),
('2','Circle K','Landmark 1, Phường 22, Quận Bình Thạnh, Tp Hcm, Việt Nam','+84 28 3526 1003','1,2,3,4,5,6',true,false,10.7955759,106.7192569),
('3','Circle K','306 Cao Thắng, Phường 12, Quận 10, Tp HCM, Việt Nam','+84 28 3526 1003','1,2,3,4,5,6',true,false,10.77496,106.67477009999993),
('4','Circle K','104 Hồ Tùng Mậu, Phường Bến Nghé, Quận 1, Tp.HCM, Việt Nam','+84 28 3526 1003','1,2,3,4,5,6',true,false,10.7729456,106.70352809999997),
('5','Circle K','175 Nguyễn Công Trứ, Phường Bến Thành, Quận 1, Tp.HCM, Việt Nam','+84 28 3526 1003','1,2,3,4,5,6',true,false,10.768191,106.701053),
('6','Circle K','52 Trương Định, Phường Bến Thành, Quận 1, Tp.HCM, Việt Nam','+84 28 3526 1003','1,2,3,4,5,6',true,false,10.7725285,106.69585749999999),
('7','Circle K','24 Hồ Huấn Nghiệp, Phường Bến Nghé, Quận 1, Tp.HCM, Việt Nam','+84 28 3526 1003','1,2,3,4,5,6',true,false,10.774498,106.70512299999996),
('8','Circle K','2 Trần Khắc Chân, Phường Tân Định, Quận 1, Tp.HCM, Việt Nam','+84 28 3526 1003','1,2,3,4,5,6',true,false,10.792414,106.69131289999996),
('9','Circle K','250 Đề Thám, Phường Phạm Ngũ Lão, Quận 1, Tp.HCM, Việt Nam','+84 28 3526 1003','1,2,3,4,5,6',true,false,10.76815,106.69379400000002),
('10','Circle K','62 Phạm Ngọc Thạch, Phường 06, Quận 3, Tp.HCM, Việt Nam','+84 28 3526 1003','1,2,3,4,5,6',true,false,10.786109500465226,106.69226713478565);


--
-- TOC entry 3042 (class 0 OID 0)
-- Dependencies: 215
-- Name: store_store_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.store_store_id_seq', 1, false);


--
-- TOC entry 2912 (class 2606 OID 17712)
-- Name: store store_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.store
    ADD CONSTRAINT store_pkey PRIMARY KEY (store_id);


-- Completed on 2019-06-05 11:43:06 +07

--
-- PostgreSQL database dump complete
--

