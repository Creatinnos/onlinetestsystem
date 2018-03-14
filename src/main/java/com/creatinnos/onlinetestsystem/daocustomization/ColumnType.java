package com.creatinnos.onlinetestsystem.daocustomization;

public enum ColumnType {
	INT(256), BIGINT(256), STRING(256), ENUM(0), LIST(0);
	int maxSize;

	ColumnType(int maxSize) {
		this.maxSize = maxSize;
	}

	public int getMaxSize() {
		return maxSize;

	}
}