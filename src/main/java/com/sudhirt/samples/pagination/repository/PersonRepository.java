package com.sudhirt.samples.pagination.repository;

import com.sudhirt.samples.pagination.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "employees")
public interface PersonRepository extends JpaRepository<Person, Long> {

}
