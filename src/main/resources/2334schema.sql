drop function public.insert_cart();
CREATE function public.insert_cart() returns trigger as '
	BEGIN
			INSERT INTO cart(id,item,masa_no,restaurant_id,siparis_tarihi,total_price)
			VALUES (NEW.id,NEW.item,NEW.masa,NEW.restaurant_id,current_timestamp,NEW.price);
			RETURN NEW;
	END;
' language plpgsql;

CREATE trigger insert_cart before insert on table_orders for each row execute procedure insert_cart();