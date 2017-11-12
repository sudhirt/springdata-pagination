package com.sudhirt.samples.pagination.criteria;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PersonSearchCriteria extends AbstractPaginationCriteria {

    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;

    /**
     * All arguments constructor.
     * <p>Added manually for builder pattern to work properly for inherited properties as well.</p>
     *
     * @param pageNumber Page number to be fetched
     * @param pageSize Number of results per page
     * @param firstName Person's first name
     * @param lastName Person's last name
     * @param email Person's email
     * @param dateOfBirth Person'e date of birth
     * @see <a href="https://reinhard.codes/2015/09/16/lomboks-builder-annotation-and-inheritance/">@Builder and inheritance</a>
     */
    @Builder
    public PersonSearchCriteria(Integer pageNumber, Integer pageSize, String firstName, String lastName, String email, Date dateOfBirth) {
        super(pageNumber, pageSize);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
}
