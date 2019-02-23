package com.digimenu.main.JsonSerializer;

import java.io.IOException;

import com.digimenu.main.entity.Restaurant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomRestaurantSerializer extends StdSerializer<Restaurant>{

	public CustomRestaurantSerializer() {
        this(null);
    }
 
    public CustomRestaurantSerializer(Class<Restaurant> t) {
        super(t);
    }

	@Override
	public void serialize(Restaurant value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		Restaurant res=new Restaurant();
		res=value;
		gen.writeObject(res);
	}
	

}
