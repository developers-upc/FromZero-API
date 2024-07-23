package com.acme.fromzeroapi.profiles.application.internal.queryServices;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.profiles.domain.services.ProfileQueryService;
import com.acme.fromzeroapi.profiles.domain.model.queries.*;
import com.acme.fromzeroapi.profiles.infrastructure.persistence.jpa.repositories.DeveloperRepository;
import com.acme.fromzeroapi.profiles.infrastructure.persistence.jpa.repositories.EnterpriseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final DeveloperRepository developerRepository;
    private final EnterpriseRepository enterpriseRepository;

    public ProfileQueryServiceImpl(DeveloperRepository developerRepository, EnterpriseRepository enterpriseRepository) {
        this.developerRepository = developerRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    @Override
    public List<Developer> handle(GetAllDevelopersAsyncQuery query) {
        return developerRepository.findAll();
    }

    /*@Override
    public Optional<Developer> handle(GetDeveloperByUserIdAsyncQuery query) {
        return developerRepository.findDeveloperByUserUserId(query.id());
    }

    @Override
    public Optional<Enterprise> handle(GetEnterpriseByUserIdAsyncQuery query) {
        return enterpriseRepository.findEnterpriseByUserUserId(query.id());
    }*/

    @Override
    public Optional<Enterprise> handle(GetEnterpriseByIdQuery query) {
        return this.enterpriseRepository.findById(query.enterpriseId());
    }

    @Override
    public Optional<Developer> handle(GetDeveloperByIdQuery query) {
        return this.developerRepository.findById(query.developerId());
    }

    @Override
    public Optional<Developer> handle(GetDeveloperProfileIdByEmailQuery query) {
        return developerRepository.findByEmail(query.email());
    }

    @Override
    public Optional<Enterprise> handle(GetCompanyProfileIdByEmailQuery query) {
        return enterpriseRepository.findByEmail(query.email());
    }

    @Override
    public Optional<Enterprise> handle(GetCompanyByIdQuery query) {
        return enterpriseRepository.findById(query.id());
    }
}
