package com.hincky.routesys.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * goods
 * @author 
 */
@Data
public class Goods implements Serializable {
    private Integer goodsId;

    private String goodsName;

    private Float price;

    private String onSale;

    private String detail;

    private Integer count;

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
        Goods other = (Goods) that;
        return (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getGoodsName() == null ? other.getGoodsName() == null : this.getGoodsName().equals(other.getGoodsName()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getOnSale() == null ? other.getOnSale() == null : this.getOnSale().equals(other.getOnSale()))
            && (this.getDetail() == null ? other.getDetail() == null : this.getDetail().equals(other.getDetail()))
            && (this.getCount() == null ? other.getCount() == null : this.getCount().equals(other.getCount()))
            && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getGoodsName() == null) ? 0 : getGoodsName().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getOnSale() == null) ? 0 : getOnSale().hashCode());
        result = prime * result + ((getDetail() == null) ? 0 : getDetail().hashCode());
        result = prime * result + ((getCount() == null) ? 0 : getCount().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append(" goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", price=").append(price);
        sb.append(", onSale=").append(onSale);
        sb.append(", detail=").append(detail);
        sb.append(", count=").append(count);
        sb.append(", weight=").append(weight);
        sb.append("]");
        return sb.toString();
    }
}