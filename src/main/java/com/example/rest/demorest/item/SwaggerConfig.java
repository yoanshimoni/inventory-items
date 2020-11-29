package com.example.rest.demorest.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
 @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo())
          .produces(new HashSet<String>(Arrays.asList("application/json", "application/json")));
                                                     
    }
  
  private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Inventory Exercise Api", 
	      "Inventory Exercise", 
	      "1.0.0", 
	      "Terms of service", 
	      new Contact("Yoan Shimoni", "", "yoanshimoni@gmail.com"), 
	      "License ", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}

}
