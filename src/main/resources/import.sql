insert into city(id,name) values (22,'Edirne');
insert into city(id,name) values (17,'Çanakkale');

insert into user_table(id,email,is_enabled,name,password,surname,username) values(-1,'user@gmail.com',true,'user','$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by','user','user');
insert into user_table(id,email,is_enabled,name,password,surname,username) values(-2,'user2@gmail.com',true,'user2','$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by','user2','user2');
insert into user_table(id,email,is_enabled,name,password,surname,username) values(-3,'user3@gmail.com',true,'user3','$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by','user3','user3');


insert into role(id,name) values (1,'ROLE_ADMIN');
insert into role(id,name) values (2,'ROLE_RESTAURANT');
insert into role(id,name) values (3,'ROLE_USER');

insert into user_role(user_id,role_id) values (-1,3);
insert into user_role(user_id,role_id) values (-1,2);
insert into user_role(user_id,role_id) values (-2,3);
insert into user_role(user_id,role_id) values (-3,3);
insert into user_role(user_id,role_id) values (-3,2);

insert into restaurant(id,name,address,tel,mail,owner_id,city_id,table_amount,latitude,longitude,radius) values (1,'Aydın Tava Ciğer','çilingirler cad',123456789,'aydın@asd',-1,22,20,41.669290,26.593021,15);
insert into restaurant(id,name,address,tel,mail,owner_id,city_id,table_amount,latitude,longitude,radius) values (2,'Variant','üni',123456789,'var@asd',-3,22,15,41.669290,26.593021,15);

--alt alta yazma bunu patlıyor !!!
insert into category(id,name) values (-1,'İçecekler'),(-2,'Yiyecekler'),(-3,'Yemekler'),(-4,'Alkollü İçecekler'),(-5,'Alkolsüz İçecekler'),(-6,'Kokteyller'),(-7,'Alkollü Kokteyller'),(-8,'Alkolsüz Kokteyller'),(-9,'İthal Alkoller'),(-10,'Yerli Alkoller'),(-11,'Meşrubatlar'),(-12,'Sıcak İçecekler'),(-13,'Soğuk İçecekler'),(-14,'Soğuk Kahveler'),(-15,'Sıcak Kahveler'),(-16,'Alkollü Kahveler'),(-17,'Kahveler'),(-18,'Bitki Çayları'),(-19,'Başlangıçlar'),(-20,'Aperatifler'),(-21,'Kebaplar'),(-22,'Dönerler'),(-23,'Makarnalar'),(-24,'Pizzalar'),(-25,'Burgerler'),(-26,'Izgaralar'),(-27,'Köfteler'),(-28,'Kırmızı Et'),(-29,'Beyaz Et'),(-30,'Çorbalar'),(-31,'Salatalar'),(-32,'Deniz Ürünleri'),(-33,'Ana Yemekler'),(-34,'Ara Sıcaklar'),(-35,'Mezeler'),(-36,'Kahvaltılar'),(-37,'Sandviçler'),(-38,'Dürümler'),(-39,'Tatlılar'),(-40,'Diyet Menü'),(-41,'Sporcu Menü'),(-42,'Pastalar'),(-43,'Sütlü Tatlılar'),(-44,'Şerbetli Tatlılar'),(-45,'Dondurmalar'),(-46,'Waffle'),(-47,'Dünya Mutfağı'),(-48,'Türk Mutfağı'),(-49,'Uzak Doğu Mutfağı'),(-50,'Fransız Mutfağı'),(-51,'Meksika Mutfağı'),(-52,'İtalyan Mutfağı'),(-53,'Soğuk Mezeler'),(-54,'Sıcak Mezeler');



insert into menu(id,item,ingredients,price,restaurant_id,category,is_active,is_favourite,rating,vote_count) values (-1,'ciğer','dana ciğer',17,1,'Ana Yemek',true,false,0,0);
insert into menu(id,item,ingredients,price,restaurant_id,category,is_active,is_favourite,rating,vote_count) values (-2,'ayran','yogurt',3,1,'İçecek',true,false,0,0);
insert into menu(id,item,ingredients,price,restaurant_id,category,is_active,is_favourite,rating,vote_count) values (-3,'ciğer sarma','dana ciğer,lavaş',15.45,1,'Ana Yemek',true,false,0,0);
insert into menu(id,item,ingredients,price,restaurant_id,category,is_active,is_favourite,rating,vote_count) values (-4,'Izgara Köfte','dana kıyma,maydanoz,biber',16.56,1,'Ana Yemek',true,false,0,0);
insert into menu(id,item,ingredients,price,restaurant_id,category,is_active,is_favourite,rating,vote_count) values (-5,'Vali Kebabı','Köfte,Adana Kebap,Urfa Kebap,Lahmacun',35,1,'Ana Yemek',true,false,0,0);
insert into menu(id,item,ingredients,price,restaurant_id,category,is_active,is_favourite,rating,vote_count) values (-6,'İşkembe Çorbası','işkembe,sarımsak,sirke',10,1,'Ana Yemek',true,false,0,0);
insert into menu(id,item,ingredients,price,restaurant_id,category,is_active,is_favourite,rating,vote_count) values (-7,'Coca-Cola','',5,1,'İçecek',true,false,0,0);
insert into menu(id,item,ingredients,price,restaurant_id,category,is_active,is_favourite,rating,vote_count) values (-8,'Fanta','',5,1,'İçecek',true,false,0,0);
insert into menu(id,item,ingredients,price,restaurant_id,category,is_active,is_favourite,rating,vote_count) values (-9,'Su','',2,1,'İçecek',true,false,0,0);
insert into menu(id,item,ingredients,price,restaurant_id,category,is_active,is_favourite,rating,vote_count) values (-10,'bira','bira',20,2,'Alkollü İçecek',true,false,0,0);
insert into menu(id,item,ingredients,price,restaurant_id,category,is_active,is_favourite,rating,vote_count) values (-11,'patates kızartması','patetes,yağ',10,2,'Aperatifler',true,false,0,0);



