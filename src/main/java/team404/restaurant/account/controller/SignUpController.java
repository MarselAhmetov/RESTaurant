package team404.restaurant.account.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team404.restaurant.account.dto.AccountDto;
import team404.restaurant.account.service.SignUpService;

@Api(value = "Registration")
@RestController
@AllArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @ApiOperation(value = "Register endpoint")
    @PostMapping("/api/signup")
    public void signUpRestaurateur(@ApiParam @RequestBody AccountDto accountDto) {
        signUpService.signUp(accountDto);
    }
}
