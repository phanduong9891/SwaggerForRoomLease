package com.axonactive.roomLeaseManagement.entity;

import lombok.*;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.persistence.*;
import java.time.YearMonth;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PreAuthorize("hasRole('ADMIN')")
public class MonthlyServiceUsing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double electricityUsage;

    @Enumerated(EnumType.STRING)
    private Month month;

    private String year;

    @JoinColumn
    @ManyToOne
    private ContractDeal contractDeal;
}
