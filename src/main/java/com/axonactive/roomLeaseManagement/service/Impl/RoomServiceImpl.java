package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Room;
import com.axonactive.roomLeaseManagement.entity.RoomStatus;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.repository.RoomRepository;
import com.axonactive.roomLeaseManagement.request.RoomRequest;
import com.axonactive.roomLeaseManagement.service.RoomService;
import com.axonactive.roomLeaseManagement.service.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private OwnerServiceImpl ownerService;

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> findById(Integer id) {
        return roomRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        roomRepository.deleteById(id);
    }

    @Override
    public RoomByStatusDto numberOfRoomByStatus() {
        return new RoomByStatusDto(
                roomRepository.findByStatus(RoomStatus.RENTED).size(),
                roomRepository.findByStatus(RoomStatus.AVAILABLE).size(),
                roomRepository.findByStatus(RoomStatus.UNAVAILABLE).size());
    }

    @Override
    public Optional<Room> findByRoomNumber(int roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }

    @Override
    public List<Room> showRoomByStatus(RoomStatus status) {
        return roomRepository.findByStatus(status);
    }

    @Override
    public Room create(RoomRequest roomRequest) {
        if (!ownerService.findById(roomRequest.getOwnerId()).isPresent()) {
            log.info("Cant find owner of this room with this id {} ", roomRequest.getOwnerId());
            throw ExceptionList.ownerNotFound();
        }
        Room room = new Room();

        room.setRoomNumber(roomRequest.getRoomNumber());
        room.setRoomType(roomRequest.getRoomType());
        room.setStatus(roomRequest.getRoomStatus());
        room.setOwner(ownerService.findById(roomRequest.getOwnerId()).get());

        return room;
    }

    @Override
    public Room edit(Integer roomId, RoomRequest roomRequest) {

        if (!roomRepository.findById(roomId).isPresent()) {
            log.info("cant find room with this id {} ", roomId);
            throw ExceptionList.roomNotFound();
        }

        if (!ownerService.findById(roomRequest.getOwnerId()).isPresent()) {
            log.info("Cant find owner of this room with this id {} ", roomRequest.getOwnerId());
            throw ExceptionList.ownerNotFound();
        }

        Room room = roomRepository.findById(roomId).get();

        room.setRoomNumber(roomRequest.getRoomNumber());
        room.setRoomType(roomRequest.getRoomType());
        room.setStatus(roomRequest.getRoomStatus());
        room.setOwner(ownerService.findById(roomRequest.getOwnerId()).get());

        return room;
    }

    @Override
    public RoomHistoryDto showRoomHistory(int roomId) {
        Room room1 = roomRepository.findById(roomId).orElseThrow(ExceptionList::roomNotFound);

        List<ContractInfoDto> contractInfoDtoList = roomRepository.showRoomContractInfo(roomId);

        double totalMoney = 0;

        if (!contractInfoDtoList.isEmpty()){
            totalMoney = roomRepository.findTotalRoomMoney(roomId);
        }

        return new RoomHistoryDto(
                room1.getRoomNumber(),
                totalMoney,
                contractInfoDtoList
        );
    }


//    @Override
//    public List<RoomIncomeDto> showRoomIncome(LocalDate date1, LocalDate date2) {
//        List<Room> roomList = roomRepository.findAll();
//        List<RoomIncomeDto> roomIncomeDtoList = new ArrayList<>();
//        double roomIncome = 0;
//
//        for(Room r: roomList){
//            Integer roomNumber = r.getRoomNumber();
//            roomIncome = roomRepository.showRoomIncome(date1,date2,roomNumber);
//            RoomIncomeDto roomIncomeDto = new RoomIncomeDto(roomNumber, roomIncome);
//            roomIncomeDtoList.add(roomIncomeDto);
//        }
//        return roomIncomeDtoList;
//    }

    @Override
    public List<RoomIncomeDto> showRoomIncome(LocalDate date1, LocalDate date2) {
        List<RoomIncomeDto> roomIncomeDtoList = roomRepository.showRoomIncome(date1,date2);

        List<Room> roomList = roomRepository.findAll();

        Map<Integer,Double> roomIncomeList = new HashMap<>();

        for(Room r: roomList){
            roomIncomeList.put(r.getRoomNumber(), (double) 0);
        }

        for(RoomIncomeDto r: roomIncomeDtoList){
            if(roomIncomeList.containsKey(r.getRoomNumber())){
                roomIncomeList.replace(r.getRoomNumber(), (double) 0,r.getRoomIncome());
            }
        }
        List<RoomIncomeDto> roomIncomeDtoList1 = new ArrayList<>();

        for(Integer i: roomIncomeList.keySet()){
            Integer roomNumber = i;
            Double roomIncome = roomIncomeList.get(i);
            RoomIncomeDto roomIncomeDto = new RoomIncomeDto(roomNumber,roomIncome);
            roomIncomeDtoList1.add(roomIncomeDto);
        }
        return roomIncomeDtoList1;
    }

    @Override
    public List<RoomRentInfoDto> showRoomRentInfo(Long rentTimes) {
        return roomRepository.showRoomInfo(rentTimes);
    }

}
