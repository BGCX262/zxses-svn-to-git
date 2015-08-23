package com.lcweb.commons;

import java.util.List;

public class Pager {
	private int totalRows = 0;
	private int pageSize = 4;
	private int currentPage = 1;
	private int totalPages = 0;
	private int startRow = 0;
	private List elements;
	private int pageSizeCurrentPage;

	public Pager() {

	}

	public Pager(int totalRows) {
		this.totalRows = totalRows;

		totalPages = totalRows == 0 ? 1 : totalRows / pageSize;
		//-------------------------------------------
		int mod = totalRows % pageSize;
		if (mod > 0) {
			totalPages++;
		}
		currentPage = 1;
		startRow = 0;
	}

	public void first() {
		currentPage = 1;
		startRow = 0;
	}

	public void previous() {
		if (currentPage == 1) {
			return;
		}
		currentPage--;
		startRow = (currentPage - 1) * pageSize;
	}

	public void next() {
		if (currentPage < totalPages) {
			currentPage++;
		}
		startRow = (currentPage - 1) * pageSize;
	}

	public void last() {
		currentPage = totalPages;
		startRow = (currentPage - 1) * pageSize;
	}

	public void refresh(int currentPage) {
		this.currentPage = currentPage;
		if (currentPage > totalPages)
			last();
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public List getElements() {
		return elements;
	}

	public void setElements(List elements) {
		this.elements = elements;
	}

	public int getPageSizeCurrentPage() {
		// return pageSize*currentPage;
		return pageSize * (currentPage - 1);
	}

	public void setPageSizeCurrentPage() {
		//  this.pageSizeCurrentPage=pageSize*currentPage;
		this.pageSizeCurrentPage = pageSize * (currentPage - 1);
	}

}