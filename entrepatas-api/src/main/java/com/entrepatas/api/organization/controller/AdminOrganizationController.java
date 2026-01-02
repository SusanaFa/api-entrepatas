package com.entrepatas.api.organization.controller;

import org.springframework.web.bind.annotation.*;

import com.entrepatas.api.organization.model.Organization;
import com.entrepatas.api.organization.service.OrganizationService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/admin/organizations")
public class AdminOrganizationController {

    private final OrganizationService organizationService;

    public AdminOrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public Organization create(@Valid @RequestBody Organization org) {
        return organizationService.create(org);
    }

    @GetMapping
    public List<Organization> list() {
        return organizationService.list();
    }
}
