package com.axonactive.roomLeaseManagement.service.dto;

import com.axonactive.roomLeaseManagement.entity.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    private int roomNumber;
    private String roomType;
    private RoomStatus status;
}
