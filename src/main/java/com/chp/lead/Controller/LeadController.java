package com.chp.lead.Controller;

import com.chp.lead.dto.LeadCommunicationDTO;
import com.chp.lead.dto.LeadMasterDTO;
import com.chp.lead.Service.LeadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/leads")
@RequiredArgsConstructor
@Slf4j
public class LeadController {

    private final LeadService leadService;
    @PostMapping
    public LeadMasterDTO createLead(@RequestBody LeadMasterDTO dto) {
        log.info("Creating new lead: {}", dto);
        return leadService.createLead(dto);
    }

    @GetMapping
    public List<LeadMasterDTO> getAllLeads() {
        log.info("Fetching all leads");
        return leadService.getAllLeads();
    }

    @PostMapping("/{leadId}/communications")
    public LeadCommunicationDTO addCommunication(@PathVariable UUID leadId,
                                                 @RequestBody LeadCommunicationDTO dto) {
        log.info("Adding communication to lead ID {}: {}", leadId, dto);
        dto.setLeadId(leadId);
        return leadService.addCommunication(dto);
    }


}
