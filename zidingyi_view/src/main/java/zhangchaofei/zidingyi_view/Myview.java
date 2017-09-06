package zhangchaofei.zidingyi_view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by 张超飞 on 2017/9/6.
 */

public class Myview extends View {
    //圆弧线宽
    private float circleBorderWidth= TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,20,getResources().getDisplayMetrics());
    //内边距
    private float circlePadding=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,20,getResources().getDisplayMetrics());
    private float textsize=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,50,getResources().getDisplayMetrics());
    //绘制圆的画笔
    private Paint backCieclePaint;
    //绘制圆周白色分割线的画笔
    private Paint linePaint;
    private Paint textPaint;
    //百分比
    private int perent=3;
    //渐变圆颜色数组
    private int[] grdientColorArray=new int[]{Color.GREEN,Color.parseColor("#FE751a"),Color.parseColor("#13be23"),Color.GREEN};
    private Paint gradientCirclePaint;
    private boolean falg=true;
    private boolean falg1=true;
    private boolean flag2=true;


    public Myview(Context context) {
        super(context);
        init();
    }

    private void init() {
    }

    public Myview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Myview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

  
}
