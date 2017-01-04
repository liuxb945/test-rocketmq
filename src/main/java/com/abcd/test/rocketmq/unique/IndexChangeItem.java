package com.abcd.test.rocketmq.unique;

import java.io.Serializable;

public class IndexChangeItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5311702697545964410L;
	private IndexChangeType changeType;
	private Integer changeId;
	public IndexChangeType getChangeType() {
		return changeType;
	}
	public void setChangeType(IndexChangeType changeType) {
		this.changeType = changeType;
	}
	public Integer getChangeId() {
		return changeId;
	}
	public void setChangeId(Integer changeId) {
		this.changeId = changeId;
	}

}
