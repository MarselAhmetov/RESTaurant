package team404.restaurant.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team404.restaurant.account.dto.SignInDto;
import team404.restaurant.account.service.SignInService;
import team404.restaurant.general.dto.TokenDto;

@Tag(name = "SignIn")
@RestController
@RequiredArgsConstructor
public class SignInController {

    private final SignInService signInService;

    @Operation(summary = "Login endpoint")
    @PostMapping("/api/signIn")
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto signInDto) {
        return ResponseEntity.ok(signInService.signIn(signInDto));
    }

}
