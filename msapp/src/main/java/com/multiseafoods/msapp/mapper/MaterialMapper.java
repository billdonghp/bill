package com.multiseafoods.msapp.mapper;

import com.multiseafoods.msapp.entity.Material;

public interface MaterialMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Material record);

    int insertSelective(Material record);

    Material selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);
}