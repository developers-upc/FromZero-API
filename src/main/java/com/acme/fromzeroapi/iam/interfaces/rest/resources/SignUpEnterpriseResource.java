package com.acme.fromzeroapi.iam.interfaces.rest.resources;

public record SignUpEnterpriseResource(
        String mail,
        String password,
        String enterpriseName
) {
}
