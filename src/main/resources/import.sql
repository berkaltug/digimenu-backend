
insert into city(id,name) values (22,'Edirne');
insert into city(id,name) values (17,'Çanakkale');

insert into restaurant(id,name,address,tel,mail,owner,city_id) values (1,'Aydın Tava Ciğer','çilingirler cad',123456789,'aydın@asd','Aydın',22);
insert into restaurant(id,name,address,tel,mail,owner,city_id) values (2,'Variant','üni',123456789,'var@asd','Berk',22);

insert into menu(id,item,ingredients,price,restaurant_id) values (1,'ciğer','dana ciğer',17,1);
insert into menu(id,item,ingredients,price,restaurant_id) values (2,'ayran','yogurt',3,1);
insert into menu(id,item,ingredients,price,restaurant_id) values (3,'bira','bira',20,2);
insert into menu(id,item,ingredients,price,restaurant_id) values (4,'patates kızartması','patetes,yağ',10,2);

insert into category(id,name) values (1,'yiyecek');
insert into category(id,name) values (2,'içecek');

insert into category_restaurant(category_id,restaurant_id) values (1,1);
insert into category_restaurant(category_id,restaurant_id) values (2,1);
insert into category_restaurant(category_id,restaurant_id) values (1,2);
insert into category_restaurant(category_id,restaurant_id) values (2,2);
