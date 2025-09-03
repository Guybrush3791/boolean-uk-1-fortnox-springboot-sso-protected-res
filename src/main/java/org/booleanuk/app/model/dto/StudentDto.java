package org.booleanuk.app.model.dto;

public record StudentDto (
        int id,
        String firstName,
        String lastName,
        String email,
        boolean retired
) {}
