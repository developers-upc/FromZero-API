package com.acme.fromzeroapi.support.interfaces.rest.resources;

public record CreateSupportTicketResource(String title, String type, String description, Long senderId) {
}
