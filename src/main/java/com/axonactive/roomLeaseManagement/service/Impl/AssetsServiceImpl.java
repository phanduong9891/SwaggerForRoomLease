package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Assets;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.repository.AssetsRepository;
import com.axonactive.roomLeaseManagement.request.AssetsRequest;
import com.axonactive.roomLeaseManagement.service.AssetsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class AssetsServiceImpl implements AssetsService {
    @Autowired
    private AssetsRepository assetsRepository;
    @Autowired
    private RoomServiceImpl roomService;

    @Override
    public List<Assets> getAll() {
        return assetsRepository.findAll();
    }

    @Override
    public Assets save(Assets assets) {
        return assetsRepository.save(assets);
    }

    @Override
    public Optional<Assets> findById(Integer id) {
        return assetsRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        assetsRepository.deleteById(id);

    }

    @Override
    public Assets create(AssetsRequest assetsRequest) {
        if(!roomService.findById(assetsRequest.getRoomId()).isPresent()){
            log.info("cant find room of the asset by id {}", assetsRequest.getRoomId());
            throw ExceptionList.roomNotFound();
        }
        Assets createAsset = new Assets();
        createAsset.setName(assetsRequest.getName());
        createAsset.setQuantity(assetsRequest.getQuantity());
        createAsset.setPrice(assetsRequest.getPrice());
        createAsset.setStatus(assetsRequest.getStatus());
        createAsset.setRoom(roomService.findById(assetsRequest.getRoomId()).get());

        return createAsset;
    }

    @Override
    public Assets edit(Integer assetId, AssetsRequest assetsRequest) {
        if(!assetsRepository.findById(assetId).isPresent()){
            log.info("cant find asset by id {} ", assetId);
            throw ExceptionList.assetNotFound();
        }
        if(!roomService.findById(assetsRequest.getRoomId()).isPresent()){
            log.info("cant find room of the asset by id {} ", assetsRequest.getRoomId());
            throw ExceptionList.roomNotFound();
        }
        Assets updateAsset = assetsRepository.findById(assetId).get();

        updateAsset.setName(assetsRequest.getName());
        updateAsset.setPrice(assetsRequest.getPrice());
        updateAsset.setQuantity(assetsRequest.getQuantity());
        updateAsset.setStatus(assetsRequest.getStatus());
        updateAsset.setRoom(roomService.findById(assetsRequest.getRoomId()).get());

        return updateAsset;
    }


}
