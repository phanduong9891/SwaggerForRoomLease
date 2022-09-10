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
public class Assets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String quantity;
    private double price;
    @Column
    @Enumerated(EnumType.STRING)
    private AssetStatus status;

    @JoinColumn
    @ManyToOne
    private Room room;
}
