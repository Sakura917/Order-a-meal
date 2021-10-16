package cn.itcast.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.order.R;
import cn.itcast.order.activity.ShopDetailActivity;
import cn.itcast.order.bean.ShopBean;

//店铺界面的列表由ShopListView控件显示，需创建该类对ShopListView控件进行数据适配
public class    ShopAdapter extends BaseAdapter {
    //定义上下文对象
    private Context mContext;
    //定义数据集
    private List<ShopBean> sbl = new ArrayList<>();
//    private List<ShopBean> sbl;
    //定义构造方法接收上下文参数
    public ShopAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * 设置数据更新界面
     */
    public void setData(List<ShopBean> sbl) {
        this.sbl.clear();
        this.sbl = sbl;//我们不能改变this.sb1的地址，否则会导致无法刷新数据
        //数据有变化，数据进行刷新
        notifyDataSetChanged();
    }
    /**
     * 获取Item的总数
     */
    @Override
    public int getCount() {
        return sbl == null ? 0 : sbl.size();
    }
    /**
     * 根据position得到对应Item的对象
     */
    @Override
    public ShopBean getItem(int position) {
        return sbl == null ? null : sbl.get(position);
    }
    /**
     * 根据position得到对应Item的id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     * 得到相应position对应的Item视图，position是当前Item的位置，
     * convertView参数是滚出屏幕的Item的View
     */

    //加载布局
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //利用ViewHolder 做优化
        final ViewHolder vh;
        //复用convertView
        if (convertView == null) {
            vh = new ViewHolder();
            //数据绑定，加载布局文件
            convertView=LayoutInflater.from(mContext).inflate(R.layout.shop_item,null);
            vh.tv_shop_name = (TextView) convertView.findViewById(R.id.tv_shop_name);
            vh.tv_sale_num = (TextView) convertView.findViewById(R.id.tv_sale_num);
            vh.tv_cost = (TextView) convertView.findViewById(R.id.tv_cost);
            vh.tv_welfare = (TextView) convertView.findViewById(R.id.tv_welfare);
            vh.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            vh.iv_shop_pic = (ImageView) convertView.findViewById(R.id.iv_shop_pic);
            //绑定数据到view中
            convertView.setTag(vh);
        } else {
            //获取vh
            vh = (ViewHolder) convertView.getTag();
        }
        //获取position对应的Item的数据对象,给控件设置值
        final ShopBean bean = getItem(position);
        if (bean != null) {
            //商品名称
            vh.tv_shop_name.setText(bean.getShopName());
            //销售额
            vh.tv_sale_num.setText("月售" + bean.getSaleNum());
            //起送+配送
            vh.tv_cost.setText("起送￥" + bean.getOfferPrice() + "|配送￥" +
                    bean.getDistributionCost());
            //配送时间
            vh.tv_time.setText(bean.getTime());
            //福利
            vh.tv_welfare.setText(bean.getWelfare());
            //利用第三方插件Glide 获取图片资源， error：图片不显示的话设置一个默认图片
            Glide.with(mContext)
                    .load(bean.getShopPic())
                    .error(R.mipmap.ic_launcher)
                    .into(vh.iv_shop_pic);
        }
        //每个Item的点击事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(mContext,bean.getShopName(),Toast.LENGTH_LONG).show();
                //跳转到店铺详情界面
                if (bean == null) return;
                //startActivity该方法属于上下文对象方法，需要获取上下文对象
                Intent intent = new Intent(mContext,ShopDetailActivity.class);
                //把店铺的详细信息传递到店铺详情界面，传入数据
                intent.putExtra("shop", bean);
                mContext.startActivity(intent);

            }
        });
        return convertView;
    }
    class ViewHolder {
        public TextView tv_shop_name, tv_sale_num, tv_cost, tv_welfare, tv_time;
        public ImageView iv_shop_pic;
    }
}
