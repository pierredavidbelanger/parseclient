package ca.pjer.parseclient.support;

import ca.pjer.parseclient.support.jackson.ParseClientModule;
import ca.pjer.parseclient.support.jersey.ParseClientWithErrorResponseFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.filter.LoggingFilter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleJerseyWithJacksonConfiguration extends ClientConfig {

	public SimpleJerseyWithJacksonConfiguration() {
		this(new ObjectMapper());
	}

	public SimpleJerseyWithJacksonConfiguration(ObjectMapper objectMapper) {

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.registerModule(new ParseClientModule());
		register(new JacksonJaxbJsonProvider(objectMapper, null));

		property(ClientProperties.ASYNC_THREADPOOL_SIZE, 1);

		register(new ParseClientWithErrorResponseFilter(objectMapper));

		Logger logger = Logger.getLogger("ca.pjer.parseclient");
		if (logger.isLoggable(Level.INFO))
			register(new LoggingFilter(logger, true));
	}
}
