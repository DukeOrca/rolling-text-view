# RollingTextView
[![](https://jitpack.io/v/wing-tree/rolling-text-view.svg)](https://jitpack.io/#wing-tree/rolling-text-view)

RollingTextView works like this:

Only integer types are supported,
The following properties are supported.
```
<attr name="duration" format="integer" />
<attr name="android:fontFamily" />
<attr name="android:textColor" />
<attr name="android:textSize" />
<attr name="android:textStyle" />
```
**Step 1.** Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
**Step 2.** Add the dependency
```
	dependencies {
	        implementation 'com.github.wing-tree:rolling-text-view:1.0.0'
	}
```
