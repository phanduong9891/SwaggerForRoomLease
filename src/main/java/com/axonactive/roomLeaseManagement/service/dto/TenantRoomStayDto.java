package com.axonactive.roomLeaseManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TenantRoomStayDto {
    private String fullName;
    private String phoneNumber;
    private int roomNumber;
}
