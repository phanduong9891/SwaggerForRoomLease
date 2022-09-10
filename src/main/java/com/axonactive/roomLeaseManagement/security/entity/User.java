package com.axonactive.roomLeaseManagement.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @JsonIgnore
    private String password;

    private Boolean active;

    @OneToMany(mappedBy = "users")
    private List<UserRoleAssignment> roles = new ArrayList<>();

    public List<UserRoleAssignment> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleAssignment> roles) {
        this.roles = roles;
    }

}
