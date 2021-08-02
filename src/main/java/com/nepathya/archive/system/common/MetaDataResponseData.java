package com.nepathya.archive.system.common;

public class MetaDataResponseData {
	private int pageNumber;
	private int pageSize;
	private int totalPages;

	public MetaDataResponseData() {
		super();
	}

	public MetaDataResponseData(int pageNumber, int pageSize, int totalPages) {
		super();

		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalPages = totalPages;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
}
