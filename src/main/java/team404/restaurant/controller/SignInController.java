package team404.restaurant.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team404.restaurant.model.dto.AccountDto;
import team404.restaurant.model.dto.TokenDto;
import team404.restaurant.service.SignInService;

@Api(value = "Login")
@RestController
@AllArgsConstructor
public class SignInController {

    private final SignInService signInService;

    @ApiOperation(
            value = "Login endpoint"
    )
    @PostMapping("/signIn")
    public ResponseEntity<TokenDto> signIn(@RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(signInService.signIn(accountDto));
    }

}
