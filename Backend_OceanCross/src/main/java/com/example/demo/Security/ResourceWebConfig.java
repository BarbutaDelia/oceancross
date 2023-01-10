package com.example.demo.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceWebConfig implements WebMvcConfigurer {
    final Environment environment;

    public ResourceWebConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        //String location = environment.getProperty("app.file.storage.mapping");

        String location = System.getProperty("user.dir") + "\\uploads\\files\\";
        //registry.addResourceHandler("/uploads/**").addResourceLocations(location);
        //TODO de vazut caile
        registry.addResourceHandler("/uploads/files/**").addResourceLocations(location);
    }
}