package com.acme.fromzeroapi.profiles.interfaces.rest;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.profiles.domain.model.commands.UpdateDeveloperProfileCommand;
import com.acme.fromzeroapi.profiles.domain.model.commands.UpdateEnterpriseProfileCommand;
import com.acme.fromzeroapi.profiles.domain.model.queries.*;
import com.acme.fromzeroapi.profiles.domain.services.ProfileCommandService;
import com.acme.fromzeroapi.profiles.domain.services.ProfileQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/profiles")
@Tag(name = "Profiles", description = "Operations related to profiles")
public class ProfileController {
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfileController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }

    @Operation(summary = "Get all developers")
    @GetMapping("/developers")
    public ResponseEntity<List<Developer>> getAllDevelopers() {
        return ResponseEntity.ok(profileQueryService.handle(new GetAllDevelopersAsyncQuery()));
    }

    @Operation(summary = "Get developer by id")
    @GetMapping("/developers/{userId}")
    public ResponseEntity<Developer> getDeveloperById(@PathVariable Long userId) {
        var getDeveloperByUserIdQuery = new GetDeveloperByUserIdAsyncQuery(userId);
        var developer = profileQueryService.handle(getDeveloperByUserIdQuery);
        if (developer.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(developer.get());
    }

    @Operation(summary = "Get enterprise by id")
    @GetMapping("/enterprises/{userId}")
    public ResponseEntity<Enterprise> getEnterpriseById(@PathVariable Long userId) {
        var getEnterpriseByUserID = new GetEnterpriseByUserIdAsyncQuery(userId);
        var enterprise = profileQueryService.handle(getEnterpriseByUserID);
        if (enterprise.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(enterprise.get());
    }

    @GetMapping("/enterprise/{enterpriseId}")
    public ResponseEntity<Enterprise> getEnterprise(@PathVariable Long enterpriseId){
        var getEnterpriseByIdQuery = new GetEnterpriseByIdQuery(enterpriseId);
        var enterprise = profileQueryService.handle(getEnterpriseByIdQuery);
        if (enterprise.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(enterprise.get());
    }

    @Operation(summary = "Update developer profile")
    @PutMapping("/developers/{id}")
    public ResponseEntity<Developer> updateDeveloperProfile(@PathVariable Long id, @RequestBody UpdateDeveloperProfileCommand command) {

        if (!id.equals(command.id())) {
            throw new IllegalArgumentException("Path variable id doesn't match with request body id");
        }
        var updatedDeveloper = profileCommandService.handle(command);

        return ResponseEntity.ok(updatedDeveloper.get());
    }

    @Operation(summary = "Update enterprise profile")
    @PutMapping("/enterprises/{id}")
    public ResponseEntity<Enterprise> updateEnterpriseProfile(@PathVariable Long id, @RequestBody UpdateEnterpriseProfileCommand command) {

        if (!id.equals(command.id())) {
            throw new IllegalArgumentException("Path variable id doesn't match with request body id");
        }
        var updatedEnterprise = profileCommandService.handle(command);

        return ResponseEntity.ok(updatedEnterprise.get());
    }
    //
    // ------------------------------------------------------
    //
    @Operation(summary = "Get Developer Profile Id by email")
    @GetMapping(value = "/developer/{email}")
    public ResponseEntity<Long> getDeveloperProfileIdByEmail(@PathVariable String email) {
        var query = new GetDeveloperProfileIdByEmailQuery(email);
        var developer = profileQueryService.handle(query);
        if (developer.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(developer.get().getId());
    }

    @Operation(summary = "Get Company Profile Id by email")
    @GetMapping(value = "/company/{email}")
    public ResponseEntity<Long> getCompanyProfileIdByEmail(@PathVariable String email) {
        var query = new GetCompanyProfileIdByEmailQuery(email);
        var company = profileQueryService.handle(query);
        if (company.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(company.get().getId());
    }
}
