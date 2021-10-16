package cn.itcast.order.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import cn.itcast.order.bean.ShopBean;
/**
 * 单例（解析类）
 *
 * */
public class JsonParse {
    //3.定义一个静态的对象
    private static JsonParse instance;
    //1.将构造方法私有化，让别人没法new
    private JsonParse() {
    }
    //2.提供一个方法获取实例,为了防止多线程时，两个线程同时进入，获取的值都为空
    public synchronized static JsonParse getInstance() {
        if (instance == null) {
            //实例化
            instance = new JsonParse();
        }
    //返回实例
        return instance;
    }
    //用list装所有的ShopBean，需要用gson库解析json数据
    public List<ShopBean> getShopList(String json) {
        Gson gson = new Gson(); // 使用gson库解析JSON数据
        // 创建一个TypeToken的匿名子类对象，并调用对象的getType()方法
        Type listType = new TypeToken<List<ShopBean>>() {
        }.getType();
        // 把获取到的信息集合存到shopList中
        List<ShopBean> shopList = gson.fromJson(json, listType);
        return shopList;
    }
}
