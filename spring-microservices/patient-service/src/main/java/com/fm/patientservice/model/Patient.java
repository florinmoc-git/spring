package com.fm.patientservice.model;

public record Patient(long id, long wardId, String name, int age, String illness) {
}
