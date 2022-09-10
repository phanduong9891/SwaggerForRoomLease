package com.axonactive.roomLeaseManagement.service.dto;

import com.axonactive.roomLeaseManagement.entity.ContractType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDealDto {
    private double rent;
    private double deposit;
    private double electricityPrice;
    private double waterPrice;
    private ContractType type;
    private int roomNumber;
    private String roomType; //may need to change to enum
    private String tenantFullName;
    private String tenantIdCardNumber;

}
