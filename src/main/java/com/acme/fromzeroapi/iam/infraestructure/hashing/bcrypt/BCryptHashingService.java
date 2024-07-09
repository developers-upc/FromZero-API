package com.acme.fromzeroapi.iam.infraestructure.hashing.bcrypt;

import com.acme.fromzeroapi.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
