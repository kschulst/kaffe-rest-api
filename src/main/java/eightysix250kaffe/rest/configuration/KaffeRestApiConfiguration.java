package eightysix250kaffe.rest.configuration;

import eightysix250kaffe.rest.spring.SpringApplicationContext;
import io.dropwizard.Configuration;

public class KaffeRestApiConfiguration extends Configuration {

    public Class<?>[] getSpringApplicationContextClasses() {
        return new Class[] { SpringApplicationContext.class };
    }
}
