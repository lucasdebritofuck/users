package br.com.lucasfuck.user.adapter;

import br.com.lucasfuck.user.adapter.model.UserDto;
import br.com.lucasfuck.user.core.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Invalid user with constraint message")
    })
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto dto) {
        UserDto created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Find a user by identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource found"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    @GetMapping(path = "/{id}")
    public UserDto findAllByName(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Find users by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resources found"),
    })
    @GetMapping
    public List<UserDto> findAllByName(@RequestParam String name) {
        return service.findAllByName(name);
    }

    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "400", description = "Invalid user with constraint message")
    })
    @PatchMapping(path = "/{id}")
    public UserDto patch(@PathVariable Long id, @RequestBody UserDto dto) {
        return service.update(id, dto);
    }

}
