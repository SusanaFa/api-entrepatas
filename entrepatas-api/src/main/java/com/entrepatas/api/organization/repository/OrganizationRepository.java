package com.entrepatas.api.organization.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.entrepatas.api.organization.model.Organization;

@Repository
public interface OrganizationRepository extends MongoRepository<Organization, String> {

    Optional<Organization> findBySlug(String slug);

}
