/*
 * Customized Response for findAll() method of Contact Repository
 * 
 * @author Kiran Kumar G S
 * @creationDate 06/11/2022
 * */
package com.globallogic.payload;

import java.util.List;

import com.globallogic.dto.ContactDto;

public class ListResponse {

	private List<ContactDto> contactList;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;

	public ListResponse() {
		super();
	}

	public ListResponse(List<ContactDto> contactList, int pageNo, int pageSize, long totalElements, int totalPages,
			boolean last) {
		super();
		this.contactList = contactList;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.last = last;
	}

	public List<ContactDto> getContactList() {
		return contactList;
	}

	public void setContactList(List<ContactDto> contactList) {
		this.contactList = contactList;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	@Override
	public String toString() {
		return "ListResponse [contactList=" + contactList + ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", totalElements=" + totalElements + ", totalPages=" + totalPages + ", last=" + last + "]";
	}

}
