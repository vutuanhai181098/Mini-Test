package com.miniTest.demo.controller;

import com.miniTest.demo.dto.UserDto;
import com.miniTest.demo.request.AvatarRequest;
import com.miniTest.demo.request.UpdatePasswordRequest;
import com.miniTest.demo.request.UserRequest;
import com.miniTest.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    //1. Lấy danh sách users (có phân trang - pagination)
    @GetMapping("/users")
    public ResponseEntity<?> getUsers(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "10") int limit){
        return new ResponseEntity<>(userService.getUsers(page, limit), HttpStatus.OK);
    }

    //2. Tìm kiếm user theo tên
    @GetMapping("/search")
    public ResponseEntity<?> getUserByName(@RequestParam String name){
        return new ResponseEntity<>(userService.getUserByName(name), HttpStatus.OK);
    }

    //3. Lấy chi tiết user theo id
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    //4. Tạo mới user
    @PostMapping("/users")
    public ResponseEntity<?> creatUser(@Valid @RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
    }

    //5. Cập nhật thông tin user
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id,@Valid @RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.updateUser(id, userRequest), HttpStatus.OK);
    }

    //6. Xóa user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    //7. Thay đổi ảnh avatar
    @PutMapping("/users/{id}/update-avatar")
    public ResponseEntity<?> updateAvatar(@PathVariable Integer id,@RequestBody AvatarRequest avatarRequest){
        userService.updateAvatar(id, avatarRequest);
        return ResponseEntity.noContent().build();
    }

    //8. 8. Thay đổi mật khẩu
    @PutMapping("/users/{id}/update-password")
    public ResponseEntity<?> updatePassword(@PathVariable Integer id,@Valid @RequestBody UpdatePasswordRequest request){
        userService.updatePassword(id, request);
        return ResponseEntity.noContent().build();
    }

    //9. Quên mật khẩu
    @PostMapping("/users/{id}/forgot-password")
    public ResponseEntity<?> forgotPassword(@PathVariable Integer id){
        return new ResponseEntity<>(userService.forgotPassword(id), HttpStatus.OK);
    }
}
