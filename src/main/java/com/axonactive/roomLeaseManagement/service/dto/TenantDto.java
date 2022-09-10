package com.axonactive.roomLeaseManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantDto {
    private String fullName;
    private String gender;
    private String phoneNumber;
    private String idCardNumber;
    private LocalDate birthday;
    private String relativesFirstName;
    private String relationship;
    private String relativePhoneNumber;

}
