package com.entrepatas.api.intakerequests.dto;

import com.entrepatas.api.intakerequests.enums.IntakeRequestType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateIntakeRequestRequest {

    @NotNull(message = "type es requerido")
    private IntakeRequestType type;

    @NotBlank(message = "name es requerido")
    @Size(min = 3, max = 80, message = "El nombre debe tener entre 3 y 80 caracteres.")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre no debe contener números ni caracteres especiales.")
    private String name;

    @NotBlank(message = "email es requerido")
    @Email(message = "Ingrese un correo válido")
    private String email;

    // opcional, pero validado si viene
    @Pattern(regexp = "^[0-9+ ]{8,20}$", message = "Teléfono inválido.")
    private String phone;

    private String city;

    @NotBlank(message = "message es requerido")
    @Size(min = 10, max = 1000, message = "El mensaje debe tener entre 10 y 1000 caracteres.")
    private String message;

    // Opcional: si es organización
    private String organizationName;

    // Opcional: resumen del caso/animal (MVP)
    private String petSummary; // ej: "Perro hembra, 6-8 meses, rescatada, necesita hogar temporal"

    public IntakeRequestType getType() {
        return type;
    }

    public void setType(IntakeRequestType type) {
        this.type = type;
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
}
