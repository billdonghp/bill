package com.multiseafoods.msapp.mapper;

import com.multiseafoods.msapp.entity.Cost;

public interface CostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Cost record);

    int insertSelective(Cost record);

    Cost selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Cost record);

    int updateByPrimaryKey(Cost record);
}