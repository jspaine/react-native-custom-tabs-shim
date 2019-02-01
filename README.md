# react-native-custom-tabs-shim

Wrapper for [react-native-custom-tabs](https://github.com/droibit/react-native-custom-tabs) that allows applinks to be opened in the native app.

It checks if a given url is supported by any extra apps compared to a baseline (http://example.com) and, if so, opens it directly instead.

## Getting started

`$ npm install react-native-custom-tabs-shim --save`

### Mostly automatic installation

`$ react-native link react-native-custom-tabs-shim`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-custom-tabs-shim` and add `RNCustomTabsShim.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNCustomTabsShim.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.reactlibrary.RNCustomTabsShimPackage;` to the imports at the top of the file
  - Add `new RNCustomTabsShimPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-custom-tabs-shim'
  	project(':react-native-custom-tabs-shim').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-custom-tabs-shim/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-custom-tabs-shim')
  	```

## Usage
```javascript
import * as RNCustomTabs from "react-native-custom-tabs"
import RNCustomTabsShim from "react-native-custom-tabs-shim"

const {CustomTabs, ANIMATIONS_FADE} = RNCustomTabsShim(RNCustomTabs)

const url = "https://www.netflix.com" // opens in netflix app
// const url = "https://example.com" // opens in custom tab

const options = {
	toolbarColor: navbarColour,
	enableUrlBarHiding: true,
	showPageTitle: false,
	forceCloseOnRedirection: true,
	animations: RNCustomTabs.ANIMATIONS_FADE,
}

RNCustomTabs.CustomTabs.openURL(url, options)
```
