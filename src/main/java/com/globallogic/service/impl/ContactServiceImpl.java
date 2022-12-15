/*
 * Contact Service class
 * 
 * @author Kiran Kumar G S
 * @creationDate 06/11/2022
 * */
package com.globallogic.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.globallogic.dto.ContactDto;
import com.globallogic.entity.Contact;
import com.globallogic.exception.ResourceNotFoundException;
import com.globallogic.payload.ListResponse;
import com.globallogic.repository.ContactRepository;
import com.globallogic.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	/*
	 * @Description : Gets All Contacts From Database
	 * @Params : Takes Page Number, Page Size, SortBy and SortDir as Parameters
	 * @Returns : ListResponse (Customized Response for List)
	 * */
	@Override
	public ListResponse getAllContacts(int pageNo, int pageSize, String sortBy, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Contact> contactPage = contactRepository.findAll(pageable);
		List<Contact> contactList = contactPage.getContent();

		List<ContactDto> contactDtoList = contactList.stream().map(this::convertToDto).collect(Collectors.toList());
		
		ListResponse listResponse=new ListResponse();
		listResponse.setContactList(contactDtoList);
		listResponse.setPageNo(contactPage.getNumber());
		listResponse.setPageSize(contactPage.getSize());
		listResponse.setTotalElements(contactPage.getTotalElements());
		listResponse.setTotalPages(contactPage.getTotalPages());
		listResponse.setLast(contactPage.isLast());

		return listResponse;
	}

	/*
	 * @Description : Gets a Contact from Database by using it's id
	 * @Params : Takes Contact ID as Parameter
	 * @Throws: Resource Not Found Exception
	 * @Returns : Contact 
	 * */
	@Override
	public ContactDto getContactById(long id) {

		Contact contact = contactRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found With ID : " + id));
		return convertToDto(contact);
	}

	/*
	 * @Description : Adds a Contact to Database
	 * @Params : Takes Contact as Parameter
	 * @Returns : Saved Contact
	 * */
	@Override
	public ContactDto addContact(ContactDto contactDto) {

		return convertToDto(contactRepository.save(convertToEntity(contactDto)));
	}

	/*
	 * @Description : Updates a Contact by using it's id
	 * @Params : Takes Contact ID and Contact as Parameters
	 * @Throws: Resource Not Found Exception
	 * @Returns : Updated Contact
	 * */
	@Override
	public ContactDto updateContactById(long id, ContactDto contactDto) {

		Contact contact = contactRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found With ID : " + id));

		contact.setId(contactDto.getId());
		contact.setFirstName(contactDto.getFirstName());
		contact.setLastName(contactDto.getLastName());
		contact.setEmail(contactDto.getEmail());
		contact.setMobilePhone(contactDto.getMobilePhone());

		return convertToDto(contactRepository.save(contact));
	}

	/*
	 * @Description : Deletes a Contact by using it's ID
	 * @Params : Takes Contact ID as Parameter
	 * @Throws: Resource Not Found Exception
	 * */
	@Override
	public void deleteContactById(long id) {

		Contact contact = contactRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found With ID : " + id));
		contactRepository.delete(contact);

	}

	/*
	 * @Description : Gets a Count of Contacts in Database
	 * @Returns : String with Number of Contacts 
	 * */
	@Override
	public String getNumberOfContacts() {

		return "Number of Contacts : " + contactRepository.count();
	}

	/*
	 * Helper Method which Converts Entity to DTO
	 */
	private ContactDto convertToDto(Contact contact) {

		return new ModelMapper().map(contact, ContactDto.class);
	}

	/*
	 * Helper Method which Converts DTO to Entity
	 */
	private Contact convertToEntity(ContactDto contactDto) {

		return new ModelMapper().map(contactDto, Contact.class);
	}

}
