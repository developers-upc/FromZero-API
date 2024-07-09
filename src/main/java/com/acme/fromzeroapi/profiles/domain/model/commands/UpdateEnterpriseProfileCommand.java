package com.acme.fromzeroapi.profiles.domain.model.commands;

public record UpdateEnterpriseProfileCommand(Long id, String description, String country, String ruc, String phone, String website, String profileImgUrl, String sector) {
}
