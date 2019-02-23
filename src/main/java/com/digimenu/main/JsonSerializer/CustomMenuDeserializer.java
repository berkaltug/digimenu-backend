package com.digimenu.main.JsonSerializer;

import java.io.IOException;

import com.digimenu.main.entity.Menu;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.LongNode;

public class CustomMenuDeserializer extends StdDeserializer<Menu> {

	protected CustomMenuDeserializer(Class<?> vc) {
		super(vc);
	}
	protected CustomMenuDeserializer() {
		this(null);
	}
	
	@Override
	public Menu deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node=p.getCodec().readTree(p);
		Long id=(Long) node.get("id").asLong();
//		String item=node.get("item").asText();
//		String ingredients=node.get("ingredients").asText();
//		Integer price=(Integer)((IntNode)node.get("price")).numberValue();
//		String category=node.get("category").asText();
		//JsonNode resNode = node.get("restaurant"); içerdeki objeleri yapmadan deneyelim
		
		return new Menu(id);
	}

}
