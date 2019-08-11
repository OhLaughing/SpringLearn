package com.demo.mapper;

import com.demo.pojo.Info;
import com.demo.pojo.Role;

import java.util.List;

/**
 * Created by gustaov on 2019/6/7.
 */
public interface RoleMapper {
    Role selectRole(long id);
    List<Role> selectRoles(Info info);
}
