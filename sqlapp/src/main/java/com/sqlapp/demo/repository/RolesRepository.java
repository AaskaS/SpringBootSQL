package com.sqlapp.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqlapp.demo.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

	//List<Roles> findById(Long id);
}
