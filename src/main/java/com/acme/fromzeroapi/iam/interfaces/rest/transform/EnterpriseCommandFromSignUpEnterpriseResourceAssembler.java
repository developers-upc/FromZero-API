package com.acme.fromzeroapi.iam.interfaces.rest.transform;

import com.acme.fromzeroapi.iam.domain.model.commands.SignUpEnterpriseCommand;
import com.acme.fromzeroapi.iam.interfaces.rest.resources.SignUpEnterpriseResource;

public class EnterpriseCommandFromSignUpEnterpriseResourceAssembler {
    public static SignUpEnterpriseCommand toCommandFromResource(SignUpEnterpriseResource signUpEnterpriseResource) {
        return new SignUpEnterpriseCommand(signUpEnterpriseResource.mail(), signUpEnterpriseResource.password(), signUpEnterpriseResource.enterpriseName());
    }
}
