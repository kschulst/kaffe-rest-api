package eightysix250kaffe.rest;

import static com.google.common.base.Preconditions.checkNotNull;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.LinkedHashSet;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.reflections.ReflectionUtils;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.spring.DBIFactoryBean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

import eightysix250kaffe.rest.configuration.KaffeRestApiConfiguration;
import eightysix250kaffe.rest.repositories.ParticipantDao;
import eightysix250kaffe.rest.spring.SpringConfigurationHelper;

@Slf4j
public class KaffeRestApiApplication extends Application<KaffeRestApiConfiguration> {

	public static void main(String[] args) throws Exception {
		if (args == null || args.length == 0) {
			args = new String[] { "server", "config.yml" };
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
		AnnotationConfigWebApplicationContext applicationContext = SpringConfigurationHelper.initializeSpring(
				configuration, environment, populateSpringApplicationContextClasses(configuration));

		DBI dbi = (DBI) applicationContext.getBean("dbi", DBI.class);

		ParticipantDao participantDao = dbi.open(ParticipantDao.class);
		participantDao.createTable();
		participantDao.insert("Q7E", "Mats", "q7e@storebrand.no", "92837465", "Mats latte");
		participantDao.insert("007", "Kenneth", "007@storebrand.no", "92837465", "Dark black death");
		participantDao.close();

		// Servlet filters
		// environment.servlets().addFilter("springSecurityFilterChain",
		// DelegatingFilterProxy.class)
		// .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true,
		// "/*");

		// Enables JSON pretty print
		environment.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

	}

	private LinkedHashSet<Class<?>> populateSpringApplicationContextClasses(KaffeRestApiConfiguration configuration) {
		LinkedHashSet<Class<?>> springApplicationContextClasses = Sets.newLinkedHashSet();
		for (Class<?> ctx : configuration.getSpringApplicationContextClasses()) {
			checkNotNull(ctx);
			Preconditions.checkArgument(
					ReflectionUtils.withAnnotation(org.springframework.context.annotation.Configuration.class).apply(
							ctx), "Invalid spring app context " + ctx
							+ ". Must be annotated with org.springframework.context.annotation.Configuration.");
			springApplicationContextClasses.add(ctx);
		}
		return springApplicationContextClasses;
	}

}
