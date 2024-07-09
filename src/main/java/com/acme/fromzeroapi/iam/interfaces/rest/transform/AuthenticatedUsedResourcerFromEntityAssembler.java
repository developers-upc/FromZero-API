package com.acme.fromzeroapi.iam.interfaces.rest.transform;

import com.acme.fromzeroapi.iam.domain.model.aggregates.User;
import com.acme.fromzeroapi.iam.interfaces.rest.resources.AuthenticateUserResource;

public class AuthenticatedUsedResourcerFromEntityAssembler {
    public static AuthenticateUserResource toResourceFromEntity(User user) {
        return new AuthenticateUserResource(user.getUserId(),user.getUserType());
    }
}
