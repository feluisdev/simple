package cv.igrp.simple.shared.config;

import com.fasterxml.jackson.core.JacksonException;
import cv.igrp.simple.utente.application.constants.GeneroTipo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;


public class GeneroDeserializer extends JsonDeserializer<GeneroTipo>{

    @Override
    public GeneroTipo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String value = jsonParser.getText();
        if (value == null || value.trim().isEmpty()) {
            return null; // converte "" em null
        }
        return GeneroTipo.valueOf(value.toUpperCase());
    }
}
