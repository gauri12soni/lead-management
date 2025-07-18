package com.chp.lead.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeadMasterDTO {
    private UUID leadId;
    private String name;
    private String phoneNo;
    private Integer age;
    private LocalDate dob;
    private String inquiry;
    private String status;
    private String facilityId;
    private String origin;
}
