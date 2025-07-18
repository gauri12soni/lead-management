package com.chp.lead.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeadCommunicationDTO {
    private Integer communicationId;
    private UUID leadId;
    private String communicationBy;
    private String communication;
}
