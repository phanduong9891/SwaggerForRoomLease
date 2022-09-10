package com.axonactive.roomLeaseManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelativesDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String relationship;
    private String phoneNumber;
    private String idCardNumber;
}
