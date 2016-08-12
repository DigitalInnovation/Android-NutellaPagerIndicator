# Android-NutellaPagerIndicator
An animated ViewPager indicator.

This indicator uses image clipping for the transition effect.
It is fully configurable with XML, where you can set the inactive, active indicator colour (or grab the default settings, including the accent from theme)
If the ViewPager has just a single item, the indicator is hidden.

Example:

![PongPagerIndicator sample](https://raw.githubusercontent.com/DigitalInnovation/Android-NutellaPagerIndicator/master/ezgif-2664577401.gif)

## Usage

### Initialise with the ViewPager instance:

```
  ViewPager viewpager = configureViewPager();
  NutellaPagerIndicator indicator = (NutellaPagerIndicator) findViewById(R.id.indicator);
  indicator.initWithViewPager(viewPager);
```

### Layout XML:

```
<com.mandsdigital.nutellaPagerIndicator.NutellaPagerIndicator
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
