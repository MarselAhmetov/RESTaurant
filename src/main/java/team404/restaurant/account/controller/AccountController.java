package team404.restaurant.account.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team404.restaurant.account.dto.AccountDto;
import team404.restaurant.account.dto.AccountSafeDto;
import team404.restaurant.account.service.AccountService;

@Api(value = "Account controller")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ApiOperation(value = "Submit Account information")
    @PostMapping("/api/account")
    public ResponseEntity<String> submit(@ApiParam @RequestBody AccountDto accountDto) {
        accountService.submit(accountDto);
        return ResponseEntity.ok("Ok");
    }

    @ApiOperation(value = "Get current Account information")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/account")
    public ResponseEntity<AccountSafeDto> submit() {
        return ResponseEntity.ok(accountService.getCurrentAccountInformation());
    }
}
