package team404.restaurant.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team404.restaurant.account.dto.AccountDto;
import team404.restaurant.account.service.SignUpService;

@Tag(name = "SignUp")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class SignUpController {

    private final SignUpService signUpService;

    @Operation(summary = "Register endpoint")
    @PostMapping("/api/signup")
    public void signUpRestaurateur(@RequestBody AccountDto accountDto) {
        signUpService.signUp(accountDto);
    }
}
