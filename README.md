# Weather-Web-App
Web application built on Vue.js and Spring boot (Java + Gradle) on IntelliJ IDEA.

Application uses public API of [this website](https://www.ilmateenistus.ee/ilm/prognoosid/locality-forecast/?lang=en). 
Direct link for API itself: https://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng

___

APIs XML Response:
![image](https://user-images.githubusercontent.com/73297277/192157768-9084b3e6-f742-4994-8647-935ace4023b4.png)

___

Data structure draft:
![map](https://user-images.githubusercontent.com/73297277/192157501-f94ab099-0a54-4f06-85e8-11c4fc3137e5.png)

Note: this is only overall illustration of data structure, not exact one. For example, 'wind', 'places' and other objects are only available for today's date. That means these objects not initialized for next few days.

___

# How to execute this project

1) Run Application by IntelliJ IDEA (Shift + F10 on windows 10)
2) Use these 2 commands:
```
 cd .\src\vue-frontend\
 npm run serve
```
!!! In case if project doesn't run properly after ```npm run serve```, try to use ````npm install``` and then ```nom run serve```. !!!

Video below demonstrates functionality of website and gives clear intruction for installing project.

https://user-images.githubusercontent.com/73297277/192157651-3be3bf38-7f3c-4a59-bb26-504bde3043ab.mp4


