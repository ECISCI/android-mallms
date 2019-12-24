package com.mincat.mobilemallmanager.entity.pics;

public class PicsRes {

    /**
     * 图片url地址
     */
    private String imageUrl;
    /**
     * 图片文字描述
     */
    private String imageDesc;
    /**
     * 图片链接的URL地址
     */
    private String imageLink;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public String toString() {
        return "PicsRes{" +
                "imageUrl='" + imageUrl + '\'' +
                ", imageDesc='" + imageDesc + '\'' +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
