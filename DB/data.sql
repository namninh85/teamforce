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

--2019/06/04 - Dieu - insert data to table utility
INSERT INTO public.utility(utility_id,name,image,web_link,is_active,is_deleted)
VALUES (1,'seat','https://www.circlek.com.vn/wp-content/uploads/2016/01/seat.png','https://www.circlek.com.vn/wp-content/uploads/2016/01/seat',true,false),
(2,'food','https://www.circlek.com.vn/wp-content/uploads/2016/01/1.png','https://www.circlek.com.vn/wp-content/uploads/2016/01/1',true,false),
(3,'bill','https://www.circlek.com.vn/wp-content/uploads/2016/01/1.png','https://www.circlek.com.vn/wp-content/uploads/2016/01/1',true,false),
(4,'card','https://www.circlek.com.vn/wp-content/uploads/2018/10/icon_54_card_payment.png','https://www.circlek.com.vn/wp-content/uploads/2018/10/icon_54_card_payment',true,false),
(5,'phone','https://www.circlek.com.vn/wp-content/uploads/2016/01/phonecard.png','https://www.circlek.com.vn/wp-content/uploads/2016/01/phonecard',true,false),
(6,'game','https://www.circlek.com.vn/wp-content/uploads/2016/01/73.png','https://www.circlek.com.vn/wp-content/uploads/2016/01/73',true,false);


--2019/06/04 - Dieu - Insert data to table store
INSERT INTO public.store(store_id,name,street,ward,district,city,country,phone,position_map,utilities,is_active,is_deleted)
VALUES (1,'CÔNG TY TNHH PHƯƠNG VIỆT INTERNATIONAL','216 Võ Văn Ngân','Phường Bình Thọ','Quận Thủ Đức','TP Hồ Chí Minh','VN','093436636','123,556','1,2,3',true,false),
(2,'CÔNG TY TNHH PHUCCA','18 Cộng Hòa','Phường 4','Quận Thủ Đức','TP Hồ Chí Minh','VN','013436636','123345,522356','1,2,3,6',true,false),
(3,'CÔNG TY CỔ PHẦN SÀI THÀNH FOODS','Số 10 Nguyễn Tư Giản','Phường 12','Quận Gò Vấp,','TP Hồ Chí Minh','VN','092676636','12345,522356','1,2,3,5',true,false),
(4,'CÔNG TY CỔ PHẦN COHOTA','115/11/7/1C Đường 11','Khu Phố 9, Phường Trường Thọ','Quận Thủ Đức,','TP Hồ Chí Minh','VN','092676636','12345,522356','1,2',true,false);


--2019/06/04 - Dieu - insert data to table product_in_store
INSERT INTO product_in_store(product_id,store_id,stock_amount,is_active,is_deleted)
VALUES(1,1,10,true,false),
(1,2,15,true,false),
(1,3,20,true,false),
(2,2,21,true,false),
(2,3,14,true,false),
(3,1,2,true,false),
(2,1,30,true,false),
(4,1,5,true,false);

--2019/06/04 -Dieu - Insert data to voucher
INSERT INTO voucher(voucher_id,name,image,value,currency,description,number_date_use,is_active,is_deleted)
VALUES(1,'Dolpan Sam','https://salt.tikicdn.com/cache/w1200/ts/product/2a/fd/5e/16efa91c73dc1dd97812ed897e03f92e.png',179,'VND','Dolpan Sam - Voucher Buffet Nướng Trị Giá 179K',30,true,false),
(2,'Voucher 2 bia tươi Gammer và 1 xúc xích','https://salt.tikicdn.com/cache/w1200/ts/product/2a/fd/5e/d897e092e.png',300,'VND','Voucher 2 bia tươi Gammer và 1 xúc xích',10,true,false),
(3,'[E-Voucher] Yến Sào Hồng Sâm Cao Cấp Bionest Ginseng Lọ 70ml','https://salt.tikicdn.com/cache/w1200/ts/product/2a/fd/5e/16ee.png',219,'VND','[E-Voucher] Yến Sào Hồng Sâm Cao Cấp Bionest Ginseng Lọ 70ml',30,true,false),
(4,'The Coffee House - Voucher Thưởng Thức Toàn Menu Chỉ Có Tại TIKI','https://salt.tikicdn.com/cache/w1200/ts/product/2a/fd/5e/163f92e.png',400000,'VND','The Coffee House - Voucher Thưởng Thức Toàn Menu ',30,true,false),
(5,'[E-Voucher] Yến Sào Cao Cấp Bionest Gold - Hộp Quà Tặng (6 Lọ X 70ml)','https://salt.tikicdn.com/cache/w1200/ts/d/5e/163f92e.png',400,'VND','[E-Voucher] Yến Sào Cao Cấp Bionest Gold - Hộp Quà Tặng (6 Lọ X 70ml)',20,true,false),
(6,' Yến Sào Cao Cấp Bionest Gold - Hộp Tiết Kiệm (6 Lọ X 70ml)','https://salt.tikicdn.com/cache/w1200/ts/product/2a/897e03f92e.png',9000,'VND',' Yến Sào Cao Cấp Bionest Gold - Hộp Tiết Kiệm (6 Lọ X 70ml)',10,true,false),
(7,'Dolpan Sam','https://salt.tikicdn.com/cache/w1200/ts/product/2a/fd/52ed897e03f92e.png',1456,'VND','Dolpan Sam - Voucher Buffet Nướng Trị Giá 179K',15,true,false),
(8,'Voucher 100Gr Yến Tinh Chế - Yến Sào Cao Cấp Nha Trang NestViet','https://salt.tikicdn.com/cache/w1200/ts/product/2a/fd/5e/16efa93f92e.png',540000,'VND','Voucher 100Gr Yến Tinh Chế - Yến Sào Cao Cấp Nha Trang NestViet',20,true,false);

 --2019/06/04 - Dieu - Insert data table loyalty_program
 INSERT INTO loyalty_program(loyalty_program_id,voucher_id,start_date,end_date,point,total_release,available,is_active,is_deleted)
 VALUES(1,1,20190601,20190610,4000,200,50,true,false),
 (2,2,20190601,20190610,4000,200,50,true,false),
 (3,3,20190601,20190610,4000,200,50,true,false),
 (4,1,20190611,20190620,4000,200,50,true,false),
 (5,3,20190611,20190620,4000,200,50,true,false),
 (6,4,20190611,20190620,4000,200,50,true,false),
 (7,2,20190621,20190625,4000,200,50,true,false),
 (8,5,20190621,20190625,4000,200,50,true,false),
 (9,3,20190625,20190627,4000,200,50,true,false),
 (10,4,20190625,20190627,4000,200,50,true,false);