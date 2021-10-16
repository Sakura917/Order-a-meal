package cn.itcast.order.utils;
//由于该项目中的数据需通过网络请求的方式从Tomcat上获取，所以要创建请求数据的接口地址
public class Constant {
    public static final String WEB_SITE = "http://192.168.1.103:8080/order";//内网接口（站点信息）
    public static final String REQUEST_SHOP_URL = "/shop_list_data.json";  //店铺列表接口（资源信息）
}

