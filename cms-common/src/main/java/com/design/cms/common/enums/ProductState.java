package com.design.cms.common.enums;

public enum ProductState{
	SALE(10,"发售"),
	PRE_SALE(20,"预售");
	private Integer typeCode;
	private String typeDes;
	private ProductState(Integer typeCode, String typeDes) {
		this.typeCode = typeCode;
		this.typeDes = typeDes;
	}
	public Integer getTypeCode() {
		return typeCode;
	}
	public String getTypeDes() {
		return typeDes;
	}
	public static boolean Valid(Integer typeCode){
		for(ProductState tmp:values()){
			if(tmp.typeCode.equals(typeCode)){
				return true;
			}
		}
		return false;
	}
}
