package com.digimenu.main.JsonSerializer;

import java.io.IOException;

import com.digimenu.main.entity.Menu;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomMenuSerializer extends StdSerializer<Menu>{

	protected CustomMenuSerializer(Class<Menu> t) {
		super(t);
	}
	protected CustomMenuSerializer() {
		this(null);
	}
	
	@Override
	public void serialize(Menu value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		Menu menu=new Menu();
		menu=value;
		gen.writeObject(menu);
	}

}
