package team404.restaurant.restaurateur.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team404.restaurant.restaurateur.dto.RestaurateurDto;
import team404.restaurant.restaurateur.service.RestaurateurService;

@Api(value = "Restaurateur Controller")
@RestController
@RequiredArgsConstructor
public class RestaurateurController {

    private final RestaurateurService restaurateurService;

    @ApiOperation("Restaurateur submit")
    @PostMapping("/api/restaurateur")
    @ResponseBody
    public void submitRestaurateur(@RequestBody RestaurateurDto restaurateurDto) {
        restaurateurService.submit(restaurateurDto);
    }

    @ApiOperation("Get information about current restaurateur")
    @GetMapping("/api/restaurateur")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    @ResponseBody
    public RestaurateurDto getCurrentRestaurateur() {
        return restaurateurService.getCurrentRestaurateur();
    }
}
