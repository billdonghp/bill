package com.multiseafoods.msapp.mapper;

import com.multiseafoods.msapp.entity.Salesorder;

public interface SalesorderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Salesorder record);

    int insertSelective(Salesorder record);

    Salesorder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Salesorder record);

    int updateByPrimaryKey(Salesorder record);
}