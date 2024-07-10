package com.acme.fromzeroapi.iam.interfaces.rest;

import com.acme.fromzeroapi.iam.domain.model.queries.GetUserByEmailQuery;
import com.acme.fromzeroapi.iam.domain.services.UserQueryService;
import com.acme.fromzeroapi.iam.interfaces.rest.resources.UserResource;
import com.acme.fromzeroapi.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UsersController {

    private final UserQueryService userQueryService;

    public UsersController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @Operation(summary = "Get user by email")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResource> getUserByEmail(@PathVariable String email) {
        var getUserByEmailQuery=new GetUserByEmailQuery(email);
        var user = this.userQueryService.handle(getUserByEmailQuery);
        if(user.isEmpty())return ResponseEntity.notFound().build();
        var resource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(resource);
    }
}
