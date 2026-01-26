package com.entrepatas.api.adoptionapplications.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.entrepatas.api.adoptionapplications.enums.ApplicationStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Document(collection = "adoption_applications")
public class AdoptionApplication {

    @Id
    private String id;

    @NotBlank(message = "petId es requerido")
    private String petId;

    @NotBlank(message = "organizationId es requerido")
    private String organizationId;

    // estado definido al crear la solicitud = pendiente
    @NotNull(message = "status es requerido")
    private ApplicationStatus status = ApplicationStatus.PENDING;

    // datos del solicitante (version MVP)
    @NotBlank(message = "applicantName es requerido")
    @Size(min = 3, max = 80, message = "El nombre debe tener entre 3 y 80 caracteres.")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre no debe contener números ni caracteres especiales.")
    private String applicantName;

    @NotBlank(message = "applicantEmail es requerido")
    @Email(message = "Ingrese un correo válido")
    private String applicantEmail;

    @NotBlank(message = "applicantPhone es requerido")
    @Pattern(regexp = "^[0-9+ ]{8,20}$", message = "Teléfono inválido.")
    private String applicantPhone;

    // resumen motivación
    private String message;

    // Info básica para filtros(MVP)
    private String city;
    private String housingType; // casa, departamento
    private Boolean hasOtherPets; // otras mascotas en casa

    private Instant createdAt = Instant.now();

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getApplicantPhone() {
        return applicantPhone;
    }

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHousingType() {
        return housingType;
    }

    public void setHousingType(String housingType) {
        this.housingType = housingType;
    }

    public Boolean getHasOtherPets() {
        return hasOtherPets;
    }

    public void setHasOtherPets(Boolean hasOtherPets) {
        this.hasOtherPets = hasOtherPets;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}
