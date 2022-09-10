package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.Room;
import com.axonactive.roomLeaseManagement.entity.RoomStatus;
import com.axonactive.roomLeaseManagement.request.RoomRequest;
import com.axonactive.roomLeaseManagement.service.dto.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<Room> getAll();
    Room save(Room room);
    Optional<Room> findById(Integer id);
    void deleteById(Integer id);

    RoomByStatusDto numberOfRoomByStatus();
    Optional<Room> findByRoomNumber(int roomNumber);
    List<Room> showRoomByStatus(RoomStatus status);
    Room create(RoomRequest roomRequest);
    Room edit(Integer roomId, RoomRequest roomRequest);

    RoomHistoryDto showRoomHistory(int roomId);

   List<RoomIncomeDto> showRoomIncome(LocalDate date1, LocalDate date2);

   List<RoomRentInfoDto> showRoomRentInfo(Long rentTimes);



//    List<RoomIncomeDto> showRoomIncome(LocalDate date1, LocalDate date2);
}
