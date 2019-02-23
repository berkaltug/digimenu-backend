package com.digimenu.main.JsonSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.digimenu.main.entity.Table_Orders;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomJsonDeserializer extends StdDeserializer<List<Table_Orders>>{
	
	public CustomJsonDeserializer() {
        this(null);
    }
 
    public CustomJsonDeserializer(Class<?> vc) {
        super(vc);
    }
 
    @Override
    public List<Table_Orders> deserialize(
      JsonParser jsonparser, 
      DeserializationContext context) 
      throws IOException, JsonProcessingException {
         
        return new ArrayList<>();
    }
}
