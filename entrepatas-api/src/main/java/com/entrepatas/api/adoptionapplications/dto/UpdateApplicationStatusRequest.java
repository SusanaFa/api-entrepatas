package com.entrepatas.api.adoptionapplications.dto;

import com.entrepatas.api.adoptionapplications.enums.ApplicationStatus;

import jakarta.validation.constraints.NotNull;

public class UpdateApplicationStatusRequest {

    @NotNull
    private ApplicationStatus status;

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
