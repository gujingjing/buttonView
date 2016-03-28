package gjj_unit_test.buttondemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapSize;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：gjj on 2016/3/24 11:40
 * 邮箱：Gujj512@163.com
 */
public class ButtonViewViewNumber extends LinearLayout {

    private int[] colors = new int[]{R.color.color1, R.color.color2, R.color.color3, R.color.color4,
//             R.color.color5,
            R.color.color6};
    private String[] tips ;

    private Context context;
    private int screenWidth;//屏幕的宽高
    private int btnNumber;//button的个数
    int viewWidth;//每个button的宽
    private List<TextView> listViews;
    private int padding=dipToPx(4);//距离左右的距离
    private int textSize=12;//button中text大小
    private int position=-1;
    private double bili=1.2;//button放大的比例
    private double height_B=0.6;//宽高的比例
    private double long_textSize=1.2;//长textview的字体大小比例
    private int text_select_size=2;//呗选中后的字体增加多少
    private int view_padding;
    private int topPadding=dipToPx(10);//文字距离顶部的距离
    private double[] numbers;
    private int length;
    private  boolean showNumber=false;
    /**
     * DefaultBootstrapSize.SM;
     * size = DefaultBootstrapSize.MD;
     * size = DefaultBootstrapSize.LG;
     * size = DefaultBootstrapSize.XL;
     * size = DefaultBootstrapSize.XS
     */
    private DefaultBootstrapSize size = DefaultBootstrapSize.MD;//button大小

    public ButtonViewViewNumber(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ButtonViewViewNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.buttonView);
        view_padding=a.getDimensionPixelOffset(R.styleable.buttonView_marging_width,0);
        showNumber=a.getBoolean(R.styleable.buttonView_showNumber,false);

        btnNumber = colors.length;
        screenWidth = ScreenUtils.getScreenWidth(context)-dipToPx(view_padding)-padding*2;
//        viewWidth = screenWidth / btnNumber;
        viewWidth = (int)(screenWidth / (btnNumber+(1-bili)));
        textSize=(int)( 20*height_B);
        init();
    }

    public void init() {
        tips=new String[]{"偏低","正常","正常高值","轻度","重度"};
        numbers=new double[]{18.5,24.0,28.0,50};
//        = getResources().getStringArray(R.array.button_tips)



        this.setOrientation(LinearLayout.VERTICAL);//设置方向横向
        this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
//        this.setGravity(Gravity.CENTER);
        initButton(position);
    }

    public void initButton(int position) {
        //初始化所有的都清空
        listViews = new ArrayList<>();
        this.removeAllViews();

        length=0;

        LinearLayout linearLayout1=new LinearLayout(context);
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);//设置方向横向
        linearLayout1.setGravity(Gravity.CENTER);



//        if(position!=-1){
//            viewWidth = (int)((screenWidth-padding*2) / (btnNumber+0.5));
//        }
        for (int i = 0; i < colors.length; i++) {

            TextView textView=new TextView(context);
            if(position==i+1){
                LayoutParams params = new LayoutParams((int)( viewWidth*bili), (int) (( viewWidth*bili)*height_B));
                params.setMargins(padding, 0, padding, 0);
                textView.setLayoutParams(params);
                textView.setTextSize(tips[i].length()>2?(int)(textSize/long_textSize):textSize+2);
//            textView.setPadding(4,0,4,0);
            }else{
                LayoutParams params = new LayoutParams((int)( viewWidth), (int) (viewWidth*height_B));
//            params.setMargins(padding, 0, padding, 0);
                textView.setLayoutParams(params);
                textView.setTextSize(tips[i].length()>2?(int)(textSize/long_textSize):textSize);
//            textView.setPadding(4,0,4,0);
            }
            textView.setGravity(Gravity.CENTER);
            textView.setText(tips[i]);
            textView.setBackgroundResource(colors[i]);
            textView.setTextColor(Color.WHITE);

            //保存创建的view
            listViews.add(textView);
            //添加button
            linearLayout1.addView(textView);

        }
        this.addView(linearLayout1);

        if(showNumber){
            //添加文字
            LinearLayout linearLayout2=new LinearLayout(context);
            linearLayout2.setOrientation(LinearLayout.HORIZONTAL);//设置方向横向
            LayoutParams layoutParams=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            //textview设置距离左边为半个button的距离
            layoutParams.setMargins((viewWidth/2)+padding*4,topPadding,0,0);
            linearLayout2.setLayoutParams(layoutParams);

            for (int i=0;i<numbers.length;i++){
                addView(linearLayout2,i,position);
            }
            this.addView(linearLayout2);
        }
    }

    public void addView(LinearLayout linearLayout2,int i,int position){
        TextView textView2=new TextView(context);
        LayoutParams params = new LayoutParams((int)( viewWidth),LayoutParams.WRAP_CONTENT);
        if(i==position-1){
            params.setMargins(padding,0,padding,0);
        }
        textView2.setLayoutParams(params);
        textView2.setGravity(Gravity.CENTER);
        textView2.setText(numbers[i]+"");
        textView2.setTextColor(context.getResources().getColor(R.color.text_color));
        textView2.setTextSize(12);

        linearLayout2.addView(textView2);
    }
    public void setButtonShow(int position) {
            initButton(position);
    }
    public void setShowNumber(boolean showNumber,int position){
        this.showNumber=showNumber;
        setButtonShow(position);
    }

    /**
     * dip 转换成px
     * @param dip
     * @return
     */
    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int)(dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }
}
