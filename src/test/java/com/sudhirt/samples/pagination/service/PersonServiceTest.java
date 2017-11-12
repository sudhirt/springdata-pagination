package com.sudhirt.samples.pagination.service;

import com.sudhirt.samples.pagination.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    public void savePersonWithNoDetails() {
        Person p = Person.builder()
                .build();
        Throwable thrown = catchThrowable(() -> personService.save(p));
        assertThat(thrown).hasRootCauseInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void savePersonWithNoFirstName() {
        Person p = Person.builder()
                .lastName("Lastname")
                .email("test@test.com")
                .dateOfBirth(new Date())
                .build();
        Throwable thrown = catchThrowable(() -> personService.save(p));
        assertThat(thrown).hasRootCauseInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void savePersonWithNoLastName() {
        Person p = Person.builder()
                .firstName("Firstname")
                .email("test@test.com")
                .dateOfBirth(new Date())
                .build();
        Throwable thrown = catchThrowable(() -> personService.save(p));
        assertThat(thrown).hasRootCauseInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void savePersonWithNoEmail() {
        Person p = Person.builder()
                .firstName("Firstname")
                .lastName("Lastname")
                .dateOfBirth(new Date())
                .build();
        Throwable thrown = catchThrowable(() -> personService.save(p));
        assertThat(thrown).hasRootCauseInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void savePersonWithInvalidEmail() {
        Person p = Person.builder()
                .firstName("Firstname")
                .lastName("Lastname")
                .email("invalid_email")
                .dateOfBirth(new Date())
                .build();
        Throwable thrown = catchThrowable(() -> personService.save(p));
        assertThat(thrown).hasRootCauseInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void savePersonWithNoDateofBirth() {
        Person p = Person.builder()
                .firstName("Firstname")
                .lastName("Lastname")
                .email("email@mail.com")
                .build();
        Throwable thrown = catchThrowable(() -> personService.save(p));
        assertThat(thrown).hasRootCauseInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void save() {
        Person p = Person.builder()
                .firstName("Firstname")
                .lastName("Lastname")
                .email("email@mail.com")
                .dateOfBirth(new Date())
                .build();
        p = personService.save(p);
        Optional<Person> dbPerson = personService.get(p.getId());
        assertThat(dbPerson).isPresent();
        assertThat(dbPerson.get()
                .getFirstName()).isEqualTo(p.getFirstName());
        assertThat(dbPerson.get()
                .getLastName()).isEqualTo(p.getLastName());
        assertThat(dbPerson.get()
                .getEmail()).isEqualTo(p.getEmail());
        assertThat(dbPerson.get()
                .getDateOfBirth()
                .getTime()).isEqualTo(p.getDateOfBirth()
                .getTime());
    }
}
