package com.superprince.service.impl;

import com.superprince.entity.Menu;
import com.superprince.mapper.MenuMapper;
import com.superprince.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuList() {
        return menuMapper.getMenuList();
    }
}
