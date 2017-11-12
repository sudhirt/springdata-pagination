package com.sudhirt.samples.pagination.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String firstName;
    @NotBlank
    @Column(nullable = false)
    private String lastName;
    @NotNull
    @Column(nullable = false)
    private Date dateOfBirth;
    @Email
    @NotBlank
    @Column(nullable = false)
    private String email;
}
