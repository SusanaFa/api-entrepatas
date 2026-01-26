package com.entrepatas.api.medicalrecord.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.entrepatas.api.medicalrecord.enums.MedicalRecordType;
import com.entrepatas.api.medicalrecord.enums.TriState;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection = "medical_record")
public class MedicalRecord {

    @Id
    private String id;

    @NotBlank(message = "petId es requerido")
    private String petId;

    @NotBlank(message = "organizationId es requerido")
    private String organizationId;

    @NotNull(message = "Type es requerido")
    private MedicalRecordType type = MedicalRecordType.INTAKE;

    // fecha del evento médico
    private LocalDate recordDate;

    // Estado clínico básico /esterilizacion, vacunas al día / desparasitacion
    private TriState sterilized = TriState.UNKNOWN;
    private TriState vaccinesUpToDate = TriState.UNKNOWN;
    private TriState dewormed = TriState.UNKNOWN;

    // condiciones / observaciones conocidas
    private List<String> conditions; // ["Dermatitis", "Soplo" ]
    private List<String> disabilities; // ["ceguera parcial"]

    private String summary; // texto corto
    private String notes; // texto largo (opcional)

    private Instant createdAt = Instant.now();

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

    public MedicalRecordType getType() {
        return type;
    }

    public void setType(MedicalRecordType type) {
        this.type = type;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public TriState getSterilized() {
        return sterilized;
    }

    public void setSterilized(TriState sterilized) {
        this.sterilized = sterilized;
    }

    public TriState getVaccinesUpToDate() {
        return vaccinesUpToDate;
    }

    public void setVaccinesUpToDate(TriState vaccinesUpToDate) {
        this.vaccinesUpToDate = vaccinesUpToDate;
    }

    public TriState getDewormed() {
        return dewormed;
    }

    public void setDewormed(TriState dewormed) {
        this.dewormed = dewormed;
    }

    public List<String> getConditions() {
        return conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }

    public List<String> getDisabilities() {
        return disabilities;
    }

    public void setDisabilities(List<String> disabilities) {
        this.disabilities = disabilities;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}
