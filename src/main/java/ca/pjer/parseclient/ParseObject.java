package ca.pjer.parseclient;

import java.util.*;

public class ParseObject extends ParseObjectHeader {

	private Map<String, Object> properties;
	private ParseACL ACL;

	Map<String, Object> getProperties() {
		return properties;
	}

	public Object get(String name) {
		if (properties == null) return null;
		return properties.get(name);
	}

	public Object set(String name, Object value) {
		if (properties == null) properties = new HashMap<String, Object>(10);
		return properties.put(name, value);
	}

	public ParseACL getACL() {
		return ACL;
	}

	public void setACL(ParseACL ACL) {
		this.ACL = ACL;
	}

	public void counterIncrement(String key, Number amount) {
		setFieldOperation(key, "Increment", "amount", amount);
	}

	public void arrayAdd(String key, Object... objects) {
		setFieldOperation(key, "Add", "objects", Arrays.asList(objects));
	}

	public void arrayAddUnique(String key, Object... objects) {
		setFieldOperation(key, "AddUnique", "objects", Arrays.asList(objects));
	}

	public void arrayRemove(String key, Object... objects) {
		setFieldOperation(key, "Remove", "objects", Arrays.asList(objects));
	}

	protected void setFieldOperation(String key, String opName, String argName, Object argValue) {
		Map<String, Object> op;
		Object currentOp = get(key);
		if (currentOp == null
				|| !(currentOp instanceof Map)
				|| !((Map) currentOp).containsKey("__op")
				|| !((Map) currentOp).get("__op").equals(opName)) {
			op = new HashMap<String, Object>(4);
			op.put("__op", opName);
		} else {
			//noinspection unchecked
			op = (Map<String, Object>) currentOp;
		}
		Object currentArgValue = op.get(argName);
		if (currentArgValue instanceof List && argValue instanceof List) {
			List argList = (List) currentArgValue;
			argList = new ArrayList<Object>(argList);
			//noinspection unchecked
			argList.addAll((List) argValue);
			op.put(argName, argList);
		} else if (currentArgValue instanceof Number && argValue instanceof Number) {
			Number argNumber = (Number) currentArgValue;
			argNumber = argNumber.doubleValue() + ((Number) argValue).doubleValue();
			op.put(argName, argNumber);
		} else {
			op.put(argName, argValue);
		}
		set(key, op);
	}
}
