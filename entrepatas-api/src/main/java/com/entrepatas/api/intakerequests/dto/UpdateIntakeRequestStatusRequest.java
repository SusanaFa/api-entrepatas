package com.entrepatas.api.intakerequests.dto;

import com.entrepatas.api.intakerequests.enums.IntakeRequestStatus;

import jakarta.validation.constraints.NotNull;

public class UpdateIntakeRequestStatusRequest {

    @NotNull(message = "status es requerido")
    private IntakeRequestStatus status;

    public IntakeRequestStatus getStatus() {
        return status;
    }

    public void setStatus(IntakeRequestStatus status) {
        this.status = status;
    }
}
