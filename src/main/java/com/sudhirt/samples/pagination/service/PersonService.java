package com.sudhirt.samples.pagination.service;

import com.sudhirt.samples.pagination.criteria.PersonSearchCriteria;
import com.sudhirt.samples.pagination.domain.Person;
import com.sudhirt.samples.pagination.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;

    PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public Person save(@Valid Person person) {
        return personRepository.save(person);
    }

    @Transactional
    public List<Person> save(@Valid List<Person> people) {
        return personRepository.saveAll(people);
    }

    @Transactional(readOnly = true)
    public Optional<Person> get(Long id) {
        Example<Person> personCriteria = Example.of(Person.builder()
                .id(id)
                .build());
        return personRepository.findOne(personCriteria);
    }

    @Transactional(readOnly = true)
    public Page<Person> find(PersonSearchCriteria personSearchCriteria) {
        Pageable pageable = pageable(personSearchCriteria);
        if (pageable == null) {
            List<Person> people = personRepository.findAll(example(personSearchCriteria));
            return new PageImpl<Person>(people, PageRequest.of(0, people.size()), people.size());
        } else {
            return personRepository.findAll(example(personSearchCriteria), pageable);
        }
    }

    private ExampleMatcher matcher(PersonSearchCriteria criteria) {
        ExampleMatcher personMatcher = ExampleMatcher.matching();
        if (criteria.getFirstName() != null && !criteria.getFirstName()
                .isEmpty()) {
            personMatcher = personMatcher.withMatcher("firstName", contains());
        }
        if (criteria.getLastName() != null && !criteria.getLastName()
                .isEmpty()) {
            personMatcher = personMatcher.withMatcher("lastName", contains());
        }
        if (criteria.getEmail() != null && !criteria.getEmail()
                .isEmpty()) {
            personMatcher = personMatcher.withMatcher("email", exact());
        }
        if (criteria.getDateOfBirth() != null) {
            personMatcher = personMatcher.withMatcher("dateOfBirth", exact());
        }
        return personMatcher;
    }

    private Example<Person> example(PersonSearchCriteria criteria) {
        Person person = new Person();
        if (criteria.getFirstName() != null && !criteria.getFirstName()
                .isEmpty()) {
            person.setFirstName(criteria.getFirstName());
        }
        if (criteria.getLastName() != null && !criteria.getLastName()
                .isEmpty()) {
            person.setLastName(criteria.getLastName());
        }
        if (criteria.getEmail() != null && !criteria.getEmail()
                .isEmpty()) {
            person.setEmail(criteria.getEmail());
        }
        if (criteria.getDateOfBirth() != null) {
            person.setDateOfBirth(criteria.getDateOfBirth());
        }
        return Example.of(person, matcher(criteria));
    }

    private Pageable pageable(PersonSearchCriteria criteria) {
        if (criteria.getPageNumber() != null && criteria.getPageSize() != null) {
            return PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        }
        return null;
    }
}
