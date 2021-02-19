package team404.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team404.restaurant.model.dto.AccountDto;
import team404.restaurant.service.SignUpService;

@RestController
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signUp")
    public void signUp(@RequestBody AccountDto accountDto) {
        signUpService.signUp(accountDto);
    }
}
