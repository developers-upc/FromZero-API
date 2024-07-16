package com.acme.fromzeroapi.profiles.interfaces.rest.transform;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.profiles.interfaces.rest.resources.CompanyProfileResource;

public class CompanyProfileResourceFromEntityAssembler {
    public static CompanyProfileResource toResourceFromEntity(Enterprise entity){
        return new CompanyProfileResource(
                entity.getId(),
                entity.getEnterpriseName(),
                entity.getEmail(),
                entity.getDescription(),
                entity.getCountry(),
                entity.getRuc(),
                entity.getPhone(),
                entity.getWebsite(),
                entity.getProfileImgUrl(),
                entity.getSector()
        );
    }
}
