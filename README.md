# Android-PongPagerIndicator
An animated ViewPager indicator.

This indicator uses image clipping for the transition effect.
It is fully configurable with XML, where you can set the inactive, active indicator colour (or grab the default settings, including the accent from theme)
If the ViewPager has just a single item, the indicator is hidden.

Example:

![PongPagerIndicator sample](http://i.imgur.com/PXFdVqM.gifv)

## Usage

### Initialise with the ViewPager instance:

```
  ViewPager viewpager = configureViewPager();
  PongPagerIndicator indicator = (PongPagerIndicator) findViewById(R.id.indicator);
  indicator.initWithViewPager(viewPager);
```

### Layout XML:

```
<com.mandsdigital.pongpager.PongPagerIndicator
        android:id="@+id/configured_indicator"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"

        app:barHeight="6dp"
        app:barWidth="40dp"
        app:spaceWidth="8dp"
        app:emptyBarColor="@android:color/holo_blue_dark"
        app:activeIndicatorColor="@android:color/holo_green_dark"
        />
```
