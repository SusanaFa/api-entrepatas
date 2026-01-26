package com.entrepatas.api.medicalrecord.dto;

import java.time.LocalDate;
import java.util.List;

import com.entrepatas.api.medicalrecord.enums.MedicalRecordType;
import com.entrepatas.api.medicalrecord.enums.TriState;

import jakarta.validation.constraints.NotNull;

public class CreateMedicalRecordRequest {

    @NotNull
    private MedicalRecordType type;

    private LocalDate recordDate;

    private TriState sterilized = TriState.UNKNOWN;
    private TriState vaccinesUpToDate = TriState.UNKNOWN;
    private TriState dewormed = TriState.UNKNOWN;

    private List<String> conditions;
    private List<String> disabilities;

    private String summary;
    private String notes;

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
}
