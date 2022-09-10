package com.axonactive.roomLeaseManagement.repository;

import com.axonactive.roomLeaseManagement.entity.Room;
import com.axonactive.roomLeaseManagement.entity.RoomStatus;
import com.axonactive.roomLeaseManagement.service.dto.ContractInfoDto;
import com.axonactive.roomLeaseManagement.service.dto.RoomIncomeDto;
import com.axonactive.roomLeaseManagement.service.dto.RoomRentInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
    List<Room> findByStatus(RoomStatus status);
    Optional<Room> findByRoomNumber(int roomNumber);

    @Query("select sum(m.rent + m.waterBill + m.electricityBill)" +
            "from Room r, ContractDeal d, Contract c, MonthlyPayment m " +
            "where m.contract.id = c.id and " +
            "d.contract.id = c.id and " +
            "d.room.id = r.id " +
            "group by r.id,m.paid " +
            "having  m.paid = true and " +
            "r.id = ?1")
    double findTotalRoomMoney (int roomId);

    @Query("select new com.axonactive.roomLeaseManagement.service.dto.ContractInfoDto( t.id, t.firstName, t.lastName,c.dateSigned, c.dateExpiry, SUM(m.rent + m.electricityBill + m.waterBill) )" +
            "from Contract c, Tenant t, ContractDeal d, Room r, MonthlyPayment m " +
            "where t.id = c.tenant.id and m.contract.id = c.id " +
            "and d.contract.id = c.id " +
            "and d.room.id = r.id " +
            "group by t.id, t.firstName, t.lastName,c.dateSigned, c.dateExpiry, m.contract.id, r.id "+
            "having r.id = ?1")
    List<ContractInfoDto> showRoomContractInfo (int roomId);

    @Query("select new com.axonactive.roomLeaseManagement.service.dto.RoomIncomeDto(r.roomNumber,sum(m.rent + m.waterBill + m.electricityBill))"+
            "from Room r, ContractDeal d, Contract c, MonthlyPayment m "+
            "where d.room.id = r.id and d.contract.id = c.id and c.id = m.contract.id " +
            "group by r.roomNumber, m.paidDay, m.paid "+
            "having m.paidDay between ?1 and ?2 "+
            "and m.paid = true")
    List<RoomIncomeDto> showRoomIncome(LocalDate date1, LocalDate date2);

//    @Query("select sum(m.rent + m.waterBill + m.electricityBill) "+
//            "from Room r, ContractDeal d, Contract c, MonthlyPayment m "+
//            "where d.room.id = r.id and d.contract.id = c.id and c.id = m.contract.id " +
//            "group by r.roomNumber, m.paidDay, m.paid "+
//            "having m.paidDay between ?1 and ?2 "+
//            "and m.paid = true and r.roomNumber = ?3")
//    double showRoomIncome(LocalDate date1, LocalDate date2, Integer roomNumber);

    @Query("select new com.axonactive.roomLeaseManagement.service.dto.RoomRentInfoDto(r.roomNumber, count(distinct c.tenant.id)) " +
            "from Room r, ContractDeal d, Contract c " +
            "where d.room.id = r.id and d.contract.id = c.id " +
            "group by r.roomNumber " +
            "having count(distinct c.tenant.id) = ?1")
    List<RoomRentInfoDto> showRoomInfo(Long rentTime);
}
