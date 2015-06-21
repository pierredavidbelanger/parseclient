package ca.pjer.parseclient.support;

import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Utils {

	public static String queryParamSpaceEncoded(String input) {
		try {
			// Here be dragons!
			// This will surely break soon enough.
			// URLEncoder will encode ' ' as '+', but we want ' ' as '%20'
			return URLEncoder.encode(input, "UTF-8").replace("+", "%20");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T extends Object> MessageBodyReader<T> findMessageBodyReader(Configuration configuration, MediaType mediaType) {
		//noinspection unchecked
		MessageBodyReader<T> contractImpl = findContractImpl(MessageBodyReader.class, configuration);
		if (!contractImpl.isReadable(Object.class, null, null, mediaType))
			throw new RuntimeException("Unable to find a " + mediaType + " capable MessageBodyReader from the JAX-RS Client Configuration.");
		return contractImpl;
	}

	public static <T extends Object> MessageBodyWriter<T> findMessageBodyWriter(Configuration configuration, MediaType mediaType) {
		//noinspection unchecked
		MessageBodyWriter<T> contractImpl = findContractImpl(MessageBodyWriter.class, configuration);
		if (!contractImpl.isWriteable(Object.class, null, null, mediaType))
			throw new RuntimeException("Unable to find a " + mediaType + " capable MessageBodyWriter from the JAX-RS Client Configuration.");
		return contractImpl;
	}

	protected static <T> T findContractImpl(Class<T> type, Configuration configuration) {
		T contractImpl = null;
		searchForContractImpl:
		for (Object component : configuration.getInstances()) {
			for (Class<?> contractClass : configuration.getContracts(component.getClass()).keySet()) {
				if (type.isAssignableFrom(contractClass)) {
					//noinspection unchecked
					contractImpl = (T) component;
					break searchForContractImpl;
				}
			}
		}
		if (contractImpl == null)
			throw new RuntimeException("Unable to find a " + type + " impl from the JAX-RS Client Configuration.");
		return contractImpl;
	}
}
