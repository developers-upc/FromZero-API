package com.acme.fromzeroapi.iam.interfaces.rest.resources;

public record SignUpDeveloperResource(
        String mail,
        String password,
        String firstName,
        String lastName
) {
}
