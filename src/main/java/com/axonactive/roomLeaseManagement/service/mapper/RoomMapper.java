package com.axonactive.roomLeaseManagement.service.mapper;

import com.axonactive.roomLeaseManagement.entity.Room;
import com.axonactive.roomLeaseManagement.service.dto.RoomDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    RoomDto toDto(Room room);

    List<RoomDto> toDtos(List<Room> roomList);
}
