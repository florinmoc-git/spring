package com.hospital.patientadmin.proxy;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "com.mirth.connect.donkey.model.message.Status", "long" })
@Generated("jsonschema2pojo")
public class Entry {

	@JsonProperty("com.mirth.connect.donkey.model.message.Status")
	private String comMirthConnectDonkeyModelMessageStatus;
	@JsonProperty("long")
	private Integer _long;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("com.mirth.connect.donkey.model.message.Status")
	public String getComMirthConnectDonkeyModelMessageStatus() {
		return comMirthConnectDonkeyModelMessageStatus;
	}

	@JsonProperty("com.mirth.connect.donkey.model.message.Status")
	public void setComMirthConnectDonkeyModelMessageStatus(String comMirthConnectDonkeyModelMessageStatus) {
		this.comMirthConnectDonkeyModelMessageStatus = comMirthConnectDonkeyModelMessageStatus;
	}

	@JsonProperty("long")
	public Integer getLong() {
		return _long;
	}

	@JsonProperty("long")
	public void setLong(Integer _long) {
		this._long = _long;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Entry.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
				.append('[');
		sb.append("comMirthConnectDonkeyModelMessageStatus");
		sb.append('=');
		sb.append(((this.comMirthConnectDonkeyModelMessageStatus == null) ? "<null>"
				: this.comMirthConnectDonkeyModelMessageStatus));
		sb.append(',');
		sb.append("_long");
		sb.append('=');
		sb.append(((this._long == null) ? "<null>" : this._long));
		sb.append(',');
		sb.append("additionalProperties");
		sb.append('=');
		sb.append(((this.additionalProperties == null) ? "<null>" : this.additionalProperties));
		sb.append(',');
		if (sb.charAt((sb.length() - 1)) == ',') {
			sb.setCharAt((sb.length() - 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

}