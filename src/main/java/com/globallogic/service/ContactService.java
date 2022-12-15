/*
 * Contact Service Interface
 * 
 * @author Kiran Kumar G S
 * @creationDate 06/11/2022
 * */
package com.globallogic.service;

import com.globallogic.dto.ContactDto;
import com.globallogic.payload.ListResponse;

public interface ContactService {

	ListResponse getAllContacts(int pageNo,int pageSize,String sortBy,String sortDir);

	ContactDto getContactById(long id);

	ContactDto addContact(ContactDto contactDto);
	
	ContactDto updateContactById(long id, ContactDto contactDto);
	
	void deleteContactById(long id);
	
	String getNumberOfContacts();

}
