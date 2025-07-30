package cv.igrp.simple.shared.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import cv.igrp.simple.utente.application.constants.GeneroTipo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigDeserializer {

    @Bean
    public Module generoDeserializerModule() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(GeneroTipo.class, new GeneroDeserializer());
        return module;
    }
}
