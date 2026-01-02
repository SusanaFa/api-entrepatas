package com.entrepatas.api.pets.model;

import java.time.Instant;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.entrepatas.api.pets.enums.PetPriority;
import com.entrepatas.api.pets.enums.PetSex;
import com.entrepatas.api.pets.enums.PetSpecies;
import com.entrepatas.api.pets.enums.PetStatus;
import com.entrepatas.api.pets.enums.BirthDateApproxLevel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection = "pets")
public class Pet {

    @Id
    private String id;

    @NotBlank(message = "organizationId es requerido")
    private String organizationId;

    @NotBlank(message = "nombre es requerido")
    private String name;

    @NotNull(message = "especie es requerido")
    private PetSpecies species; // "DOG" o "CAT" (por ahora string simple)

    @NotNull(message = "Sexo requerido")
    private PetSex sex; // "MALE" / "FEMALE" (string simple)

    // Fecha de nacimiento aproximada (opcional)
    private LocalDate birthDateApprox;

    // Nivel de aproximación (opcional, pero recomendado si hay birthDateApprox)
    private BirthDateApproxLevel birthDateApproxLevel;

    // Texto libre si no hay fecha (opcional)
    private String ageEstimateText;

    @NotNull(message = "Estado es requerido")
    private PetStatus status = PetStatus.AVAILABLE;

    @NotNull(message = "Prioridad es requerido")
    private PetPriority priority = PetPriority.NORMAL;
    private Instant createdAt = Instant.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetSpecies getSpecies() {
        return species;
    }

    public void setSpecies(PetSpecies species) {
        this.species = species;
    }

    public PetSex getSex() {
        return sex;
    }

    public void setSex(PetSex sex) {
        this.sex = sex;
    }

    public PetStatus getStatus() {
        return status;
    }

    public void setStatus(PetStatus status) {
        this.status = status;
    }

    public void setPriority(PetPriority priority) {
        this.priority = priority;
    }

    public PetPriority getPriority() {
        return priority;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getBirthDateApprox() {
        return birthDateApprox;
    }

    public void setBirthDateApprox(LocalDate birthDateApprox) {
        this.birthDateApprox = birthDateApprox;
    }

    public BirthDateApproxLevel getBirthDateApproxLevel() {
        return birthDateApproxLevel;
    }

    public void setBirthDateApproxLevel(BirthDateApproxLevel birthDateApproxLevel) {
        this.birthDateApproxLevel = birthDateApproxLevel;
    }

    public String getAgeEstimateText() {
        return ageEstimateText;
    }

    public void setAgeEstimateText(String ageEstimateText) {
        this.ageEstimateText = ageEstimateText;
    }

    @Override
    public String toString() {
        return "Pet [id=" + id + ", organizationId=" + organizationId + ", name=" + name + ", species=" + species
                + ", sex=" + sex + ", birthDateApprox=" + birthDateApprox + ", birthDateApproxLevel="
                + birthDateApproxLevel + ", ageEstimateText=" + ageEstimateText + ", status=" + status + ", priority="
                + priority + ", createdAt=" + createdAt + "]";
    }

}
