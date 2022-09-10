package com.axonactive.roomLeaseManagement.service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomHistoryDto {
    private int roomNumber;
    private double totalRoomMoney;
    private List<ContractInfoDto> contractInfoDtoList;
}
