package com.sudhirt.samples.pagination.helper;

import com.sudhirt.samples.pagination.domain.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

public final class PersonDataHelper {

    public static List<Person> createPeople(int count) {
        List<Person> people = new ArrayList<>();
        IntStream.range(0, count)
                .peek(i -> people.add(Person.builder()
                        .firstName("Firstname_" + i)
                        .lastName("Lastname_" + i)
                        .email("email_"+ i +"@mail.com")
                        .dateOfBirth(new Date())
                        .build()))
                .count();
        return people;
    }
}
