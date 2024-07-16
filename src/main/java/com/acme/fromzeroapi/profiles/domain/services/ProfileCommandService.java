package com.acme.fromzeroapi.profiles.domain.services;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.profiles.domain.model.commands.*;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Developer> handle(UpdateDeveloperCompletedProjectsCommand command);
    Optional<Developer> handle(UpdateDeveloperProfileCommand command);
    Optional<Enterprise> handle(UpdateEnterpriseProfileCommand command);

    void handle(CreateCompanyProfileCommand command);
    void handle(CreateDeveloperProfileCommand command);
}
