package com.axonactive.roomLeaseManagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantRequest {

    private String firstName;

    private String lastName;

    private String gender;

    private String phoneNumber;

    private  String idCardNumber;

    private LocalDate birthday;

    private int relativesId;
}
