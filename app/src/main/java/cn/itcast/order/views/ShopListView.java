package cn.itcast.order.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
//由于上方的图片也需要和下面的列表一起滑动
// 显示列表的ListView控件包含在ScrollView中，嵌套式会导致数据显示不完整
//需要更改子控件测量的方法，将UNSPECIFIED方法改为MeasureSpec.AT_MOST，该方法会重新计算高度，遍历所有的子控件将所有的高度加起来
public class ShopListView extends ListView {
    public ShopListView(Context context) {
        super(context);
    }
    public ShopListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ShopListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    // 复写该方法，更改测量模式
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //view的大小是用30位进行存储，那么我们应该给他30位的最大值，Int的最大值是32，
        // 所以需要右移两位改为30，然后进入AT_MOST模式，计算所有子控件高度的和
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}