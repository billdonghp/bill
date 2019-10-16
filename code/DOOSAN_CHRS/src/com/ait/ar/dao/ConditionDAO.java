package com.ait.ar.dao;

import java.util.List;
import java.util.Map;


public interface ConditionDAO {
  /**
   * getOptions
   * <br>
   * 根据输入的条件号，找到关联于此条件的所有可能值。
   * @param conditionNo int
   * @return List
   */
  public List getOptions(int conditionNo,String cpny_id);

  /**
   * getConditionMapping
   * <br>
   * 从系统中找到所有条件，并且找到所有条件所关联的可选择的值，将这些值与相应的条件建立关联。
   * @return Map
   */
  public Map getConditionMapping();

  /**
   * getAllConditions
   * <br>
   * 找到系统中所有提供的可供选择的条件。
   * @param conditionNo int
   * @return List
   */
  public List getAllConditions();

}
