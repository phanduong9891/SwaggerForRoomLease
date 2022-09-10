package com.axonactive.roomLeaseManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomRentInfoDto {
    private Integer roomNumber;
    private Long rentTime;
}
