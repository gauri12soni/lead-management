package com.chp.lead.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "LeadMaster", schema = "lead")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeadMaster {

    @Id
    private UUID leadId;

    private String name;
    private String phoneNo;
    private Integer age;
    private LocalDate dob;
    private String inquiry;
    private String status;
    private String facilityId;
    private String origin;

    @OneToMany(mappedBy = "lead", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LeadCommunication> communications;
}
