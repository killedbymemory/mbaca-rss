This is my personal attempt to study Android. There will be nothing valuable here. I just had this thought that I will start by creating an RSS reader. 

Why another RSS reader? 
An RSS reader at its minimum utilize a lot of things, such as: content fetching, XML parsing, make use of Android's list, etc.

I already started this project for sometimes now, locally. I've tried to fetch feed from my own blog and realise that AndroidHttpClient will only work on its own thread (instead of main activity thread). Well, I don't have any multi-thread programming experience so I resort to use DefaultHttpClient instead. Works like charm but it'll freeze the activity.  

Well, I also have a limited amount of experience with Java so to speak. So, I can safely assume this will be a long way to go. ;)
