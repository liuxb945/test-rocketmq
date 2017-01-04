package com.abcd.test.rocketmq.unique;

public enum IndexChangeType {
	Brand_Update(11,"brand","update"),
	Brand_Delete(12,"brand","delete"),
	Goods_Update(21,"goods","update"),
	Goods_Delete(22,"goods","delete"),
	Category_Update(31,"category","update"),
	Category_Delete(32,"category","delete"),
	Type_Update(41,"type","update"),
	Type_Delete(42,"type","delete");
	private IndexChangeType(int typeId,String typeName,String action){
		this.typeId=typeId;
		this.typeName=typeName;
		this.action=action;
	}
	private int typeId;
	private String typeName;
	private String action;
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

}
