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


CREATE TABLE product(
   product_id serial,
   code varchar(250),
   name varchar(250),
   description text,
   is_active boolean,
   is_deleted boolean,
   PRIMARY KEY(product_id)
);

CREATE TABLE product_category(
   product_category_id serial,
   code varchar(250),
   name varchar(250),
   parent_id int,
   description text,
   is_active boolean,
   is_deleted boolean,
   PRIMARY KEY(product_category_id)
);


CREATE TABLE product_in_pd_category(
   pipc_id serial,
   product_id int,
   product_category_id int,
   PRIMARY KEY(pipc_id)
);

CREATE TABLE price(
   price_id serial,
   product_id int,
   value decimal,
   start_date bigint,
   end_date bigint,
   is_active boolean,
   is_deleted boolean,
   PRIMARY KEY(price_id)
);

CREATE TABLE store(
   store_id serial,
   name varchar(250),
   street varchar(250),
   district varchar(250),
   city varchar(250),
   position_map varchar(250),
   is_active boolean,
   is_deleted boolean,
   PRIMARY KEY(store_id)
);

CREATE TABLE product_in_store(
   product_id int,
   store_id int,
   stock_amount int,
   is_active boolean,
   is_deleted boolean,
   PRIMARY KEY(store_id)
);

CREATE TABLE customer(
   customer_id int,
   first_name varchar(250),
   last_name varchar(250),
   email varchar(250),
   phone varchar(250),
   lang varchar(250),
   qrcode text,
   total_point int,
   total_voucher int,
   is_active boolean,
   is_deleted boolean,
   PRIMARY KEY(customer_id)
);


CREATE TABLE customer_like_product(
   customer_id int,
   product_id int
);

CREATE TABLE voucher(
   voucher_id serial,
   value decimal,
   description text,
   is_active boolean,
   is_deleted boolean,
   PRIMARY KEY(voucher_id)
);

CREATE TABLE voucher_code(
   voucher_code_id serial,
   voucher_id int,
   voucher_code varchar(250),
   is_active boolean,
   is_deleted boolean,
   PRIMARY KEY(voucher_code_id)
);

CREATE TABLE loyalty_program(
   loyalty_program_id serial,
   voucher_id int,
   start_date bigint,
   end_date bigint,
   is_active boolean,
   is_deleted boolean,
   PRIMARY KEY(loyalty_program_id)
);

CREATE TABLE customer_rewards_log(
   customer_rewards_log_id serial,
   customer_id int,
   point_burn_earn int,
   loyalty_program_id int,
   voucher_code_id int,
   reward_date bigint,
   is_active boolean,
   is_deleted boolean,
   PRIMARY KEY(customer_rewards_log_id)
);

CREATE TABLE customer_has_voucher(
   customer_has_voucher_id serial,
   customer_id int,
   voucher_code_id int,
   received_date bigint,
   expired_date bigint,
   is_active boolean,
   is_deleted boolean,
   PRIMARY KEY(customer_has_voucher_id)
);

CREATE TABLE history_log(
   history_log_id serial,
   date bigint,
   table_name varchar(250),
   content_log text,
   PRIMARY KEY(history_log_id)
);

-- 2019/05/28: update customer

ALTER TABLE customer ADD COLUMN interested_fields character varying(250),
ADD COLUMN address character varying(250),
ADD COLUMN avartar_img text,
ADD COLUMN banner_header_img text,
ADD COLUMN qrcode_img text;


-- 2019/05/29: update customer (total_offer, is_deleted)
ALTER TABLE customer ADD COLUMN total_offer int;
ALTER TABLE customer ALTER COLUMN is_deleted
SET DEFAULT 'FALSE';

-- 2019/05/29 :Dieu -  update table product  (image , web_link)
ALTER TABLE product ADD COLUMN image text,
ADD COLUMN web_link text,
ADD COLUMN has_new boolean;

-- 2019/05/29 :Dieu -  update table product_category  (image , web_link)
ALTER TABLE product_category ADD COLUMN image text,
ADD COLUMN web_link text;


-- 2019/05/30  :Dieu -  Insert data to table product_category
INSERT INTO public.product_category(product_category_id ,code,name,parent_id,description,image,web_link,is_active,is_deleted)
values(1,'BIA – RƯỢU TRÁI CÂY','BIA – RƯỢU TRÁI CÂY',0,'Sản phẩm bia và rượu trái cây tại Circle K được phục vụ 24/7 với đầy đủ các thương hiệu nổi tiếng trong nước và trên thế giới.Bạn sẽ không lo cuộc vui của mình bị gián đoạn.','https://www.circlek.com.vn/wp-content/uploads/2016/01/blk-1-BEER-pc-450.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false),
	   (2,'RƯỢU','RƯỢU',0,'Tại Circle K, bạn sẽ an tâm vì sản phẩm của chúng tôi  được đảm bảo về chất lượng và xuất xứ rõ ràng.',
		'https://www.circlek.com.vn/wp-content/uploads/2016/01/wine_470x450.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false),
	   (3,'NƯỚC GIẢI KHÁT LẠNH','NƯỚC GIẢI KHÁT LẠNH',0,'Hầu hết các thương hiệu nước giải khát được yêu thích, có chất lượng và uy tín trên thị trường hiện nay đã có tại Circle K, từ nước tinh khiết, nước khoáng thiên nhiên, nước có ga, nước tăng lực, nước uống thể thao, trà, cà phê, nước trái cây…',
		'https://www.circlek.com.vn/wp-content/uploads/2016/01/blk-3-DRINK-pc1.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false),
	   (4,'BIA – RƯỢU TRÁI CÂY','BIA – RƯỢU TRÁI CÂY',0,'Sản phẩm bia và rượu trái cây tại Circle K được phục vụ 24/7 với đầy đủ các thương hiệu nổi tiếng trong nước và trên thế giới.  Bạn sẽ không lo cuộc vui của mình bị gián đoạn.','https://www.circlek.com.vn/wp-content/uploads/2016/01/blk-1-BEER-pc-450.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false),
	   (5,'BÁNH KẸO','BÁNH KẸO',0,'Chủng loại bánh, kẹo, kẹo cao su, sô-cô-la đa dạng tại Circle K sẽ mang đến nhiều niềm vui cho bữa ăn nhẹ thường ngày của bạn.','https://www.circlek.com.vn/wp-content/uploads/2016/01/candy-biscuit_4701.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false),
	   (6,'BÁNH MÌ TƯƠI','BÁNH MÌ TƯƠI',0,'Các loại bánh tươi đa dạng đươc chế biến bằng nguyên liệu cao cấp, bảo đảm an toàn vê sinh thực phẩm, có xuất xứ trong nước và nước ngoài với các thương hiệu nổi tiếng như SAVOURE, D-PLUS, SAMPLIP,LEELA… cùng nhiều mùi vị hấp dẫn sẽ mang đến cho khách hàng những trải nghiệm thú vị.',
		'https://www.circlek.com.vn/wp-content/uploads/2018/04/Bakery.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false),
	   (7,'SNACK','SNACK',0,'Tại Circle K Việt Nam, Snack là ngành hàng đa dạng nhất với nhiều lựa chọn từ các nhãn hàng trong nước, quốc tế đến các sản phẩm nhập khẩu của riêng Circle K như các loại hạt, rong biển, thịt/ cá khô…'
		,'https://www.circlek.com.vn/wp-content/uploads/2016/01/blk-5-SNACK-pc1.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false),
	   (8,'KEM','KEM',0,'Kem tại Circle K luôn đa dạng về chủng loại (kem que, ốc quế, kem hộp) và phong phú về hương vị, dung tích, thương hiệu (của Việt Nam, Hàn Quốc, New Zealand, Mỹ…)'
		,'https://www.circlek.com.vn/wp-content/uploads/2016/01/Product-Ice-cream-update-san-pham.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false),
	   (9,'SỮA & SẢN PHẨM TỪ SỮA','SỮA & SẢN PHẨM TỪ SỮA',0,'Circle K cung cấp các loại sản phẩm sữa tươi, sữa tiệt trùng và các sản phẩm chế biến từ sữa …'
		,'https://www.circlek.com.vn/wp-content/uploads/2016/01/blk-6-DAIRY-pc1.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false);

--2019/05/30 - Dieu - Create table utility
CREATE TABLE utility(
   utility_id serial,
   name VARCHAR(250),
   image text,
   web_link text,
   is_active boolean,
   is_deleted boolean,
   PRIMARY KEY(utility_id)
);

--2019/31/05  - Dieu - Update table product_in_store
DROP TABLE IF EXISTS product_in_store;
CREATE TABLE product_in_store(
   product_id int,
   store_id int,
   stock_amount int,
   is_active boolean,
   is_deleted boolean,
   PRIMARY KEY(product_id,store_id)
);

-- 2019/05/31 :Dieu -  update table store  (ward , country)
ALTER TABLE store ADD COLUMN ward VARCHAR(250),
ADD COLUMN country VARCHAR(250),
ADD COLUMN phone VARCHAR(50),
ADD COLUMN  utilities varchar(20);

--2019/06/03 : Dieu - update table voucher
ALTER TABLE voucher ADD COLUMN name VARCHAR(250),
ADD COLUMN image text ,
ADD COLUMN currency VARCHAR(50),
ADD COLUMN number_date_use int;

-- 2019/06/02 : Nin - New Table: special_offer
CREATE TABLE special_offer
(
   special_offer_id serial,
   code varchar(250),
   name varchar(250),
   image text,
   web_link text,
   is_active boolean,
   is_deleted boolean,
   PRIMARY KEY(special_offer_id)

);
ALTER TABLE special_offer ALTER COLUMN is_deleted
SET DEFAULT 'FALSE';

INSERT INTO special_offer(special_offer_id, code, name, image, web_link, is_active, is_deleted) VALUES (1,'fast-delicious','fast-delicious','http://triqtran.github.io/images/loyalty/special-offer/fast-delicious.jpg','https://www.circlek.com.vn/vi/thuc-an-thuc-uong/',TRUE,FALSE);
INSERT INTO special_offer(special_offer_id, code, name, image, web_link, is_active, is_deleted) VALUES (2,'food-box','food-box','http://triqtran.github.io/images/loyalty/special-offer/food-box.jpg','https://www.circlek.com.vn/vi/thuc-an-thuc-uong/thuc-an/',TRUE,FALSE);
INSERT INTO special_offer( special_offer_id, code, name, image, web_link, is_active, is_deleted) VALUES (3,'drink-box','drink-box','http://triqtran.github.io/images/loyalty/special-offer/drink-box.jpg','https://www.circlek.com.vn/vi/',TRUE,FALSE);
INSERT INTO special_offer( special_offer_id, code, name, image, web_link, is_active, is_deleted) VALUES (4,'promotion','promotion','http://triqtran.github.io/images/loyalty/special-offer/promotion.png','https://www.circlek.com.vn/vi/uu-dai-dac-biet/?l=tp-ho-chi-minh-1',TRUE,FALSE);
INSERT INTO special_offer(special_offer_id, code, name, image, web_link, is_active, is_deleted) VALUES (5,'products-services','products-services','http://triqtran.github.io/images/loyalty/special-offer/products-services.jpg','https://www.circlek.com.vn/vi/san-pham-dich-vu/',TRUE,FALSE);




--2019/06/03 : Dieu - update table loyalty_program
ALTER TABLE loyalty_program ADD COLUMN point int ,
ADD COLUMN total_release int,
ADD COLUMN available int ;

--2019/06/04 : Dieu - Insert data product to table product
INSERT INTO public.product(product_id ,code,name,image,web_link,description,is_active,is_deleted,has_new)
values(1,'BIA – RƯỢU TRÁI CÂY','BIA – RƯỢU TRÁI CÂY','Sản phẩm bia và rượu trái cây tại Circle K được phục vụ 24/7 với đầy đủ các thương hiệu nổi tiếng trong nước và trên thế giới.Bạn sẽ không lo cuộc vui của mình bị gián đoạn.','https://www.circlek.com.vn/wp-content/uploads/2016/01/blk-1-BEER-pc-450.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false,true),
	   (2,'RƯỢU','RƯỢU','Tại Circle K, bạn sẽ an tâm vì sản phẩm của chúng tôi  được đảm bảo về chất lượng và xuất xứ rõ ràng.',
		'https://www.circlek.com.vn/wp-content/uploads/2016/01/wine_470x450.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false,true),
	   (3,'NƯỚC GIẢI KHÁT LẠNH','NƯỚC GIẢI KHÁT LẠNH','Hầu hết các thương hiệu nước giải khát được yêu thích, có chất lượng và uy tín trên thị trường hiện nay đã có tại Circle K, từ nước tinh khiết, nước khoáng thiên nhiên, nước có ga, nước tăng lực, nước uống thể thao, trà, cà phê, nước trái cây…',
		'https://www.circlek.com.vn/wp-content/uploads/2016/01/blk-3-DRINK-pc1.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false,false),
	   (4,'BIA – RƯỢU TRÁI CÂY','BIA – RƯỢU TRÁI CÂY','Sản phẩm bia và rượu trái cây tại Circle K được phục vụ 24/7 với đầy đủ các thương hiệu nổi tiếng trong nước và trên thế giới.  Bạn sẽ không lo cuộc vui của mình bị gián đoạn.','https://www.circlek.com.vn/wp-content/uploads/2016/01/blk-1-BEER-pc-450.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false,false),
	   (5,'BÁNH KẸO','BÁNH KẸO','Chủng loại bánh, kẹo, kẹo cao su, sô-cô-la đa dạng tại Circle K sẽ mang đến nhiều niềm vui cho bữa ăn nhẹ thường ngày của bạn.','https://www.circlek.com.vn/wp-content/uploads/2016/01/candy-biscuit_4701.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false,true),
	   (6,'BÁNH MÌ TƯƠI','BÁNH MÌ TƯƠI','Các loại bánh tươi đa dạng đươc chế biến bằng nguyên liệu cao cấp, bảo đảm an toàn vê sinh thực phẩm, có xuất xứ trong nước và nước ngoài với các thương hiệu nổi tiếng như SAVOURE, D-PLUS, SAMPLIP,LEELA… cùng nhiều mùi vị hấp dẫn sẽ mang đến cho khách hàng những trải nghiệm thú vị.',
		'https://www.circlek.com.vn/wp-content/uploads/2018/04/Bakery.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false,false),
	   (7,'SNACK','SNACK','Tại Circle K Việt Nam, Snack là ngành hàng đa dạng nhất với nhiều lựa chọn từ các nhãn hàng trong nước, quốc tế đến các sản phẩm nhập khẩu của riêng Circle K như các loại hạt, rong biển, thịt/ cá khô…'
		,'https://www.circlek.com.vn/wp-content/uploads/2016/01/blk-5-SNACK-pc1.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false,true),
	   (8,'KEM','KEM','Kem tại Circle K luôn đa dạng về chủng loại (kem que, ốc quế, kem hộp) và phong phú về hương vị, dung tích, thương hiệu (của Việt Nam, Hàn Quốc, New Zealand, Mỹ…)'
		,'https://www.circlek.com.vn/wp-content/uploads/2016/01/Product-Ice-cream-update-san-pham.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false,false),
	   (9,'SỮA & SẢN PHẨM TỪ SỮA','SỮA & SẢN PHẨM TỪ SỮA','Circle K cung cấp các loại sản phẩm sữa tươi, sữa tiệt trùng và các sản phẩm chế biến từ sữa …'
		,'https://www.circlek.com.vn/wp-content/uploads/2016/01/blk-6-DAIRY-pc1.png',
	   'https://www.circlek.com.vn/vi/san-pham-dich-vu/san-pham/',true,false,true);
