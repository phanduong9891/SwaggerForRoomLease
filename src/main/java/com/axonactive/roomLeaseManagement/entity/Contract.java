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
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false,name = "Signed_date")
    private LocalDate dateSigned;

    @Column(name = "Expiry_date")
    private LocalDate dateExpiry;

    @Column
    private double contractValue;

    @JoinColumn
    @ManyToOne
    private Tenant tenant;
}
