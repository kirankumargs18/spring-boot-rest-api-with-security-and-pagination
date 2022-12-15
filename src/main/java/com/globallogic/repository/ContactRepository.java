/*
 * Contact Repository
 * 
 * @author Kiran Kumar G S
 * @creationDate 06/11/2022
 * */
package com.globallogic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globallogic.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
