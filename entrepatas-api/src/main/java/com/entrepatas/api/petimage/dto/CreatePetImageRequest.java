package com.entrepatas.api.petimage.dto;

import jakarta.validation.constraints.NotBlank;

public class CreatePetImageRequest {

    @NotBlank
    private String url;

    private Boolean isPrimary = false;
    private Integer orderIndex = 0;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }
}
