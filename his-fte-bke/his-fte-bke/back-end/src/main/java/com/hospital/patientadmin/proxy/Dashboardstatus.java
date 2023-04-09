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
@JsonPropertyOrder({ "channelId", "name", "state", "deployedRevisionDelta", "deployedDate", "statistics",
		"lifetimeStatistics", "childStatuses", "metaDataId", "queueEnabled", "queued", "waitForPrevious",
		"statusType" })
@Generated("jsonschema2pojo")
public class Dashboardstatus {

	@JsonProperty("channelId")
	private String channelId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("state")
	private String state;
	@JsonProperty("deployedRevisionDelta")
	private Integer deployedRevisionDelta;
	@JsonProperty("deployedDate")
	private DeployedDate deployedDate;
	@JsonProperty("statistics")
	private Statistics statistics;
	@JsonProperty("lifetimeStatistics")
	private LifetimeStatistics lifetimeStatistics;
	@JsonProperty("childStatuses")
	private ChildStatuses childStatuses;
	@JsonProperty("metaDataId")
	private Integer metaDataId;
	@JsonProperty("queueEnabled")
	private Boolean queueEnabled;
	@JsonProperty("queued")
	private Integer queued;
	@JsonProperty("waitForPrevious")
	private Boolean waitForPrevious;
	@JsonProperty("statusType")
	private String statusType;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("channelId")
	public String getChannelId() {
		return channelId;
	}

	@JsonProperty("channelId")
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty("deployedRevisionDelta")
	public Integer getDeployedRevisionDelta() {
		return deployedRevisionDelta;
	}

	@JsonProperty("deployedRevisionDelta")
	public void setDeployedRevisionDelta(Integer deployedRevisionDelta) {
		this.deployedRevisionDelta = deployedRevisionDelta;
	}

	@JsonProperty("deployedDate")
	public DeployedDate getDeployedDate() {
		return deployedDate;
	}

	@JsonProperty("deployedDate")
	public void setDeployedDate(DeployedDate deployedDate) {
		this.deployedDate = deployedDate;
	}

	@JsonProperty("statistics")
	public Statistics getStatistics() {
		return statistics;
	}

	@JsonProperty("statistics")
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	@JsonProperty("lifetimeStatistics")
	public LifetimeStatistics getLifetimeStatistics() {
		return lifetimeStatistics;
	}

	@JsonProperty("lifetimeStatistics")
	public void setLifetimeStatistics(LifetimeStatistics lifetimeStatistics) {
		this.lifetimeStatistics = lifetimeStatistics;
	}

	@JsonProperty("childStatuses")
	public ChildStatuses getChildStatuses() {
		return childStatuses;
	}

	@JsonProperty("childStatuses")
	public void setChildStatuses(ChildStatuses childStatuses) {
		this.childStatuses = childStatuses;
	}

	@JsonProperty("metaDataId")
	public Integer getMetaDataId() {
		return metaDataId;
	}

	@JsonProperty("metaDataId")
	public void setMetaDataId(Integer metaDataId) {
		this.metaDataId = metaDataId;
	}

	@JsonProperty("queueEnabled")
	public Boolean getQueueEnabled() {
		return queueEnabled;
	}

	@JsonProperty("queueEnabled")
	public void setQueueEnabled(Boolean queueEnabled) {
		this.queueEnabled = queueEnabled;
	}

	@JsonProperty("queued")
	public Integer getQueued() {
		return queued;
	}

	@JsonProperty("queued")
	public void setQueued(Integer queued) {
		this.queued = queued;
	}

	@JsonProperty("waitForPrevious")
	public Boolean getWaitForPrevious() {
		return waitForPrevious;
	}

	@JsonProperty("waitForPrevious")
	public void setWaitForPrevious(Boolean waitForPrevious) {
		this.waitForPrevious = waitForPrevious;
	}

	@JsonProperty("statusType")
	public String getStatusType() {
		return statusType;
	}

	@JsonProperty("statusType")
	public void setStatusType(String statusType) {
		this.statusType = statusType;
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
		sb.append(Dashboardstatus.class.getName()).append('@')
				.append(Integer.toHexString(System.identityHashCode(this))).append('[');
		sb.append("channelId");
		sb.append('=');
		sb.append(((this.channelId == null) ? "<null>" : this.channelId));
		sb.append(',');
		sb.append("name");
		sb.append('=');
		sb.append(((this.name == null) ? "<null>" : this.name));
		sb.append(',');
		sb.append("state");
		sb.append('=');
		sb.append(((this.state == null) ? "<null>" : this.state));
		sb.append(',');
		sb.append("deployedRevisionDelta");
		sb.append('=');
		sb.append(((this.deployedRevisionDelta == null) ? "<null>" : this.deployedRevisionDelta));
		sb.append(',');
		sb.append("deployedDate");
		sb.append('=');
		sb.append(((this.deployedDate == null) ? "<null>" : this.deployedDate));
		sb.append(',');
		sb.append("statistics");
		sb.append('=');
		sb.append(((this.statistics == null) ? "<null>" : this.statistics));
		sb.append(',');
		sb.append("lifetimeStatistics");
		sb.append('=');
		sb.append(((this.lifetimeStatistics == null) ? "<null>" : this.lifetimeStatistics));
		sb.append(',');
		sb.append("childStatuses");
		sb.append('=');
		sb.append(((this.childStatuses == null) ? "<null>" : this.childStatuses));
		sb.append(',');
		sb.append("metaDataId");
		sb.append('=');
		sb.append(((this.metaDataId == null) ? "<null>" : this.metaDataId));
		sb.append(',');
		sb.append("queueEnabled");
		sb.append('=');
		sb.append(((this.queueEnabled == null) ? "<null>" : this.queueEnabled));
		sb.append(',');
		sb.append("queued");
		sb.append('=');
		sb.append(((this.queued == null) ? "<null>" : this.queued));
		sb.append(',');
		sb.append("waitForPrevious");
		sb.append('=');
		sb.append(((this.waitForPrevious == null) ? "<null>" : this.waitForPrevious));
		sb.append(',');
		sb.append("statusType");
		sb.append('=');
		sb.append(((this.statusType == null) ? "<null>" : this.statusType));
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