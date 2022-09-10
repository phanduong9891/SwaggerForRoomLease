package com.axonactive.roomLeaseManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetsDto {

    private String name;
    private String quantity;
    private double price;
    private String status;
    private int roomNumber;
}
