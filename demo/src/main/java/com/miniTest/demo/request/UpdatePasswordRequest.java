package com.miniTest.demo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest {
    @NotBlank(message = "Password cannot be left blank")
    private String oldPassword;
    @Size(min = 8, max = 21, message = "Password must be between 8 and 21 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*()-_=+[{]};:',<.>/?]).*$",
            message = "Invalid password. Password must contain at least one uppercase letter, one lowercase letter, one special character")
    private String newPassword;
}
