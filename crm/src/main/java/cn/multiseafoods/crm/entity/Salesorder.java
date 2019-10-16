package cn.multiseafoods.crm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
@Entity
public class Salesorder {
    @Id
    @GeneratedValue
    private Integer billId;
    //销售日期
    private Date billDate;
    //销售单价
    private BigDecimal taxPrice;
    //客户
    private Integer customerId;
    //采购订单号
    private String sourceNo;
    //已发货
    private Integer isSend;
    //可用数量
    private BigDecimal inventoryNum;
    //出库数量
    private BigDecimal actualNum;
    //销售金额
    private BigDecimal taxAmount;
    //品号
    private Integer materialId;

    public Salesorder() {
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getSourceNo() {
        return sourceNo;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

    public BigDecimal getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(BigDecimal inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public BigDecimal getActualNum() {
        return actualNum;
    }

    public void setActualNum(BigDecimal actualNum) {
        this.actualNum = actualNum;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }
}
