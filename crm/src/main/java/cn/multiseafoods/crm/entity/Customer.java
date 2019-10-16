package cn.multiseafoods.crm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Integer id;

    private BigDecimal minMoney;

    private BigDecimal maxMoney;

    private String producer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(BigDecimal minMoney) {
        this.minMoney = minMoney;
    }

    public BigDecimal getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(BigDecimal maxMoney) {
        this.maxMoney = maxMoney;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Customer(){}

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", minMoney=" + minMoney +
                ", maxMoney=" + maxMoney +
                ", producer='" + producer + '\'' +
                '}';
    }
}
