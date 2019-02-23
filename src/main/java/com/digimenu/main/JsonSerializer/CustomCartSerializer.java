package com.digimenu.main.JsonSerializer;

import java.io.IOException;

import com.digimenu.main.entity.Cart;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomCartSerializer extends StdSerializer<Cart>{

	protected CustomCartSerializer(Class<Cart> t) {
		super(t);
	}
	protected CustomCartSerializer() {
		this(null);
	}
	@Override
	public void serialize(Cart value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		Cart cart=new Cart();
		cart=value;
		gen.writeObject(cart);
	}
	
	
}
