package com.acme.fromzeroapi.support.domain.model.commands;

import com.acme.fromzeroapi.iam.domain.model.aggregates.User;

public record CreateSupportTicketCommand(String title, String type, String description, User sender){
}
