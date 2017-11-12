package com.sudhirt.samples.pagination.service;

import com.sudhirt.samples.pagination.criteria.PersonSearchCriteria;
import com.sudhirt.samples.pagination.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/person/search/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/person/search/after.sql")
public class PersonSearchTest {

    @Autowired
    private PersonService personService;

    @Test
    public void getById() {
        Optional<Person> p = personService.get(10L);
        assertThat(p).isPresent();
    }

    @Test
    public void searchAll() {
        PersonSearchCriteria criteria = PersonSearchCriteria.builder()
                .build();
        Page<Person> resultsPage = personService.find(criteria);
        assertThat(resultsPage.getTotalElements()).isEqualTo(15);
        assertThat(resultsPage.getContent()
                .size()).isEqualTo(15);
    }

    @Test
    public void searchAllWithPagination() {
        PersonSearchCriteria criteria = PersonSearchCriteria.builder()
                .pageNumber(0)
                .pageSize(5)
                .build();
        Page<Person> resultsPage = personService.find(criteria);
        assertThat(resultsPage.getTotalElements()).isEqualTo(15);
        assertThat(resultsPage.getContent()
                .size()).isEqualTo(5);
        assertThat(resultsPage.getTotalPages()).isEqualTo(3);
    }

    @Test
    public void searchByFirstName() {
        PersonSearchCriteria criteria = PersonSearchCriteria.builder()
                .firstName("ar")
                .build();
        Page<Person> people = personService.find(criteria);
        assertThat(people.getContent()
                .isEmpty()).isFalse();
        people.stream()
                .forEach(p -> assertThat(p.getFirstName()
                        .contains("ar")).isTrue());
    }

    @Test
    public void searchByFirstNameWithPagination() {
        PersonSearchCriteria criteria = PersonSearchCriteria.builder()
                .firstName("ar")
                .pageNumber(0)
                .pageSize(2)
                .build();
        Page<Person> people = personService.find(criteria);
        assertThat(people.getContent()
                .isEmpty()).isFalse();
        assertThat(people.getTotalPages()).isEqualTo(3);
        assertThat(people.getNumberOfElements()).isEqualTo(2);
    }

    @Test
    public void searchByFirstNameAndLastName() {
        PersonSearchCriteria criteria = PersonSearchCriteria.builder()
                .firstName("ar")
                .lastName("oka")
                .build();
        Page<Person> people = personService.find(criteria);
        assertThat(people.getContent()
                .isEmpty()).isFalse();
        people.stream()
                .forEach(p -> {
                    assertThat(p.getFirstName()
                            .contains("ar")).isTrue();
                    assertThat(p.getLastName()
                            .contains("oka")).isTrue();
                });
    }
}
