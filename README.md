[![](https://jitpack.io/v/gujingjing/buttonView.svg)](https://jitpack.io/#gujingjing/buttonView)
最近项目自定义的控件比较多，自己也学习了不少，以前觉得自定义很难，研究捣鼓了一段时间，感觉也就那么回事，无非是要计算和绘制，先上效果图


![肥胖程度.png](http://upload-images.jianshu.io/upload_images/1387450-e79c6af604aef868.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


###思路:
自定义view继承 <code>LinearLayout</code>,里面再包裹两个LinearLayout
1. 第一个LinearLayout里面用来存放带颜色的<code>textview</code>显示肥胖的描述
2. 第二个LinearLayout里面用来存放肥胖指数的


###第一步初始化设置LinearLayout的属性
         public void init() {
          //        tips = context.getResources().getStringArray(R.array.button_tips);
        tips=new String[]{"偏低","正常","正常高值","轻度",
        //                "中度",
                "重度"};
        this.setOrientation(LinearLayout.HORIZONTAL);//设置方向横向
        this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        this.setGravity(Gravity.CENTER);
        initButton(position);
        }

###主要代码在initButton(int position)中
1. 设置第一个LinearLayout的属性

        //初始化所有的都清空
        listViews = new ArrayList<>();
        this.removeAllViews();

        length=0;

        LinearLayout linearLayout1=new LinearLayout(context);
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);//设置方向横向
        linearLayout1.setGravity(Gravity.CENTER);


2. 添加有颜色的文字描述
   
  每个textview显示的区域是屏幕平分的，当我们需要设置的模块是第position个的时候，把对应模块的前后加上padding,其余的正常显示

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


3. 添加显示数值的LinearLayout

        1. 把屏幕分等分，和上面的颜色分等分一样，而且宽度也一样
        2. 让左边偏移每个view宽度的一半
        3. 让textview居中显示



![说明.png](http://upload-images.jianshu.io/upload_images/1387450-b2d57e8d5f1a593b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


最后附上[github项目](https://github.com/gujingjing/buttonView.git)
