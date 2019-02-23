package com.digimenu.main.JsonSerializer;

import java.io.IOException;

import com.digimenu.main.entity.Cart;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomCartDeserializer extends StdDeserializer<Cart> {

	protected CustomCartDeserializer(Class<?> vc) {
		super(vc);
	}
	
	protected CustomCartDeserializer() {
		this(null);
	}

	@Override
	public Cart deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return new Cart();
	}

}
