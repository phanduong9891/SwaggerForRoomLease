package com.axonactive.roomLeaseManagement.request;

import com.axonactive.roomLeaseManagement.entity.AssetStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetsRequest {
    private String name;
    private String quantity;
    private double price;
    private AssetStatus status;
    private Integer roomId;

}
