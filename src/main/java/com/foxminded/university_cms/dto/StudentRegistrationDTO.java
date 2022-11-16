package com.foxminded.university_cms.dto;

import com.foxminded.university_cms.entity.Student;
import com.foxminded.university_cms.entity.security.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegistrationDTO {
    @NotBlank(message = "Username is required")
    @Size(max = 30, message = "Username must be less than 30 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(max = 20, message = "Password must be less than 20 characters")
    private String password;

    @NotBlank(message = "First name is required")
    @Size(max = 10, message = "Firs name must be less than 10 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 10, message = "Last name must be less than 10 characters")
    private String lastName;

    @NotBlank(message = "Street is required")
    @Size(max = 40, message = "Street name must be less than 40 characters")
    private String street;

    @NotBlank(message = "City is required")
    @Size(max = 20, message = "City name must be less than 20 characters")
    private String city;

    @NotBlank(message = "Zip is required")
    @Size(max = 5, min = 5, message = "Zip must contains five digits")
    private String zip;

    @NotBlank(message = "Country is required")
    @Size(max = 30, message = "Country name must be less than 30 characters")
    private String country;

    @NotBlank(message = "Student card is required")
    @Size(max = 7, min = 7, message = "Student card must be 7 digits long")
    private String studentCard;

    public User toUser() {
        return new User(username, password);
    }

    public Student toStudent() {
        return new Student(firstName, lastName, street, city, zip, country, studentCard);
    }

}
