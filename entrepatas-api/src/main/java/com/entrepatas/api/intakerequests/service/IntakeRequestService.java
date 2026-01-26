package com.entrepatas.api.intakerequests.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entrepatas.api.intakerequests.dto.CreateIntakeRequestRequest;
import com.entrepatas.api.intakerequests.dto.UpdateIntakeRequestStatusRequest;
import com.entrepatas.api.intakerequests.enums.IntakeRequestStatus;
import com.entrepatas.api.intakerequests.model.IntakeRequest;
import com.entrepatas.api.intakerequests.repository.IntakeRequestRepository;

@Service
public class IntakeRequestService {

    private final IntakeRequestRepository intakeRequestRepository;

    public IntakeRequestService(IntakeRequestRepository intakeRequestRepository) {
        this.intakeRequestRepository = intakeRequestRepository;
    }

    public IntakeRequest create(CreateIntakeRequestRequest dto) {
        String email = dto.getEmail().trim().toLowerCase();

        // anti-spam básico (puedes quitarlo si te molesta por ahora)
        String msg = dto.getMessage().trim();
        if (intakeRequestRepository.existsByEmailAndMessage(email, msg)) {
            throw new IllegalArgumentException("Ya recibimos una solicitud idéntica desde ese correo.");
        }

        IntakeRequest r = new IntakeRequest();
        r.setType(dto.getType());
        r.setStatus(IntakeRequestStatus.NEW);

        r.setName(dto.getName());
        r.setEmail(email);
        r.setPhone(dto.getPhone());
        r.setCity(dto.getCity());
        r.setMessage(msg);

        r.setOrganizationName(dto.getOrganizationName());
        r.setPetSummary(dto.getPetSummary());

        return intakeRequestRepository.save(r);
    }

    public List<IntakeRequest> list(String status) {
        if (status != null && !status.isBlank()) {
            IntakeRequestStatus enumStatus = IntakeRequestStatus.valueOf(status.toUpperCase());
            return intakeRequestRepository.findByStatusOrderByCreatedAtDesc(enumStatus);
        }
        return intakeRequestRepository.findAllByOrderByCreatedAtDesc();
    }

    public IntakeRequest updateStatus(String id, UpdateIntakeRequestStatusRequest dto) {
        IntakeRequest r = intakeRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("IntakeRequest no encontrado"));

        r.setStatus(dto.getStatus());
        return intakeRequestRepository.save(r);
    }
}
