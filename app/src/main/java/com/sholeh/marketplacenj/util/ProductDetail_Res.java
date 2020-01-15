package blueappsoftware.shopeasy.beanResponse;

/**
 * Created by kamal_bunkar on 19-01-2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetail_Res {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("Information")
    @Expose
    private Information information;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }


    public class Relatedprod {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("mrp")
        @Expose
        private String mrp;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("rating")
        @Expose
        private float rating;
        @SerializedName("img_url")
        @Expose
        private String imgUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMrp() {
            return mrp;
        }

        public void setMrp(String mrp) {
            this.mrp = mrp;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public float getRating() {
            return rating;
        }

        public void setRating(float rating) {
            this.rating = rating;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

    }

    public class Information {

        @SerializedName("details")
        @Expose
        private Details details;
        @SerializedName("review")
        @Expose
        private String review;
        @SerializedName("relatedprod")
        @Expose
        private List<Relatedprod> relatedprod = null;

        public Details getDetails() {
            return details;
        }

        public void setDetails(Details details) {
            this.details = details;
        }

        public String getReview() {
            return review;
        }

        public void setReview(String review) {
            this.review = review;
        }

        public List<Relatedprod> getRelatedprod() {
            return relatedprod;
        }

        public void setRelatedprod(List<Relatedprod> relatedprod) {
            this.relatedprod = relatedprod;
        }

    }


    public class Details {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("desc")
        @Expose
        private String desc;
        @SerializedName("mrp")
        @Expose
        private String mrp;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("rating")
        @Expose
        private float rating;
        @SerializedName("rating_count")
        @Expose
        private String ratingCount;
        @SerializedName("img_url")
        @Expose
        private String imgUrl;

        @SerializedName("fulldetail")
        @Expose
        private String fulldetails;
        @SerializedName("stock")
        @Expose
        private Integer stock;


        public String getFulldetails() {
            return fulldetails;
        }

        public void setFulldetails(String fulldetails) {
            this.fulldetails = fulldetails;
        }


        public Integer getStock() {
            return stock;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getMrp() {
            return mrp;
        }

        public void setMrp(String mrp) {
            this.mrp = mrp;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public float getRating() {
            return rating;
        }

        public void setRating(float rating) {
            this.rating = rating;
        }

        public String getRatingCount() {
            return ratingCount;
        }

        public void setRatingCount(String ratingCount) {
            this.ratingCount = ratingCount;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

    }
}