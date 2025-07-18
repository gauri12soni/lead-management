package com.chp.lead.Service;

import com.chp.lead.dto.*;
import com.chp.lead.Entity.*;
import com.chp.lead.Repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeadService {

    private final LeadMasterRepository leadRepo;
    private final LeadCommunicationRepository commRepo;
    private final ModelMapper mapper;

    public LeadMasterDTO createLead(LeadMasterDTO dto) {
        // Set UUID if not provided
        if (dto.getLeadId() == null) {
            dto.setLeadId(UUID.randomUUID());
        }

        LeadMaster entity = mapper.map(dto, LeadMaster.class);
        LeadMaster saved = leadRepo.save(entity);

        log.info("Lead created with ID: {}", saved.getLeadId());
        return mapper.map(saved, LeadMasterDTO.class);
    }

    public List<LeadMasterDTO> getAllLeads() {
        List<LeadMaster> leads = leadRepo.findAll();
        log.info("Total leads fetched: {}", leads.size());

        return leads.stream()
                .map(lead -> mapper.map(lead, LeadMasterDTO.class))
                .collect(Collectors.toList());
    }

    public LeadCommunicationDTO addCommunication(LeadCommunicationDTO dto) {
        UUID leadId = dto.getLeadId();

        // Step 1: Find the Lead
        LeadMaster lead = leadRepo.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found with ID: " + leadId));

        // Step 2: Manually create entity (avoid ModelMapper for new entities)
        LeadCommunication communication = new LeadCommunication();
        communication.setCommunicationBy(dto.getCommunicationBy());
        communication.setCommunication(dto.getCommunication());
        communication.setLead(lead); // Set reference

        // Step 3: Save
        LeadCommunication saved = commRepo.save(communication);
        log.info("Communication added for Lead ID: {}", leadId);

        // Step 4: Build response DTO
        return LeadCommunicationDTO.builder()
                .communicationId(saved.getCommunicationId())
                .leadId(saved.getLead().getLeadId())
                .communicationBy(saved.getCommunicationBy())
                .communication(saved.getCommunication())
                .build();
    }

}
