package com.previsto.dinero.net;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestTemplateHelper {

    private RestTemplateHelper() {}
    
    public static void configureForDineroJargon(RestTemplate restTemplate) {
        // Changed converter defaults
        for(HttpMessageConverter converter : restTemplate.getMessageConverters()) {
            if(converter instanceof MappingJackson2HttpMessageConverter) {
                ObjectMapper om = ((MappingJackson2HttpMessageConverter)converter).getObjectMapper();
                om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                om.enable(SerializationFeature.INDENT_OUTPUT);
                om.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
                om.registerModule(new JavaTimeModule());
            }
        }
        
        // Apply paged result interceptor
        restTemplate.getInterceptors().add(new PagedResultInterceptor());

        // Apply Throttle
        restTemplate.getInterceptors().add(new ThrottleInterceptor());
    }
}
