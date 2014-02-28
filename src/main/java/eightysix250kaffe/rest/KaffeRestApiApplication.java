package eightysix250kaffe.rest;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import eightysix250kaffe.rest.configuration.KaffeRestApiConfiguration;
import eightysix250kaffe.rest.spring.SpringConfigurationHelper;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.reflections.ReflectionUtils;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.LinkedHashSet;

import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
public class KaffeRestApiApplication extends Application<KaffeRestApiConfiguration> {

	public static void main(String[] args) throws Exception {
		if (args == null || args.length == 0) {
			args = new String[] {
					"server", "config.yml"
			};
		}
		new KaffeRestApiApplication().run(args);
	}

    @Override
    public void initialize(Bootstrap<KaffeRestApiConfiguration> bootstrap) {
        KaffeRestApiApplication.log.info(getClass().getName() + " initializing");
        bootstrap.getObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        bootstrap.getObjectMapper().setDateFormat(new ISO8601DateFormat());
    }

    @Override
    public void run(KaffeRestApiConfiguration configuration, Environment environment) throws Exception {
        AnnotationConfigWebApplicationContext applicationContext = SpringConfigurationHelper.initializeSpring(configuration, environment, populateSpringApplicationContextClasses(configuration));

        // Servlet filters
//        environment.servlets().addFilter("springSecurityFilterChain", DelegatingFilterProxy.class)
//                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

        // Enables JSON pretty print
        environment.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }

    private LinkedHashSet<Class<?>> populateSpringApplicationContextClasses(KaffeRestApiConfiguration configuration) {
        LinkedHashSet<Class<?>> springApplicationContextClasses = Sets.newLinkedHashSet();
        for (Class ctx : configuration.getSpringApplicationContextClasses()) {
            checkNotNull(ctx);
            Preconditions.checkArgument(ReflectionUtils.withAnnotation(org.springframework.context.annotation.Configuration.class).apply(ctx), "Invalid spring app context " + ctx + ". Must be annotated with org.springframework.context.annotation.Configuration.");
            springApplicationContextClasses.add(ctx);
        }
        return springApplicationContextClasses;
    }

}
