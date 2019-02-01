import {
  Linking,
  NativeModules,
  Platform,
} from 'react-native'

const {RNCustomTabsShim} = NativeModules

export default (RNCustomTabs) => {
  if (Platform.OS === "ios") {
    return RNCustomTabs
  }

  return {
    ANIMATIONS_SLIDE: RNCustomTabs.ANIMATIONS_SLIDE,
    ANIMATIONS_FADE: RNCustomTabs.ANIMATIONS_FADE,
    CustomTabs: {
      openURL(url, options) {
        return RNCustomTabsShim.hasPackagesForUrl(url)
        .then(hasPackage => {
          return hasPackage ?
          Linking.openURL(url) :
          RNCustomTabs.openURL(url, options)
        })
      },
    },
  }
}
