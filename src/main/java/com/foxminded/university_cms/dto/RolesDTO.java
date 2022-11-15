package com.foxminded.university_cms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class RolesDTO {
    private Set<String> roles = new HashSet<>();
}
