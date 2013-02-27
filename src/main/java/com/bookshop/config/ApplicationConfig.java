package com.bookshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.bookshop.controllers.BookController;

@Configuration
@ComponentScan(basePackageClasses = BookController.class)
@EnableWebMvc
public class ApplicationConfig extends WebMvcConfigurerAdapter {

	@Bean
	public MappingJacksonJsonView jsonView() {
		MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
		jsonView.setPrefixJson(true);
		return jsonView;
	}

}
