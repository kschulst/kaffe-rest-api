package eightysix250kaffe.rest.spring;

import com.codahale.metrics.health.HealthCheck;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import io.dropwizard.Configuration;
import io.dropwizard.servlets.tasks.Task;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;
import java.util.LinkedHashSet;
import java.util.Map;

public class SpringConfigurationHelper {

    private static final Logger log = LoggerFactory.getLogger(SpringConfigurationHelper.class);

    /**
     *
     * Creates the application context, register all relevant beans in dropwizard (healthchecks, resources, tasks, providers) and add the
     * Spring context loader listener to the embedded Jetty.
     *
     * @param configuration
     * @param environment
     * @param springApplicationContextClasses
     * @return
     */
    public static AnnotationConfigWebApplicationContext initializeSpring(Configuration configuration, Environment environment, LinkedHashSet<Class<?>> springApplicationContextClasses) {
        log.info("Initializing Dropwizard Spring Support");
        AnnotationConfigWebApplicationContext ctx = startSpring(configuration, springApplicationContextClasses);

        //now that Spring is started, let's get all the beans that matter into DropWizard
        registerHealthChecks(ctx, environment);
        registerResources(ctx, environment);
        registerTasks(ctx, environment);
        registerJaxRsProviders(ctx, environment);

        //last, but not least, link Spring to the embedded Jetty in Dropwizard
        environment.servlets().addServletListeners(new SpringContextLoaderListener(ctx));

        return ctx;
    }

    private static AnnotationConfigWebApplicationContext startSpring(Configuration configuration, LinkedHashSet<Class<?>> springApplicationContextClasses) {
        //init Spring context
        //before we init the app context, we have to create a parent context with all the config objects others rely on to get initialized
        AnnotationConfigWebApplicationContext parent = new AnnotationConfigWebApplicationContext();
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        parent.refresh();
        parent.getBeanFactory().registerSingleton("configuration", configuration);
        parent.registerShutdownHook();
        parent.start();

        //the real main app context has a link to the parent context
        ctx.setParent(parent);
        for (Class<?> springConfigClass : springApplicationContextClasses) {
            ctx.register(springConfigClass);
        }

        ctx.setConfigLocations(configLocations(springApplicationContextClasses));
        ctx.refresh();
        ctx.registerShutdownHook();
        ctx.start();

        return ctx;
    }

    private static String[] configLocations(LinkedHashSet<Class<?>> springApplicationContextClasses) {
        return Iterables.toArray(
                Iterables.transform(springApplicationContextClasses, new Function<Class<?>, String>() {
                    @Override
					public String apply(Class<?> springConfig) {
                        return springConfig.getPackage().getName();
                    }
                }),
                String.class
        );
    }

    private static void registerHealthChecks(ApplicationContext ctx, Environment environment) {
        Map<String, HealthCheck> healthChecks = ctx.getBeansOfType(HealthCheck.class);
        for(Map.Entry<String,HealthCheck> entry : healthChecks.entrySet()) {
            log.info("Registering DW health check: " + entry.getKey());
            environment.healthChecks().register(entry.getKey(), entry.getValue());
        }
    }

    private static void registerResources(ApplicationContext ctx, Environment environment) {
        Map<String, Object> resources = ctx.getBeansWithAnnotation(Path.class);
        for(Map.Entry<String,Object> entry : resources.entrySet()) {
            log.info("Registering DW resource: " + entry.getKey());
            environment.jersey().register(entry.getValue());
        }
    }

    private static void registerTasks(ApplicationContext ctx, Environment environment) {
        Map<String, Task> tasks = ctx.getBeansOfType(Task.class);
        for(Map.Entry<String,Task> entry : tasks.entrySet()) {
            log.info("Registering DW task: " + entry.getKey());
            environment.admin().addTask(entry.getValue());
        }
    }

    private static void registerJaxRsProviders(ApplicationContext ctx, Environment environment) {
        Map<String, Object> providers = ctx.getBeansWithAnnotation(Provider.class);
        for(Map.Entry<String,Object> entry : providers.entrySet()) {
            log.info("Registering DW jax-rs provider: " + entry.getKey());
            environment.jersey().register(entry.getValue());
        }
    }

}
