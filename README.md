# ScalingAnimationDroid
[![](https://img.shields.io/badge/Platform-Android-blue.svg)](https://jitpack.io/#annchar/ScalingAnimationDroid)
[![](https://jitpack.io/v/annchar/ScalingAnimationDroid.svg)](https://jitpack.io/#annchar/ScalingAnimationDroid)
[![](https://img.shields.io/badge/License-Apache_v2.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

<p align="center">
<img src="./screenshot/ScalingAnimationDroid.gif" width="500" height="800" />
</p>


## Getting Started
### Installation Gradle:
Add the following to your project level build.gradle:

```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

Add this to your app build.gradle:

```
dependencies {
    implementation 'com.github.annchar:ScalingAnimationDroid:1.0.0'
}
```

## Usage
`ScalingAnimationDroid` is really easy to use. The library gives to your Views a beautiful touch effect.

### Kotlin
Some of material widgets needs to be casted to View, otherwise it will show `Type mismatch` warning.
```Kotlin
  ScalingAnimationDroid(textview_title) // Use attributes default
  ScalingAnimationDroid(button_search) // Use attributes default
  
  // Custom Attributes
  ScalingAnimationDroid(card_view1).apply {
            setScalingAnimationType(ScalingAnimationType.SCALING_IN)
            setDurationActionDown(400)
            setDurationActionUp(400)
            setScalingPadding(0.8f)
  }
  
  // OR
  val animateView = ScalingAnimationDroid(card_view1)
  animateView.setScalingAnimationType(ScalingAnimationType.SCALING_IN)
  animateView.setDurationActionDown(400)
  animateView.setDurationActionUp(400)
  animateView.setScalingPadding(0.8f)
```
### JAVA
After views are binded simply pass the name of widget to `ScalingAnimationDroid` constructor. 
```java
  new ScalingAnimationDroid(textview_title); // Use attributes default
  new ScalingAnimationDroid(button_search); // Use attributes default
  
  // Custom Attributes
  ScalingAnimationDroid animateView = new ScalingAnimationDroid(card_view1);
  animateView.setScalingAnimationType(ScalingAnimationType.SCALING_IN);
  animateView.setDurationActionDown(400);
  animateView.setDurationActionUp(400);
  animateView.setScalingPadding(0.8f);
```

## Config Attributes
|            Attribute            |            Description            |            Default            |
| ------------------------------- | -------------------------------   | --------------------------    |
| Scaling type  (ScalingAnimationType)|The type of animation|ScalingAnimationIn|
| Duration action down (Int)|Time to control animate for your touch|400 (milliseconds)|
| Duration action up (Int)|Time to control animate for move on|400 (milliseconds)|
| Scaling padding (float)|Alpha degree value between 0.0f and 1.0f|0.8f|

* ### ScalingAnimationType
    * This is `ScalingAnimationType.SCALING_IN`
    
  <p align="center"><img src="./screenshot/ScalingAnimationType_In.gif" width="300" height="500" ><br/><br/></p>

    * This is `ScalingAnimationType.SCALING_OUT`
    
  <p align="center"><img src="./screenshot/ScalingAnimationType_out.gif" width="300" height="500" ><br/><br/></p>

## License

```
Copyright 2020 ChanoknadM

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
