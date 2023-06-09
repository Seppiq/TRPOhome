package com.example.TRPOhome.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@RequiredArgsConstructor
public class SignupRequest {
    @NotBlank
    private String name;

    private Set<String> role;

    @NotBlank
    private String lastname;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    private String password;
}
