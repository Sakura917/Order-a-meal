package cn.itcast.order.activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.List;

import cn.itcast.order.R;
import cn.itcast.order.adapter.ShopAdapter;
import cn.itcast.order.bean.ShopBean;
import cn.itcast.order.utils.Constant;
import cn.itcast.order.utils.JsonParse;
import cn.itcast.order.views.ShopListView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//将列表数据展示出来
public class ShopActivity extends AppCompatActivity {
    private TextView tv_back,tv_title;         //返回键与标题控件
    private ShopListView slv_list;              //列表控件
    private ShopAdapter adapter;                //列表的适配器
    public static final int MSG_SHOP_OK = 1; //获取数据
    private MHandler mHandler;
    private RelativeLayout title_bar;//标题栏
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        mHandler=new MHandler();
        //该方法用于传数据
        initData();
        init();
    }
    /**
     * 初始化界面控件
     */
    private void init(){
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        //设置标题栏文字
        tv_title.setText("店铺");
        //获取标题栏控件
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        //设置标题栏颜色
        title_bar.setBackgroundColor(getResources().getColor(R.color.blue_color));
        tv_back.setVisibility(View.GONE);
        slv_list= (ShopListView) findViewById(R.id.slv_list);
        //用ShopAdapter把数据传入listview中
        adapter=new ShopAdapter(this);
        slv_list.setAdapter(adapter);
    }
    //数据的获取，网络请求用okhttp
    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        //获取请求地址
        Request request = new Request.Builder().url(Constant.WEB_SITE +
                Constant.REQUEST_SHOP_URL).build();
        //发起请求
        Call call = okHttpClient.newCall(request);
        // 开启异步线程访问网络
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string(); //获取店铺数据
                Message msg = new Message();
                msg.what = MSG_SHOP_OK;
                msg.obj = res;
                mHandler.sendMessage(msg); //传递至主线程
            }
            @Override
            public void onFailure(Call call, IOException e) {

            }
        });
    }
    /**
     * 事件捕获，创建Handler消息通信
     */
    class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case MSG_SHOP_OK:
                    if (msg.obj != null) {
                        String vlResult = (String) msg.obj;
                        //解析获取的JSON数据
                        List<ShopBean> pythonList = JsonParse.getInstance().
                                getShopList(vlResult);
                        adapter.setData(pythonList);
                    }
                    break;
            }
        }
    }
    protected long exitTime;//记录第一次点击时的时间
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(ShopActivity.this, "再按一次退出订餐应用",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                ShopActivity.this.finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
