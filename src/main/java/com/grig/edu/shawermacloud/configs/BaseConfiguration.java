package com.grig.edu.shawermacloud.configs;

import com.grig.edu.shawermacloud.repositories.ingredient.IngredientRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BaseConfiguration implements WebMvcConfigurer {

    private final IngredientRepository memRepository;
    private final IngredientRepository repository;

    public BaseConfiguration(@Qualifier("inMemoryRepository") IngredientRepository memRepository,
                             @Qualifier("jdbcIngredientRepository") IngredientRepository repository) {
        this.memRepository = memRepository;
        this.repository = repository;
    }

    @Bean
    public CommandLineRunner loadDataIntoDb() {
        return args -> memRepository.findAll().forEach(repository::save);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/success").setViewName("success");

    }
}
