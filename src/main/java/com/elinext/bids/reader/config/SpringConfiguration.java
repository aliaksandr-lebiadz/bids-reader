package com.elinext.bids.reader.config;

import com.fasterxml.jackson.core.JsonFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Bean
    public JsonFactory jsonFactory() {
        return new JsonFactory();
    }

}
