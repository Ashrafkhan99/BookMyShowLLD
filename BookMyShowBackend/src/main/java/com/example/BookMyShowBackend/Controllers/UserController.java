package com.example.BookMyShowBackend.Controllers;

import com.example.BookMyShowBackend.dto.ResponseStatus;
import com.example.BookMyShowBackend.dto.SignUpRequestDto;
import com.example.BookMyShowBackend.dto.SignUpResponseDto;
import com.example.BookMyShowBackend.model.User;
import com.example.BookMyShowBackend.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {

        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();

        // Call the service to handle user registration
        User user = userService.signUp(signUpRequestDto.getEmail(),
                           signUpRequestDto.getPassword());

        signUpResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        signUpResponseDto.setUserId(user.getId());

        return signUpResponseDto;
    }
}
