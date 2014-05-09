package eightysix250kaffe.rest.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 Main Spring Configuration
 */
@Configuration
@ImportResource({
        "classpath:spring/kaffe-rest-api-spring-resources.xml"
        })
//@ComponentScan(basePackages = {
//        "eightysix250kaffe.rest"
//})
public class SpringApplicationContext {

}
