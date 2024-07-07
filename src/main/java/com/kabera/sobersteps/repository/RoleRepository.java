package com.kabera.sobersteps.repository;

import com.kabera.sobersteps.model.Role;
import com.kabera.sobersteps.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName name);
}
