package com.entrepatas.api.intakerequests.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.entrepatas.api.intakerequests.enums.IntakeRequestStatus;
import com.entrepatas.api.intakerequests.enums.IntakeRequestType;

import jakarta.validation.constraints.NotNull;

@Document(collection = "intake_requests")
public class IntakeRequest {

    @Id
    private String id;

    @NotNull
    private IntakeRequestType type;

    @NotNull
    private IntakeRequestStatus status = IntakeRequestStatus.NEW;

    private String name;
    private String email;
    private String phone;
    private String city;

    private String message;

    private String organizationName;
    private String petSummary;

    private Instant createdAt = Instant.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IntakeRequestType getType() {
        return type;
    }

    public void setType(IntakeRequestType type) {
        this.type = type;
    }

    public IntakeRequestStatus getStatus() {
        return status;
    }

    public void setStatus(IntakeRequestStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getPetSummary() {
        return petSummary;
    }

    public void setPetSummary(String petSummary) {
        this.petSummary = petSummary;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
