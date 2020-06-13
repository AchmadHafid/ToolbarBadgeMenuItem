ToolbarBadgeMenuItem
====================

[![Release](https://jitpack.io/v/AchmadHafid/toolbar-badge-menu-item.svg)](https://jitpack.io/#AchmadHafid/toolbar-badge-menu-item)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

**Assalamu'alaikum brothers and sisters, peace be upon you!**

Add badge to your toolbar menu item easily by using this library

![image](https://github.com/AchmadHafid/ToolbarBadgeMenuItem/blob/master/art/demo.gif)
<br />
[Download Demo App Here](https://github.com/AchmadHafid/ToolbarBadgeMenuItem/releases/download/v3.2.1/ToolbarBadgeMenuItem.v3.2.1.apk)


Main Features
--------
* Create menu item badge by simply using extension function
* Support Activity & Fragment
* Full support for color theming (e.g. light & dark theme from Material Components)


Compatibility
-------------

This library is compatible from API 21 (Android 5.0 Lollipop) & AndroidX.



Download
--------

Add jitpack repository into your root build.gradle

```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
    ...
  }
}
```

Add the dependency

```groovy
dependencies {
  ...
  implementation 'com.github.AchmadHafid:toolbar-badge-menu-item:3.4.0'
  ...
}
```



Usage
-----

1. Set your toolbar menu item action layout like below

``` xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    ...

    <item
        ...
        app:actionLayout="@layout/toolbar_badge_menu_item"
        app:showAsAction="always" />

    ...

</menu>
```

2. Override **```onPrepareOptionsMenu```** to build badges

``` kotlin
override fun onPrepareOptionsMenu(menu: Menu?) = createToolbarBadge(menu) {
    addItem(R.id.item_1, R.drawable.icon_1, totalCount1)
    addItem(R.id.item_2, R.drawable.icon_2) { totalCount2 } // can also use lambda
    withColor {
        /** can also use a plain color resource (e.g. R.color.my_color) */
        textRes       = R.attr.colorSurface
        backgroundRes = R.attr.colorPrimary
        iconTintRes   = R.attr.colorOnSurface // or null (no tint)
    }
}

```

3. Optionally You can change the default value for `textColorRes`, `backgroundColorRes` and `iconTintRes` by using extension function available on `Application`

```kotlin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        setDefaultToolbarBadgeMenuItemColor {
            textRes       = R.attr.colorSurface   // default value
            backgroundRes = R.attr.colorPrimary   // default value
            iconTintRes   = R.attr.colorOnSurface // default value
        }
    }
}

```


__That's it! May this library ease your Android development task__


License
-------

    Copyright 2019 Achmad Hafid

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
