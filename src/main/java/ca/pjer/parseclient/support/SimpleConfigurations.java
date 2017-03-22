package ca.pjer.parseclient.support;

import ca.pjer.parseclient.support.jackson.ParseClientModule;
import ca.pjer.parseclient.support.jersey.ParseClientWithErrorResponseFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.core.Configuration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleConfigurations {

	public static Configuration jerseyWithJackson() {
		return jerseyWithJackson(new ObjectMapper());
	}

	public static Configuration jerseyWithJackson(ObjectMapper objectMapper) {

		ClientConfig clientConfig = new ClientConfig();

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.registerModule(new ParseClientModule());
		clientConfig.register(new JacksonJaxbJsonProvider(objectMapper, null));

		clientConfig.property(ClientProperties.ASYNC_THREADPOOL_SIZE, 1);

		clientConfig.register(new ParseClientWithErrorResponseFilter());

		Logger logger = Logger.getLogger("ca.pjer.parseclient");
		if (logger.isLoggable(Level.INFO))
			clientConfig.register(new LoggingFeature(logger, Level.INFO,
					LoggingFeature.DEFAULT_VERBOSITY, LoggingFeature.DEFAULT_MAX_ENTITY_SIZE));

		return clientConfig;
	}
}
