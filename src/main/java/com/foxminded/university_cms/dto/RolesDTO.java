package com.foxminded.university_cms.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RolesDTO {
    private Set<String> roles = new HashSet<>();
}
