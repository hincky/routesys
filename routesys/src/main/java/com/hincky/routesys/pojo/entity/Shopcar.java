package com.hincky.routesys.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * shopcar
 * @author 
 */
@Data
public class Shopcar implements Serializable {
    private Integer shopCarId;

    private String goodsName;

    private Float price;

    private Integer count;

    private Float total;

    private Integer userId;

    private Integer goodsId;

    private Float weight;

    private static final long serialVersionUID = 1L;

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
        Shopcar other = (Shopcar) that;
        return (this.getShopCarId() == null ? other.getShopCarId() == null : this.getShopCarId().equals(other.getShopCarId()))
            && (this.getGoodsName() == null ? other.getGoodsName() == null : this.getGoodsName().equals(other.getGoodsName()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getCount() == null ? other.getCount() == null : this.getCount().equals(other.getCount()))
            && (this.getTotal() == null ? other.getTotal() == null : this.getTotal().equals(other.getTotal()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getShopCarId() == null) ? 0 : getShopCarId().hashCode());
        result = prime * result + ((getGoodsName() == null) ? 0 : getGoodsName().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getCount() == null) ? 0 : getCount().hashCode());
        result = prime * result + ((getTotal() == null) ? 0 : getTotal().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append(" shopCarId=").append(shopCarId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", price=").append(price);
        sb.append(", count=").append(count);
        sb.append(", total=").append(total);
        sb.append(", userId=").append(userId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", weight=").append(weight);
        sb.append("]");
        return sb.toString();
    }
}