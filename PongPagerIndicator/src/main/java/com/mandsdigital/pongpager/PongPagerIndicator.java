package com.mandsdigital.pongpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class PongPagerIndicator extends FrameLayout {


    public PongPagerIndicator(Context context) {
        this(context, null);
    }

    private int barHeight;
    private int barWidth;
    private int spaceWidth;
    private int emptyBarColor;
    private int activeIndicatorColor;

    private int numberOfItems;
    private int singlePathLength;
    private View activeIndicator;
    private ViewPager viewPager;
    private ViewPager.OnPageChangeListener pageChangeListener;
    private Paint emptyPaint;

    @SuppressLint("Deprecation")
    public PongPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray chosenParams = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PongPagerIndicator, 0, 0);
        Resources defaultRes = context.getResources();
        barHeight = chosenParams.getDimensionPixelSize(R.styleable.PongPagerIndicator_barHeight, defaultRes.getDimensionPixelSize(R.dimen.default_bar_height));
        barWidth = chosenParams.getDimensionPixelSize(R.styleable.PongPagerIndicator_barWidth, defaultRes.getDimensionPixelSize(R.dimen.default_bar_width));
        spaceWidth = chosenParams.getDimensionPixelSize(R.styleable.PongPagerIndicator_spaceWidth, defaultRes.getDimensionPixelSize(R.dimen.default_space_width));

        int defaultEmptyColor;
        int defaultActiveColor;
        TypedValue themeDefault = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorAccent, themeDefault, true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            defaultEmptyColor = defaultRes.getColor(R.color.empty_indicator, context.getTheme());
        } else {
            defaultEmptyColor = defaultRes.getColor(R.color.empty_indicator);
        }
        defaultActiveColor = themeDefault.data;

        emptyBarColor = chosenParams.getColor(R.styleable.PongPagerIndicator_emptyBarColor, defaultEmptyColor);
        activeIndicatorColor = chosenParams.getColor(R.styleable.PongPagerIndicator_activeIndicatorColor, defaultActiveColor);
        emptyPaint = new Paint();
        emptyPaint.setColor(emptyBarColor);

        singlePathLength = barWidth + spaceWidth;
        activeIndicator = new View(context);
        activeIndicator.setLayoutParams(new FrameLayout.LayoutParams(barWidth, barHeight));
        activeIndicator.setBackgroundColor(activeIndicatorColor);
        addView(activeIndicator);

        setWillNotDraw(false);
    }

    public void initWithViewPager(ViewPager viewPager){
        numberOfItems = viewPager.getAdapter().getCount();
        this.viewPager = viewPager;

        if(numberOfItems <= 1){
            numberOfItems = 0;
        }

        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = barWidth * numberOfItems + spaceWidth * (numberOfItems -1);
        layoutParams.height = barHeight;
        setLayoutParams(layoutParams);

        invalidate();

        pageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                float translation = position * singlePathLength + positionOffset * singlePathLength;
                activeIndicator.setTranslationX(translation);
            }
            @Override
            public void onPageSelected(int ignored) {}
            @Override
            public void onPageScrollStateChanged(int ignored) {}
        };
        viewPager.addOnPageChangeListener(pageChangeListener);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        emptyPaint.setColor(emptyBarColor);
        Path path = createPath(numberOfItems);
        canvas.drawPath(path, emptyPaint);
        canvas.clipPath(path);
    }

    private Path createPath(int numberOfRectangles) {
        Path path = new Path();
        for (int i = 0; i < numberOfRectangles; i++) {
            int x0 = (i * barWidth) + (i * spaceWidth);
            int y0 = 0;

            int x1 = x0 + barWidth;
            int y1 = barHeight;
            path.addRect(x0, y0, x1, y1, Path.Direction.CW);
        }
        return path;
    }
}
