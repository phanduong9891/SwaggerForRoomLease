package com.axonactive.roomLeaseManagement.api;

import com.axonactive.roomLeaseManagement.entity.Room;
import com.axonactive.roomLeaseManagement.entity.RoomStatus;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.request.RoomRequest;
import com.axonactive.roomLeaseManagement.service.Impl.OwnerServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.RoomServiceImpl;
import com.axonactive.roomLeaseManagement.service.dto.*;
import com.axonactive.roomLeaseManagement.service.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping(RoomResource.PATH)
public class RoomResource {
    public static final String PATH = "/api/room";
    @Autowired
    private RoomServiceImpl roomService;
    @Autowired
    private OwnerServiceImpl ownerService;

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAll() {
        List<Room> roomList = roomService.getAll();
        return ResponseEntity.ok(RoomMapper.INSTANCE.toDtos(roomList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getById(@PathVariable(value = "id") Integer id) {
        Room room = roomService.findById(id)
                .orElseThrow(ExceptionList::roomNotFound);
        return ResponseEntity.ok(RoomMapper.INSTANCE.toDto(room));
    }

    @GetMapping("/find")
    public ResponseEntity<RoomDto> findRoom(@RequestParam(name = "roomNumber", required = false) Integer roomNumber) {
        if (roomNumber == null)
            return ResponseEntity.noContent().build();
        Room room = roomService.findByRoomNumber(roomNumber)
                .orElseThrow(ExceptionList::roomNotFound);
        return ResponseEntity.ok(RoomMapper.INSTANCE.toDto(room));
    }

    @GetMapping("/roomCondition")
    public RoomByStatusDto getNumberOfRoomByStatus() {
        return roomService.numberOfRoomByStatus();
    }

    //nhap status sai thi xu sao?
    @GetMapping("/roomStatus/{status}")
    public ResponseEntity<List<RoomDto>> findRoomByStatus(@PathVariable(value = "status") RoomStatus status) {
        List<Room> roomList = roomService.showRoomByStatus(status);
        return ResponseEntity.ok(RoomMapper.INSTANCE.toDtos(roomList));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) {
        Room room = roomService.findById(id)
                .orElseThrow(ExceptionList::roomNotFound);
        roomService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RoomDto> create(@RequestBody RoomRequest roomRequest) {
        Room createRoom = roomService.save(roomService.create(roomRequest));

        return ResponseEntity.created(URI.create(RoomResource.PATH + "/" + createRoom.getId())).body(RoomMapper.INSTANCE.toDto(createRoom));
    }


    @PutMapping("/{id}")
    public ResponseEntity<RoomDto> update(@PathVariable(value = "id") Integer id, @RequestBody RoomRequest roomRequest) {

        Room roomUpdate = roomService.save(roomService.edit(id, roomRequest));

        return ResponseEntity.ok(RoomMapper.INSTANCE.toDto(roomUpdate));
    }


    @GetMapping("/roomHistory/{id}")
    public ResponseEntity<RoomHistoryDto> showRoomHistory(@PathVariable(value = "id") Integer id) {
        RoomHistoryDto roomHistoryDto = roomService.showRoomHistory(id);
        return ResponseEntity.ok(roomHistoryDto);
    }

    @GetMapping("/roomIncome")
    public ResponseEntity<List<RoomIncomeDto>> showRoomIncome(@RequestParam(name = "date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date1,
                                                              @RequestParam(name = "date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date2) {
        return ResponseEntity.ok(roomService.showRoomIncome(date1,date2));
    }

    @GetMapping("/roomRentTime")
    public ResponseEntity<List<RoomRentInfoDto>> showRoomRentInfo(@RequestParam(name = "a") Long rentTimes){

        List<RoomRentInfoDto> roomRentInfoList = roomService.showRoomRentInfo(rentTimes);

        return ResponseEntity.ok(roomRentInfoList);
    }
}
