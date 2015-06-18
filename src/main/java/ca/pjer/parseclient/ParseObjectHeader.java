package ca.pjer.parseclient;

import java.util.Date;

public class ParseObjectHeader implements ParseObjectCreation, ParseObjectUpdate {

	private String objectId;
	private Date createdAt;
	private Date updatedAt;

	public String getObjectId() {
		return objectId;
	}

	void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
