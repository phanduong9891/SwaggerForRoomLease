package com.axonactive.roomLeaseManagement.entity;

import lombok.*;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PreAuthorize("hasRole('ADMIN')")
public class MonthlyPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @Enumerated(EnumType.STRING)
    private Month month;

    private String year;

    private double electricityBill;

    @NotNull
    @Column(nullable = false)
    private double waterBill;

    @NotNull
    @Column(nullable = false)
    private double rent;

    @NotNull
    @Column(nullable = false, name = "paid")
    private boolean paid;

    @Column
    private LocalDate paidDay;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @JoinColumn
    @ManyToOne
    private Contract contract;

}
