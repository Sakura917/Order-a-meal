package cn.itcast.order.bean;

import java.io.Serializable;
import java.math.BigDecimal;
//封装菜单信息所有属性：
// 进行序列化，创建该类实现Serializable接口，该类定义了每个菜的所有属性
public class FoodBean implements Serializable {
    private static final long serialVersionUID = 1L; //序列化时保持FoodBean类版本的兼容性
    private int foodId;         //菜的id
    private String foodName;   //菜的名称
    private String taste;      //菜的口味
    private int saleNum;       //月售量
    private BigDecimal price; //价格  BigDecimal：针对大的浮点数，是以字符串的方式传入的
    private int count;         //添加到购物车中的数量
    private String foodPic;   //菜的图片

    //一定要写set方法，因为我们要用gson做数据解析，gson的数据解析是通过反射来做的，反射的时候调用setXXX方法

    public int getFoodId() {
        return foodId;
    }
    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public String getTaste() {
        return taste;
    }
    public void setTaste(String taste) {
        this.taste = taste;
    }
    public int getSaleNum() {
        return saleNum;
    }
    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getFoodPic() {
        return foodPic;
    }
    public void setFoodPic(String foodPic) {
        this.foodPic = foodPic;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
