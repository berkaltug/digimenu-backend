insert into city(id,name) values (22,'Edirne');
insert into city(id,name) values (17,'Çanakkale');

insert into user_table(id,email,is_enabled,name,password,surname,username) values(1,'user@gmail.com',true,'user','$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by','user','user');
insert into user_table(id,email,is_enabled,name,password,surname,username) values(2,'user2@gmail.com',true,'user2','$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by','user2','user2');

insert into role(id,name) values (1,'ROLE_ADMIN');
insert into role(id,name) values (2,'ROLE_RESTAURANT');
insert into role(id,name) values (3,'ROLE_USER');

insert into user_role(user_id,role_id) values (1,3);
insert into user_role(user_id,role_id) values (1,2);

insert into restaurant(id,name,address,tel,mail,owner_id,city_id,table_amount) values (1,'Aydın Tava Ciğer','çilingirler cad',123456789,'aydın@asd',1,22,20);
insert into restaurant(id,name,address,tel,mail,owner_id,city_id,table_amount) values (2,'Variant','üni',123456789,'var@asd',2,22,15);

insert into category(id,name) values (1,'yiyecek');
insert into category(id,name) values (2,'içecek');

insert into restaurant_category(category_id,restaurant_id) values (1,1);
insert into restaurant_category(category_id,restaurant_id) values (2,1);
insert into restaurant_category(category_id,restaurant_id) values (1,2);
insert into restaurant_category(category_id,restaurant_id) values (2,2);

insert into menu(id,item,ingredients,price,restaurant_id,category) values (1,'ciğer','dana ciğer',17,1,'Ana Yemek');
insert into menu(id,item,ingredients,price,restaurant_id,category) values (2,'ayran','yogurt',3,1,'İçecek');
insert into menu(id,item,ingredients,price,restaurant_id,category) values (3,'ciğer sarma','dana ciğer,lavaş',15,1,'Ana Yemek');
insert into menu(id,item,ingredients,price,restaurant_id,category) values (4,'Izgara Köfte','dana kıyma,maydanoz,biber',16,1,'Ana Yemek');
insert into menu(id,item,ingredients,price,restaurant_id,category) values (5,'Vali Kebabı','Köfte,Adana Kebap,Urfa Kebap,Lahmacun',35,1,'Ana Yemek');
insert into menu(id,item,ingredients,price,restaurant_id,category) values (6,'İşkembe Çorbası','işkembe,sarımsak,sirke',10,1,'Ana Yemek');
insert into menu(id,item,ingredients,price,restaurant_id,category) values (7,'Coca-Cola','',5,1,'İçecek');
insert into menu(id,item,ingredients,price,restaurant_id,category) values (8,'Fanta','',5,1,'İçecek');
insert into menu(id,item,ingredients,price,restaurant_id,category) values (9,'Su','',2,1,'İçecek');
insert into menu(id,item,ingredients,price,restaurant_id,category) values (10,'bira','bira',20,2,'Alkollü İçecek');
insert into menu(id,item,ingredients,price,restaurant_id,category) values (11,'patates kızartması','patetes,yağ',10,2,'Aperatifler');



