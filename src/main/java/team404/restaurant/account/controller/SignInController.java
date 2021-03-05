package team404.restaurant.account.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team404.restaurant.account.dto.SignInDto;
import team404.restaurant.account.service.SignInService;
import team404.restaurant.general.dto.TokenDto;

@Api(value = "Login Controller")
@RestController
@RequiredArgsConstructor
public class SignInController {

    private final SignInService signInService;

    @ApiOperation(value = "Login endpoint")
    @PostMapping("/signIn")
    public ResponseEntity<TokenDto> signIn(@ApiParam @RequestBody SignInDto signInDto) {
        return ResponseEntity.ok(signInService.signIn(signInDto));
    }

}
