package com.digimenu.main.JsonSerializer;

import java.io.IOException;

import com.digimenu.main.entity.Restaurant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomRestaurantDeserializer extends StdDeserializer<Restaurant> {

	protected CustomRestaurantDeserializer(Class<?> vc) {
		super(vc);
	}
	
	public CustomRestaurantDeserializer() {
        this(null);
    } 
	
	@Override
	public Restaurant deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		return new Restaurant();
	}

}
