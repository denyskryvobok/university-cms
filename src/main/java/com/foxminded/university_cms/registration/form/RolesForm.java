package com.foxminded.university_cms.registration.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class RolesForm {
    private Set<String> roles = new HashSet<>();
}
