package mychamber.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/recipe/**").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/cookbook").setViewName("cookbook");
        registry.addViewController("/add-recipe").setViewName("cookbook");
        registry.addViewController("/edit-recipe/**").setViewName("cookbook");
        registry.addViewController("/chamber").setViewName("chamber");
        registry.addViewController("/add-chamber").setViewName("chamber");
        registry.addViewController("/edit-chamber/**").setViewName("chamber");
        registry.addViewController("/menu").setViewName("chamber");
        registry.addViewController("/add-food").setViewName("chamber");
    }
}