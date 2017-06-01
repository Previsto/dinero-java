package com.previsto.dinero.autoconfigure;

import com.previsto.dinero.DineroClient;
import org.junit.After;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import static org.junit.Assert.*;
import org.springframework.boot.test.EnvironmentTestUtils;

public class DineroClientAutoConfigurationTest {

    private AnnotationConfigApplicationContext context;

    @After
    public void tearDown() {
        if (this.context != null) {
            this.context.close();
        }
    }

    @Test
    public void defaultNativeConnectionFactory() {
        load(EmptyConfiguration.class, 
                "dinero.serviceUrl=https://api.billysbilling.com/v2",
                "dinero.apiKey=qwerty123");
        DineroClient client = this.context.getBean(DineroClient.class);
        assertEquals("qwerty123", client.getApiKey());
    }

    @Configuration
    static class EmptyConfiguration {
    }

    private void load(Class<?> config, String... environment) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        EnvironmentTestUtils.addEnvironment(applicationContext, environment);
        applicationContext.register(config);
        applicationContext.register(DineroClientAutoConfiguration.class, DineroClientFactoryAutoConfiguration.class);
        applicationContext.refresh();
        this.context = applicationContext;
    }

}