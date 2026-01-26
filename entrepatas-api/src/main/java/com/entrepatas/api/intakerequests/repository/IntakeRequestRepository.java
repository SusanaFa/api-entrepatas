package com.entrepatas.api.intakerequests.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.entrepatas.api.intakerequests.enums.IntakeRequestStatus;
import com.entrepatas.api.intakerequests.model.IntakeRequest;

public interface IntakeRequestRepository extends MongoRepository<IntakeRequest, String> {

    List<IntakeRequest> findAllByOrderByCreatedAtDesc();

    List<IntakeRequest> findByStatusOrderByCreatedAtDesc(IntakeRequestStatus status);

    // anti-spam simple: mismo email + mismo mensaje (normalizado) en un corto plazo
    // lo haremos después si quieres
    boolean existsByEmailAndMessage(String email, String message);
}
