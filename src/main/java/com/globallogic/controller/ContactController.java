/*
 * Contact Controller Class
 * 
 * @author Kiran Kumar G S
 * @CreatedDate 06/11/2022
 * */
package com.globallogic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.dto.ContactDto;
import com.globallogic.payload.ListResponse;
import com.globallogic.service.ContactService;
import com.globallogic.utils.AppConstants;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {

	@Autowired
	private ContactService contactService;

	/*
	 * Gets All Contacts
	 * */
	@GetMapping
	public ResponseEntity<ListResponse> getAllContacts(
			@RequestParam(value = AppConstants.PAGE_NO, defaultValue = AppConstants.ZERO, required = false) int pageNo,
			@RequestParam(value = AppConstants.PAGE_SIZE, defaultValue = AppConstants.FOUR, required = false) int pageSize,
			@RequestParam(value = AppConstants.SORT_BY, defaultValue = AppConstants.FIRST_NAME, required = false) String sortBy,
			@RequestParam(value = AppConstants.SORT_DIR, defaultValue = AppConstants.ASCENDING, required = false) String sortDir) {

		return new ResponseEntity<>(contactService.getAllContacts(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
	}

	/*
	 * Saves Contact
	 * */
	@PreAuthorize(value = "hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<ContactDto> addContact(@RequestBody ContactDto contactDto) {

		return new ResponseEntity<ContactDto>(contactService.addContact(contactDto), HttpStatus.CREATED);
	}

	/*
	 * Gets a Contact by using ID
	 * */
	@GetMapping("/{id}")
	public ResponseEntity<ContactDto> getContactById(@PathVariable(value = "id") long id) {

		return new ResponseEntity<ContactDto>(contactService.getContactById(id), HttpStatus.OK);

	}

	/*
	 * Updates a Contact by using ID
	 * */
	@PreAuthorize(value = "hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<ContactDto> updateContactById(@PathVariable(value = "id") long id,
			@RequestBody ContactDto contactDto) {
		return new ResponseEntity<ContactDto>(contactService.updateContactById(id, contactDto), HttpStatus.OK);
	}

	/*
	 * Deletes a Contact by using ID
	 * */
	@PreAuthorize(value = "hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteContactbyId(@PathVariable(value = "id") long id) {

		contactService.deleteContactById(id);

		return new ResponseEntity<String>("Delete Successfully", HttpStatus.OK);

	}

	/*
	 * Counts number of Contacts
	 * */
	@GetMapping("/count")
	public ResponseEntity<String> getNumberOfContacts() {
		return new ResponseEntity<>(contactService.getNumberOfContacts(), HttpStatus.OK);
	}

}
