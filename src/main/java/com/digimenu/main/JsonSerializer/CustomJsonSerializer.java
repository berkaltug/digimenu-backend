package com.digimenu.main.JsonSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.digimenu.main.entity.Table_Orders;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomJsonSerializer extends StdSerializer<List<Table_Orders>> {

	public CustomJsonSerializer() {
        this(null);
    }
 
    public CustomJsonSerializer(Class<List<Table_Orders>> t) {
        super(t);
    }
    
    @Override
    public void serialize(
      List<Table_Orders> items, 
      JsonGenerator generator, 
      SerializerProvider provider) 
      throws IOException, JsonProcessingException {
         
        List<Table_Orders> ids = new ArrayList<>();
        for (Table_Orders item : items) {
            ids.add(item);
        }
        generator.writeObject(ids);
    }
 
}
