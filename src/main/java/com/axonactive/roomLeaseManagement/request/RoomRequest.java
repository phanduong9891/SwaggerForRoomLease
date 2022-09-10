package com.axonactive.roomLeaseManagement.request;

import com.axonactive.roomLeaseManagement.entity.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest {

    private int roomNumber;

    private String roomType;

    private RoomStatus roomStatus;

    private int ownerId;
}
