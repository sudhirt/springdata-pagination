package com.sudhirt.samples.pagination.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PersonSearchCriteria extends AbstractPaginationCriteria {

    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;

    @Builder
    public PersonSearchCriteria(Integer pageNumber, Integer pageSize, String firstName, String lastName, String email, Date date) {
        super(pageNumber, pageSize);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
}
