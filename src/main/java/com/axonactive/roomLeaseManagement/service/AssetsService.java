package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.Assets;
import com.axonactive.roomLeaseManagement.request.AssetsRequest;

import java.util.List;
import java.util.Optional;

public interface AssetsService {
    List<Assets> getAll();
    Assets save(Assets assets);
    Optional<Assets> findById(Integer id);
    void deleteById(Integer id);
    Assets create(AssetsRequest assetsRequest);
    Assets edit(Integer assetId, AssetsRequest assetsRequest);

}
