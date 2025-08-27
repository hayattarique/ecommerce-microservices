package com.boot.user.repositories;

import com.boot.user.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RolesRepository extends JpaRepository<Roles, Long> {

}
