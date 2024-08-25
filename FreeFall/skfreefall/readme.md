# Module skfreefall

skfreefall is a library to detect free fall event of android devices.

It sends notification to the system which will be displayed in  notification bar of device.
It saves all the free fall events and user can get the list .
Fall event can be detected even if the activity is in background.

Built in accelerometer sensor needed in the device.
It reads accelerometer sensors data and based on the reading detect fall event.
Fall threshold value can be different from device to device.
Therefore, client can anjust the threshold.

There is a one second time gap between two fall events.
That means library will ignore the event if the last event happened within one second.

## Developer Guide

[com.tuhin.skfreefall.FallDetector]
