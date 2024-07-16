package com.acme.fromzeroapi.profiles.domain.services;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.profiles.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    List<Developer> handle(GetAllDevelopersAsyncQuery query);
    Optional<Developer> handle(GetDeveloperByUserIdAsyncQuery query);
    Optional<Enterprise> handle(GetEnterpriseByUserIdAsyncQuery query);
    Optional<Enterprise> handle(GetEnterpriseByIdQuery query);
    Optional<Developer> handle(GetDeveloperByIdQuery query);
    Optional<Developer> handle(GetDeveloperProfileIdByEmailQuery query);
    Optional<Enterprise> handle(GetCompanyProfileIdByEmailQuery query);
    Optional<Enterprise> handle(GetCompanyByIdQuery query);
}
