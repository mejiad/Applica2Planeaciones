package com.evoltech.register.model.jpa;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class User {

    private String email;
    private String password;
}
