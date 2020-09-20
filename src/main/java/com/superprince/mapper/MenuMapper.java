package com.superprince.mapper;

import com.superprince.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends BaseMapper<Menu>{

    List<Menu> getMenuList();
}
