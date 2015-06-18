package ca.pjer.parseclient.support.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public interface ParseObjectHeaderMixin {

	@JsonIgnore
	String getObjectId();

	@JsonProperty
	void setObjectId(String objectId);

	@JsonIgnore
	Date getCreatedAt();

	@JsonProperty
	void setCreatedAt(Date createdAt);

	@JsonIgnore
	Date getUpdatedAt();

	@JsonProperty
	void setUpdatedAt(Date updatedAt);

}
