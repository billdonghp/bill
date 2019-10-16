package com.multiseafoods.msapp.mapper;

import com.multiseafoods.msapp.entity.Oem;

public interface OemMapper {
    int insert(Oem record);

    int insertSelective(Oem record);
}