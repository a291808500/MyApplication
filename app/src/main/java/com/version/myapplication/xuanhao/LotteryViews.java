package com.version.myapplication.xuanhao;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.version.myapplication.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



/**制作人陈小缘
 * ifxcyr@gmail.com
 * https://blog.csdn.net/u011387817
 * Created by wuyr on 17-12-9 下午7:21.
 */

public class LotteryViews extends ViewGroup {

    private String[] TEXT_TABLE = new String[]{"第一位", "第二位", "第三位", "第四位", "第五位", "第六位", "第七位"};
    private float mBorderMargin, //圆圈的margin距离
            mNumberTextSize, //数字的字体大小
            mBorderRadius, //圆圈的半径
            mBorderSize, //未选择状态下的圆圈的边框大小
            mIndexTextSize, //左边中文字体大小
            mIndexTextMarginLeft, //左边中文marginLeft
            mIndexTextMarginRight; //左边中文marginRight
    private int mIndexTextColor; //左边中文的字体颜色
    private int mIndex; //"一", "二", "三", "四", "五", "六", "七"
    private Item[] mItems; //0 ~ 9号码
    private int mSelectedIndex; //当前已选择的号码
    private Paint mPaint;
    private OnItemStateChangedListener mOnItemStateChangedListener;//选择有更新的回调

    public LotteryViews(Context context) {
        this(context, null);
    }

    public LotteryViews(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LotteryViews(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LotteryView, defStyleAttr, 0);
        mNumberTextSize = a.getDimension(R.styleable.LotteryView_number_text_size, 36);
        mBorderMargin = a.getDimension(R.styleable.LotteryView_border_margin, 16);
        mBorderRadius = a.getDimension(R.styleable.LotteryView_border_radius, 36);
        mBorderSize = a.getDimension(R.styleable.LotteryView_border_size, 3);
        mIndexTextSize = a.getDimension(R.styleable.LotteryView_index_text_size, 36);
        mIndexTextColor = a.getColor(R.styleable.LotteryView_index_text_color, Color.BLACK);
        mIndexTextMarginLeft = a.getDimension(R.styleable.LotteryView_index_text_margin_left, 64);
        mIndexTextMarginRight = a.getDimension(R.styleable.LotteryView_index_text_margin_right, 64);
        a.recycle();
        init();
    }

    public void setSelectedIndex(int index) {
        //当前已有选择时， 将状态设置为未选择
        if (mSelectedIndex > -1)
            mItems[mSelectedIndex].setState(Item.STATE_UNSELECTED);
        if (index < 0) {
            mSelectedIndex = -1;
        } else {
            //更新选择状态
            mSelectedIndex = index;
            mItems[index].setState(Item.STATE_SELECTED);
        }
    }

    private void init() {
        setWillNotDraw(false);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mIndexTextColor);
        mPaint.setTextSize(mIndexTextSize);
        //水平居中
        mPaint.setTextAlign(Paint.Align.CENTER);
        mSelectedIndex = -1;
        mItems = new Item[10];

        Item.OnItemStateChangedListener listener = new Item.OnItemStateChangedListener() {
            @Override
            public void onStateChanged(Item item, int index, int state) {
                //当前已有选择时， 将状态设置为未选择
                if (state == Item.STATE_SELECTED) {
                    if (mSelectedIndex > -1)
                        mItems[mSelectedIndex].setState(Item.STATE_UNSELECTED);
                    mSelectedIndex = index;
                } else mSelectedIndex = -1;
                if (mOnItemStateChangedListener != null)
                    //回调到中
                    mOnItemStateChangedListener.onStateChanged(mIndex,mSelectedIndex);
            }
        };
        //初始化0～9号码
        for (int i = 0; i < 10; i++) {
            Item item = new Item(getContext());
            item.setTextSize(mNumberTextSize);
            item.setStrokeWidth(mBorderSize);
            item.setOnItemStateChangedListener(listener);
            item.setIndex(i);
            mItems[i] = item;
            addView(item);
        }
    }

    public void setOnItemStateChangedListener(OnItemStateChangedListener listener) {
        mOnItemStateChangedListener = listener;
    }

    public interface OnItemStateChangedListener {
        void onStateChanged(int viewIndex, int itemIndex);
    }

    //排序0～9数字
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int currentWidth;
        int currentHeight = (int) mBorderMargin;
        int offset = (int) (mBorderMargin + mIndexTextMarginRight + mIndexTextMarginLeft);
        int size = (int) ((mBorderRadius * 2) + (mBorderSize * 2));
        //6之前的数字就在第一行 水平排
        for (int i = 0; i <10; i++) {
            currentWidth = (int) (offset + i * size + i * mBorderMargin);
            getChildAt(i).layout(currentWidth, currentHeight, currentWidth + size, currentHeight + size);
        }
        //6之后的数字就在第二行(加上一个圆圈的高度 和 两条边框的宽度)
        currentHeight = (int) (mBorderMargin * 2 + size);
        for (int i = 0; i < 4; i++) {
            int temp = 6 + i;
            currentWidth = (int) (offset + i * size + i * mBorderMargin);
            getChildAt(temp).layout(currentWidth, currentHeight, currentWidth + size, currentHeight + size);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //高度 = 两个圆圈的高度 + 三个圆圈margin + 两个圆圈的各两个边框的大小
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize((int) ((mBorderRadius * 4) + (mBorderMargin * 3) + (mBorderSize * 4))));
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画 左边中文， 位置X = 中文marginLeft + 中文marginRight Y = 圆圈高度的一半
        canvas.drawText(TEXT_TABLE[mIndex], (mIndexTextMarginLeft + mIndexTextMarginRight) / 2,
                (getHeight() - mPaint.getFontMetricsInt().bottom - mPaint.getFontMetricsInt().top) / 4 + mBorderMargin, mPaint);
    }

    //设置圆圈的margin距离
    public void setText_table(String[] text_table) {
        this.TEXT_TABLE = text_table;
    }
    public void setBorderMargin(float borderMargin) {
        this.mBorderMargin = borderMargin;
    }
    //设置数字的字体大小
    public void setNumberTextSize(float numberTextSize) {
        this.mNumberTextSize = numberTextSize;
    }

    //设置圆圈的半径
    public void setBorderRadius(float borderRadius) {
        this.mBorderRadius = borderRadius;
    }

    //设置未选择状态下的圆圈的边框大小
    public void setBorderSize(float borderSize) {
        this.mBorderSize = borderSize;
    }

    //设置左边中文字体大小
    public void setIndexTextSize(float indexTextSize) {
        this.mIndexTextSize = indexTextSize;
    }

    //设置左边中文marginLeft
    public void setIndexTextMarginLeft(float indexTextMarginLeft) {
        this.mIndexTextMarginLeft = indexTextMarginLeft;
    }

    //设置左边中文marginRight
    public void setIndexTextMarginRight(float indexTextMarginRight) {
        this.mIndexTextMarginRight = indexTextMarginRight;
    }

    //设置左边中文的字体颜色
    public void setIndexTextColor(@ColorInt int indexTextColor) {
        this.mIndexTextColor = indexTextColor;
    }

    /**
     * 自定义的0～9的号码 view
     */
    private static class Item extends View {

        @IntDef({STATE_UNSELECTED, STATE_SELECTED})
        @IntRange(from = STATE_UNSELECTED, to = STATE_SELECTED)
        @Retention(RetentionPolicy.SOURCE)
        private @interface STATE {
        }

        public static final int STATE_UNSELECTED = 0, STATE_SELECTED = 1;
        private int mCurrentStatus;//当前选择状态
        private Paint mPaint;
        private OnItemStateChangedListener mOnItemStateChangedListener;//状态更新的回调
        private int mIndex;

        public Item(Context context) {
            this(context, null);
        }

        public Item(Context context, @Nullable AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public Item(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        //设置状态
        void setState(@STATE int state) {
            mCurrentStatus = state;
            invalidate();
        }

        //设置数字 0～9
        void setIndex(int index) {
            mIndex = index;
        }

        //数字字体大小
        void setTextSize(float textSize) {
            mPaint.setTextSize(textSize);
        }

        //圆圈边框大小
        void setStrokeWidth(float width) {
            mPaint.setStrokeWidth(width);
        }

        private void init() {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setTextAlign(Paint.Align.CENTER);
            setTextSize(36);
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //号码被点击时， 更新状态
                    setState(mCurrentStatus == STATE_UNSELECTED ? STATE_SELECTED : STATE_UNSELECTED);
                    if (mOnItemStateChangedListener != null)
                        mOnItemStateChangedListener.onStateChanged(Item.this, mIndex, mCurrentStatus);
                }
            });
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int offset = getWidth() / 2;
            int radius = (int) ((getWidth() - mPaint.getStrokeWidth()) / 2);
            if (mCurrentStatus == STATE_SELECTED) {
                //如果是选择状态，就画一个红色的圆形， 然后再画白色数字
                mPaint.setColor(Color.RED);
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(offset, offset, radius, mPaint);
                mPaint.setColor(Color.WHITE);
            } else {
                //如果是未选择状态， 就画一个圆圈的边框， 然后画红色数字
                mPaint.setColor(Color.parseColor("#cccccc"));
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(offset, offset, radius, mPaint);
                mPaint.setColor(Color.BLACK);
//                mPaint.setColor(Color.RED);
//                mPaint.setStyle(Paint.Style.STROKE);
//                canvas.drawCircle(offset, offset, radius, mPaint);
//                mPaint.setStyle(Paint.Style.FILL);
            }
            canvas.drawText(String.valueOf(mIndex), offset, (getHeight() - mPaint.getFontMetricsInt().bottom - mPaint.getFontMetricsInt().top) / 2, mPaint);
        }

        //设置回调
        public void setOnItemStateChangedListener(OnItemStateChangedListener listener) {
            mOnItemStateChangedListener = listener;
        }

        public interface OnItemStateChangedListener {
            void onStateChanged(Item item, int index, int state);
        }
    }
}
