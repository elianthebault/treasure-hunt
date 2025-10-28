package com.treasure_hunt.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String phoneNumber;
    private String email;
    private String firstname;
    private String lastname;
    private String nickname;
    private String profile;
    private String password;
}
