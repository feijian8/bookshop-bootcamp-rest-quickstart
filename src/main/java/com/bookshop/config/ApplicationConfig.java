package com.bookshop.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.bookshop.controllers.BookController;
import com.mongodb.Mongo;

@Configuration
@ComponentScan(basePackageClasses = BookController.class)
@EnableWebMvc
public class ApplicationConfig extends WebMvcConfigurerAdapter{

//	@Bean
//	public MongoTemplate mongoTemplate() throws Exception{
//		Mongo mongo = new Mongo("localhost", 27017);
//		MongoTemplate mongoTemplate = new MongoTemplate(mongo, "bookshop");
//		return mongoTemplate;
//	}
	
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(openshiftMongoDBFactoryConfig());
		return mongoTemplate;
	}

	@Bean
	public MongoDbFactory openshiftMongoDBFactoryConfig() throws Exception {
		String openshiftMongoDbHost = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
		int openshiftMongoDbPort = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
		String username = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
		String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
		Mongo mongo = new Mongo(openshiftMongoDbHost, openshiftMongoDbPort);
		UserCredentials userCredentials = new UserCredentials(username,password);
		String databaseName = System.getenv("OPENSHIFT_APP_NAME");
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo, databaseName, userCredentials);
		return mongoDbFactory;
	}
	
	@Bean
	public MappingJacksonJsonView jsonView(){
		MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
		jsonView.setPrefixJson(true);
		return jsonView;
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		super.addFormatters(registry);
		registry.addConverter(new Converter<String, String[]>() {

			@Override
			public String[] convert(String source) {
				return source.split(",");
			}
		});
		
		registry.addConverter(new Converter<String[], String>() {

			@Override
			public String convert(String[] source) {
				return Arrays.toString(source);
			}
		});
	}
}
