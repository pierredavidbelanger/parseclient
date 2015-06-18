package ca.pjer.parseclient;

import java.util.ArrayList;
import java.util.List;

class BatchImpl implements Batch {

	private final List<BatchRequestImpl> requests;

	BatchImpl() {
		requests = new ArrayList<BatchRequestImpl>(50);
	}

	public List<BatchRequestImpl> getRequests() {
		return requests;
	}
}
