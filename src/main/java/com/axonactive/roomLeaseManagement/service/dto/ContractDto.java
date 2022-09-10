package com.axonactive.roomLeaseManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDto {
    private LocalDate dateSigned;
    private LocalDate dateExpiry;
    private double contractValue;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String idCardNumber;
    private String relativeFirstName;
    private String relativeRelationship;
    private String relativePhoneNumber;
}
