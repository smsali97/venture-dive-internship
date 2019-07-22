package com.crm.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.*;

@RepositoryRestResource
public interface ContactRepository extends CrudRepository<Contact, Long> {

}
