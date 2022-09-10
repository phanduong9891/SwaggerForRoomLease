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
public class Relatives {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @Column(nullable = false)
    private String relationship;

    @NotNull
    @Column(nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(nullable = false)
    private String idCardNumber;
}
