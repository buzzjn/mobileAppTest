# mobileAppTest

Java program for testing iOS application using Junit, Selenium and Cucumber. Test run entry point - TestRun.class
The project is packed with maven so just run man test.
In order to start Appium server run in bash ./scripts/ios-appium.sh Script is included in the repo.


In order to work one needs a dev signed app pre-build with xcodebuild -sdk iphoneos11.3
Insert in work dir and set iosAppDir to the app name and environment.getPathFiles() to the part file.