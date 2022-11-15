package com.foxminded.university_cms.entity.security;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserRolePK implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;
}
