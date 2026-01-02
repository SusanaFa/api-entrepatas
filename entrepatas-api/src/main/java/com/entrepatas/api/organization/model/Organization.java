package com.entrepatas.api.organization.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "organizations")
public class Organization {

    @Id
    private String id;

    @NotBlank(message = "nombre es requerido")
    private String name;

    @NotBlank(message = "el slug es obligatorio")
    private String slug;

    private Instant createdAt = Instant.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Organization [id=" + id + ", name=" + name + ", slug=" + slug + ", createdAt=" + createdAt + "]";
    }

}
