package com.bfcai.topjob.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDTO {

    @NonNull
    private String email;
    @NotNull
    private String password;
}
