package com.entrepatas.api.organization.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entrepatas.api.organization.model.Organization;
import com.entrepatas.api.organization.repository.OrganizationRepository;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization create(Organization org) {
        return organizationRepository.save(org);
    }

    public List<Organization> list() {
        return organizationRepository.findAll();
    }

    public Organization getById(String id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organización no encontrada"));
    }
}
