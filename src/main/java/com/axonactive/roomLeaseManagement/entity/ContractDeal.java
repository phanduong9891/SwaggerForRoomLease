package com.axonactive.roomLeaseManagement.entity;

import lombok.*;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PreAuthorize("hasRole('ADMIN')")
public class ContractDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private double rent;

    @NotNull
    @Column(nullable = false)
    private double deposit;

    @NotNull
    @Column(nullable = false, name = "electricity_price_per_Kw")
    private double electricityPrice;

    @NotNull
    @Column(nullable = false)
    private double waterPrice;

    @NotNull
    private int maximumGuest;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ContractType type;

    @JoinColumn
    @ManyToOne
    private Room room;

    @JoinColumn
    @ManyToOne
    private Contract contract;

}
