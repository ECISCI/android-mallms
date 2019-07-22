package com.mincat.mobilemallmanager.entity;

public class GoodsListRequest {


    private String id;

    private String title;

    private String desc;

    private String category;

    private String mainImage;

    private String descImage;

    private String price;

    private String reserveNum;

    private String carriage;

    private Integer discount;

    private String returnGoodsType;

    private Integer cid;

    private String status;

    private String createTime;

    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getDescImage() {
        return descImage;
    }

    public void setDescImage(String descImage) {
        this.descImage = descImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReserveNum() {
        return reserveNum;
    }

    public void setReserveNum(String reserveNum) {
        this.reserveNum = reserveNum;
    }

    public String getCarriage() {
        return carriage;
    }

    public void setCarriage(String carriage) {
        this.carriage = carriage;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getReturnGoodsType() {
        return returnGoodsType;
    }

    public void setReturnGoodsType(String returnGoodsType) {
        this.returnGoodsType = returnGoodsType;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "GoodsListRequest{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", category='" + category + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", descImage='" + descImage + '\'' +
                ", price='" + price + '\'' +
                ", reserveNum='" + reserveNum + '\'' +
                ", carriage='" + carriage + '\'' +
                ", discount=" + discount +
                ", returnGoodsType='" + returnGoodsType + '\'' +
                ", cid=" + cid +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
