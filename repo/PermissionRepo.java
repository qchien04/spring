package com.example.pro.repo;

import com.example.pro.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepo extends JpaRepository<Permission,Integer> {
    Optional<Permission> findByPermissionName(String permissionName);
}
