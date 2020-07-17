package com.hincky.routesys.pojo.entity;

import java.io.Serializable;

/**
 * user_order
 * @author 
 */
public class UserOrder implements Serializable {
    private Integer orderId;

    private Integer userId;

    /**
     * 订单总价
     */
    private Float allCost;

    /**
     * 订单创建时间
     */
    private String createTime;

    private String receiverPhone;

    private String receiverName;

    private Double orderWeight;

    /**
     * 时间窗1
     */
    private String timeWindow1;

    /**
     * 时间窗2
     */
    private String timeWindow2;

    /**
     * 付款状态
     */
    private String status;

    /**
     * 最早接收时间
     */
    private String earliestAccTime;

    /**
     * 最晚接收时间
     */
    private String latestAccTime;

    /**
     * 收货地址的地图坐标
     */
    private String point;

    /**
     * 收货目的地
     */
    private String destination;

    /**
     * 订单详情
     */
    private String details;

    private static final long serialVersionUID = 1L;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Float getAllCost() {
        return allCost;
    }

    public void setAllCost(Float allCost) {
        this.allCost = allCost;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Double getOrderWeight() {
        return orderWeight;
    }

    public void setOrderWeight(Double orderWeight) {
        this.orderWeight = orderWeight;
    }

    public String getTimeWindow1() {
        return timeWindow1;
    }

    public void setTimeWindow1(String timeWindow1) {
        this.timeWindow1 = timeWindow1;
    }

    public String getTimeWindow2() {
        return timeWindow2;
    }

    public void setTimeWindow2(String timeWindow2) {
        this.timeWindow2 = timeWindow2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEarliestAccTime() {
        return earliestAccTime;
    }

    public void setEarliestAccTime(String earliestAccTime) {
        this.earliestAccTime = earliestAccTime;
    }

    public String getLatestAccTime() {
        return latestAccTime;
    }

    public void setLatestAccTime(String latestAccTime) {
        this.latestAccTime = latestAccTime;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserOrder other = (UserOrder) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getAllCost() == null ? other.getAllCost() == null : this.getAllCost().equals(other.getAllCost()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getReceiverPhone() == null ? other.getReceiverPhone() == null : this.getReceiverPhone().equals(other.getReceiverPhone()))
            && (this.getReceiverName() == null ? other.getReceiverName() == null : this.getReceiverName().equals(other.getReceiverName()))
            && (this.getOrderWeight() == null ? other.getOrderWeight() == null : this.getOrderWeight().equals(other.getOrderWeight()))
            && (this.getTimeWindow1() == null ? other.getTimeWindow1() == null : this.getTimeWindow1().equals(other.getTimeWindow1()))
            && (this.getTimeWindow2() == null ? other.getTimeWindow2() == null : this.getTimeWindow2().equals(other.getTimeWindow2()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getEarliestAccTime() == null ? other.getEarliestAccTime() == null : this.getEarliestAccTime().equals(other.getEarliestAccTime()))
            && (this.getLatestAccTime() == null ? other.getLatestAccTime() == null : this.getLatestAccTime().equals(other.getLatestAccTime()))
            && (this.getPoint() == null ? other.getPoint() == null : this.getPoint().equals(other.getPoint()))
            && (this.getDestination() == null ? other.getDestination() == null : this.getDestination().equals(other.getDestination()))
            && (this.getDetails() == null ? other.getDetails() == null : this.getDetails().equals(other.getDetails()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getAllCost() == null) ? 0 : getAllCost().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getReceiverPhone() == null) ? 0 : getReceiverPhone().hashCode());
        result = prime * result + ((getReceiverName() == null) ? 0 : getReceiverName().hashCode());
        result = prime * result + ((getOrderWeight() == null) ? 0 : getOrderWeight().hashCode());
        result = prime * result + ((getTimeWindow1() == null) ? 0 : getTimeWindow1().hashCode());
        result = prime * result + ((getTimeWindow2() == null) ? 0 : getTimeWindow2().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getEarliestAccTime() == null) ? 0 : getEarliestAccTime().hashCode());
        result = prime * result + ((getLatestAccTime() == null) ? 0 : getLatestAccTime().hashCode());
        result = prime * result + ((getPoint() == null) ? 0 : getPoint().hashCode());
        result = prime * result + ((getDestination() == null) ? 0 : getDestination().hashCode());
        result = prime * result + ((getDetails() == null) ? 0 : getDetails().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", userId=").append(userId);
        sb.append(", allCost=").append(allCost);
        sb.append(", createTime=").append(createTime);
        sb.append(", receiverPhone=").append(receiverPhone);
        sb.append(", receiverName=").append(receiverName);
        sb.append(", orderWeight=").append(orderWeight);
        sb.append(", timeWindow1=").append(timeWindow1);
        sb.append(", timeWindow2=").append(timeWindow2);
        sb.append(", status=").append(status);
        sb.append(", earliestAccTime=").append(earliestAccTime);
        sb.append(", latestAccTime=").append(latestAccTime);
        sb.append(", point=").append(point);
        sb.append(", destination=").append(destination);
        sb.append(", details=").append(details);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}