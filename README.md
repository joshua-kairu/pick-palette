# Pick Palette

Android colors served right up. :smile:

## Introduction :point_up:

This repo holds the implementation of a [Udacity](https://www.udacity.com/) sample application that shows how to extract colors from a picture in Android. The sample was made for Udacity's [Material Design for Android Developers](https://www.udacity.com/course/material-design-for-android-developers--ud862) online class. The original code can be found in [this](https://github.com/udacity/ud862-samples) repo.

## Version History :package:

**latest** 	[Pick Palette v1.0.0](https://github.com/joshua-kairu/xml-test/releases/download/v1.0/XML-Test-v1.0.0.apk) 	Thursday, May 26, 2016

This repo tries its best to follow the [Semantic Versioning](http://semver.org/) guidelines.

## How To Use :wrench:

The steps to use this app are:

1. Fire up the app.

2. You'll meet with the home screen, which looks like this:

![The home screen](/run/media/joshua/DATA/Documents/Coding/Android/AndroidStudioProjects/udacity-material-design-class-code-samples/PickPalette/screenshots/main-activity-start-2016-05-26-230327.png) 

3. Clicking the green "Plus" button at bottom right allows you to choose to insert a picture from either phone storage or the camera.

![Options to get an image from](/run/media/joshua/DATA/Documents/Coding/Android/AndroidStudioProjects/udacity-material-design-class-code-samples/PickPalette/screenshots/main-activity-choosing-an-image-2016-05-26-230356.png) 

4. After acquiring an image, it should appear on the home screen as follows.

![Displaying a chosen image](/run/media/joshua/DATA/Documents/Coding/Android/AndroidStudioProjects/udacity-material-design-class-code-samples/PickPalette/screenshots/main-activity-display-color-info-2016-05-26-230504.png) 

Not only is the picture displayed but also are the major colors in the picture.

5. Touching one of the colors provides a snackbar explaining the color's hex value and the number of pixels it occupies in the image. In other words,

![Tapping on a color for more information](/run/media/joshua/DATA/Documents/Coding/Android/AndroidStudioProjects/udacity-material-design-class-code-samples/PickPalette/screenshots/main-activity-display-color-info-selected-dark-muted-2016-05-26-230504.png) 

In the above screenie, the Dark Muted color was selected and its details are shown.

## Abilities :muscle:

This app can:
* extract major colors from a picture.


## Limitations :worried:

This app was just a tutorial. It cannot:
* Highlight the locations of each of the pixels of the major 

## Possible Future Work :fast_forward:

With enough time, some improvements could be:
- [ ] Making the words in the app more user-friendly. :smile:

## Other things :books:

Huge shoutout to:
* The Udacity fellows for yet another quality demo app.
* Jake Wharton for the [ButterKnife](http://jakewharton.github.io/butterknife/) library, used to bind views to fields.
* Square for the [Picasso](http://square.github.io/picasso/) library. Truly hassle-free image loading. :+1:

## License :lock_with_ink_pen:

This repository is licensed under the [GNU General Public License Version 3](http://www.gnu.org/licenses/gpl-3.0.en.html).
