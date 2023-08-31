package com.miniTest.demo.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserRequest {
    @NotBlank(message = "Tên không được để trống")
    String name;
    @NotBlank(message = "Email không được để trống")
    @Email(regexp = "^^[A-Za-z0-9][a-zA-Z0-9\\.]{6,29}@gmail\\.com$", message = "Email không hợp lệ")
    String email;
    @NotBlank(message = "Số điện thoại không được để trống")
    String phone;
    @NotBlank(message = "Địa chỉ không được để trống")
    String address;
    @NotBlank(message = "Mật khẩu không được để trống")
    String password;
    String avatar;
}
