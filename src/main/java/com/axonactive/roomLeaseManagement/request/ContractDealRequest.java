package com.axonactive.roomLeaseManagement.request;

import com.axonactive.roomLeaseManagement.entity.ContractType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDealRequest {
    private double rent;
    private double deposit;
    private double electricityPrice;
    private double waterPrice;
    private int maximumGuest;
    private ContractType type;
    private Integer roomId;
    private Integer contractId;
}
