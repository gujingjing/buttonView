package gjj_unit_test.buttondemo;

import android.animation.LayoutTransition;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapSize;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：gjj on 2016/3/24 11:40
 * 邮箱：Gujj512@163.com
 */
public class ButtonView extends LinearLayout {

    private int[] colors = new int[]{R.color.color1, R.color.color2, R.color.color3, R.color.color4, R.color.color5, R.color.color6};
    private String[] tips = getResources().getStringArray(R.array.button_tips);

    private Context context;
    private int screenWidth;//屏幕的宽高
    private int btnNumber;//button的个数
    int viewWidth;//每个button的宽
    int textWidth;//每个文字的宽
    private List<BootstrapButton> listViews;
    private int padding=dipToPx(4);//距离左右的距离
    private int textSize=12;//button中text大小
    private int position=-1;
    private double bili=1.2;//button放大的比例
    private double height_B=0.6;//宽高的比例
    private double long_textSize=1.6;//长textview的字体大小比例
    private int text_select_size=2;//呗选中后的字体增加多少
    private int view_padding=dipToPx(20);
    private double[] numbers;
    /**
     * DefaultBootstrapSize.SM;
     * size = DefaultBootstrapSize.MD;
     * size = DefaultBootstrapSize.LG;
     * size = DefaultBootstrapSize.XL;
     * size = DefaultBootstrapSize.XS
     */
    private DefaultBootstrapSize size = DefaultBootstrapSize.MD;//button大小

    public ButtonView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public void init() {
        btnNumber = colors.length;
        screenWidth = ScreenUtils.getScreenWidth(context)-view_padding;
//        viewWidth = screenWidth / btnNumber;
        viewWidth = (int)((screenWidth-padding*5) / (btnNumber));

        textSize=(int)( 20*height_B);

        this.setOrientation(LinearLayout.VERTICAL);//设置方向横向
        this.setGravity(Gravity.CENTER);
        initButton(position);
    }

    public void initButton(int position) {
        //初始化所有的都清空
        listViews = new ArrayList<>();
        this.removeAllViews();

        LinearLayout linearLayout1=new LinearLayout(context);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);//设置方向横向
        linearLayout1.setGravity(Gravity.CENTER);

        if(position!=-1){
            viewWidth = (int)((screenWidth-padding*5) / (btnNumber+0.5));
        }
        for (int i = 0; i < colors.length; i++) {
            BootstrapButton button = new BootstrapButton(context);
            if(position==i+1){
                if(tips[i].length()>2){
                    LayoutParams params = new LayoutParams((int)( viewWidth*bili+(int)(padding*2.5)), (int) (viewWidth*bili*height_B));
                    params.setMargins(padding, 0, padding, 0);
                    button.setLayoutParams(params);
                    button.setTextSize(tips[i].length()>2?(int)( textSize/(long_textSize)+text_select_size+2):textSize+text_select_size);
                }else{
                    LayoutParams params = new LayoutParams((int)( viewWidth*bili), (int) (viewWidth*bili*height_B));
                    params.setMargins(padding, 0, padding, 0);
                    button.setLayoutParams(params);
                    button.setTextSize(tips[i].length()>2?(int)( textSize/(long_textSize)+text_select_size+2):textSize+text_select_size);
                }
            }else{
                if(tips[i].length()>2){
                    LayoutParams params = new LayoutParams(viewWidth+(int)(padding*2.5), (int) (viewWidth*height_B));
//            params.setMargins(padding, 0, padding, 0);
                    button.setLayoutParams(params);
                    button.setTextSize(tips[i].length()>2?(int)( textSize/(long_textSize)+2):textSize);
                }else{
                    LayoutParams params = new LayoutParams(viewWidth, (int) (viewWidth*height_B));
//            params.setMargins(padding, 0, padding, 0);
                    button.setLayoutParams(params);
                    button.setTextSize(tips[i].length()>2?(int)( textSize/(long_textSize)):textSize);
                }
            }
            button.setGravity(Gravity.CENTER);
            button.setText(tips[i]);
            button.setBackgroundResource(colors[i]);

            //保存创建的view
            listViews.add(button);
            //添加button
            linearLayout1.addView(button);
        }
        this.addView(linearLayout1);

        //创建数字显示
        LinearLayout linearLayout2=new LinearLayout(context);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);//设置方向横向

        textWidth=(int)((screenWidth-padding*5) / (btnNumber+0.5));
        for (int i=0;i<colors.length;i++){
            TextView textView=new TextView(context);
        }

    }

    public void setButtonShow(int position) {
//        if (listViews == null) {
            initButton(position);
//        }
//        try {
//            BootstrapButton button = listViews.get(position);
//            if (button == null) {
//                Log.e("ButtonView====", "获取Button控件为空");
//            } else {
//                LayoutParams params = new LayoutParams(viewWidth, LayoutParams.WRAP_CONTENT);
//                params.setMargins(padding, 0, padding, 0);
//                button.setLayoutParams(params);
//                button.setBootstrapSize(DefaultBootstrapSize.LG);//设置button的大小变化
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.e("ButtonView====", "获取Button控件错误");
//        }
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
