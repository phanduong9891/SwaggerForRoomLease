package com.axonactive.roomLeaseManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OwnerDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String gender;

}
