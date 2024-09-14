package com.example.pro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_name", nullable = false, unique = true, length = 50)
    private String roleName;

    // Quan hệ nhiều-nhiều với Permission
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER,
                cascade = {CascadeType.PERSIST,CascadeType.MERGE,
                            CascadeType.DETACH,CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinTable(
            name = "rolepermissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,
                    CascadeType.DETACH,CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinTable(
            name = "userroles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();


    public void addUsers(User user) {
        users.add(user);
    }

    public void addPermissions(Permission permission) {
        if(permissions==null){
            permissions=new HashSet<>();
        }
        permissions.add(permission);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleId == role.roleId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(roleId);
    }


    public Role(String roleName) {
        this.roleName = roleName;
    }
}
