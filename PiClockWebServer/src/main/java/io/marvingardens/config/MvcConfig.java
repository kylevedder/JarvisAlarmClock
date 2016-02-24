package io.marvingardens.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mustache.web.MustacheViewResolver;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.samskivert.mustache.Mustache;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "io.marvingardens" })
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired private MustacheViewResolver resolver;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	@PostConstruct
	public void call() {
		resolver.setCompiler(Mustache.compiler().defaultValue(""));
	}

}
