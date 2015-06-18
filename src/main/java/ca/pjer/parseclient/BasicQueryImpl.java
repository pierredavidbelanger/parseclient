package ca.pjer.parseclient;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

class BasicQueryImpl<T extends ParseObject> implements BasicQuery<T> {

	private final ResourcesImpl<T> resources;
	private final MultivaluedMap<String, String> parameters;

	BasicQueryImpl(ResourcesImpl<T> resources) {
		this.resources = resources;
		parameters = new MultivaluedHashMap<String, String>(10);
	}

	BasicQueryImpl(BasicQueryImpl<T> that) {
		this.resources = that.resources;
		this.parameters = new MultivaluedHashMap<String, String>(that.parameters);
	}

	public BasicQuery<T> select(String... keys) {
		return addParameters("keys", keys);
	}

	public BasicQuery<T> where(String where) {
		return setParameters("where", where);
	}

	public BasicQuery<T> ascending(String ascending) {
		return addParameters("order", ascending);
	}

	public BasicQuery<T> descending(String descending) {
		return addParameters("order", "-" + descending);
	}

	public BasicQuery<T> limit(int limit) {
		return setParameters("limit", String.valueOf(limit));
	}

	public BasicQuery<T> skip(int skip) {
		return setParameters("skip", String.valueOf(skip));
	}

	public BasicQuery<T> count() {
		return setParameters("count", "1");
	}

	public QueryResults<T> find() {
		return findOperation().now();
	}

	public Future<QueryResults<T>> findAsync() {
		return findOperation().later();
	}

	public Operation<QueryResults<T>> findOperation() {
		return new OperationImpl<QueryResults<T>>(getInvocationBuilder(),
				OperationImpl.Method.GET, null, getQueryResultsType());
	}

	protected BasicQuery<T> addParameters(String name, String... values) {
		BasicQueryImpl<T> clone = new BasicQueryImpl<T>(this);
		clone.parameters.addAll(name, Arrays.asList(values));
		return clone;
	}

	protected BasicQuery<T> setParameters(String name, String... values) {
		BasicQueryImpl<T> clone = new BasicQueryImpl<T>(this);
		clone.parameters.put(name, Arrays.asList(values));
		return clone;
	}

	protected Invocation.Builder getInvocationBuilder() {
		WebTarget webTarget = resources.getResourceWebTarget();
		for (Map.Entry<String, List<String>> parameter : parameters.entrySet()) {
			String value;
			if (parameter.getValue().size() > 1) {
				StringBuilder sb = new StringBuilder();
				for (String s : parameter.getValue()) {
					if (sb.length() != 0)
						sb.append(",");
					sb.append(s);
				}
				value = sb.toString();
			} else if (parameter.getValue().size() == 1) {
				value = parameter.getValue().get(0);
			} else {
				continue;
			}
			try {
				webTarget = webTarget.queryParam(URLEncoder.encode(parameter.getKey(), "UTF-8"),
						URLEncoder.encode(value, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return webTarget.request().headers(resources.getHeaders());
	}

	protected GenericType<QueryResults<T>> getQueryResultsType() {
		return new GenericType<QueryResults<T>>(new ParameterizedType() {

			public Type[] getActualTypeArguments() {
				return new Type[]{resources.getType()};
			}

			public Type getRawType() {
				return QueryResultsImpl.class;
			}

			public Type getOwnerType() {
				return null;
			}
		});
	}
}
