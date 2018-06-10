package com.sorahjy.buytickets.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

public class Date2LongSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(final Date value, final JsonGenerator gen, final SerializerProvider serializers)
        throws IOException {

        gen.writeNumber(value.getTime()/1000);

    }
}
