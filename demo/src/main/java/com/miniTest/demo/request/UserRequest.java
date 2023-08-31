package com.miniTest.demo.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserRequest {
    @NotBlank(message = "Name cannot be left blank.")
    String name;

    @Email(regexp = "^[A-Za-z0-9][a-zA-Z0-9\\.]{6,32}@gmail\\.com$", message = "Email không hợp lệ.")
    String email;

    @NotBlank(message = "Phone number cannot be left blank.")
    String phone;

    @NotBlank(message = "Address cannot be left blank.")
    String address;

    @Size(min = 8, max = 21, message = "Password must be between 8 and 21 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()-_=+[{]};:',<.>/?]).*$",
            message = "Invalid password. Password must contain at least one uppercase letter, one lowercase letter, one number and one special character.")
    String password;
}
