package com.chp.lead.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "LeadCommunication", schema = "lead")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadCommunication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer communicationId;

    private String communicationBy;
    private String communication;

    @ManyToOne
    @JoinColumn(name = "leadId") // this should match the foreign key column name in DB
    @ToString.Exclude
    private LeadMaster lead;
}
