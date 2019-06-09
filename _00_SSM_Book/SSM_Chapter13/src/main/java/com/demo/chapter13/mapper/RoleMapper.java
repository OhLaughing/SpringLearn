package com.demo.chapter13.mapper;

import com.demo.chapter13.pojo.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
	int insertRole(Role role);
}