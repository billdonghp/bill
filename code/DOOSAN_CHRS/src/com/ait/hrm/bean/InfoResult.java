package com.ait.hrm.bean;

import java.util.ArrayList;
import java.util.List;

public class InfoResult {
  private final List values = new ArrayList();
  public InfoResult() {
  }

  public void setValue(int key, Object value) {
    values.set(key, value);
  }

  public Object getValue(int key) {
    return values.get(key);
  }
}
