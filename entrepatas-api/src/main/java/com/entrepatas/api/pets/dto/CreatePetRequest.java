package com.entrepatas.api.pets.dto;

import java.time.LocalDate;

import com.entrepatas.api.pets.enums.BirthDateApproxLevel;
import com.entrepatas.api.pets.enums.PetPriority;
import com.entrepatas.api.pets.enums.PetSex;
import com.entrepatas.api.pets.enums.PetSpecies;
import com.entrepatas.api.pets.enums.PetStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//DTO 
public class CreatePetRequest {

    @NotBlank
    private String organizationId;

    @NotBlank
    private String name;

    @NotNull
    private PetSpecies species;

    @NotNull
    private PetSex sex;

    private LocalDate birthDateApprox;
    private BirthDateApproxLevel birthDateApproxLevel;
    private String ageEstimateText;

    // Opcionales (con defaults)
    private PetStatus status = PetStatus.AVAILABLE;
    private PetPriority priority = PetPriority.NORMAL;

    // Getter y Setter
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

    public PetStatus getStatus() {
        return status;
    }

    public void setStatus(PetStatus status) {
        this.status = status;
    }

    public PetPriority getPriority() {
        return priority;
    }

    public void setPriority(PetPriority priority) {
        this.priority = priority;
    }

    // getters/setters

}
