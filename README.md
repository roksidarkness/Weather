# Weather
Android application to show weather forecast for user locations

![Screenshot_20220809_183201_com roksidark weatherforecast (1)](https://user-images.githubusercontent.com/90980503/183704523-269fdc68-4d13-46cb-9edc-656ec0fac6ac.png)
![Screenshot_20220809_183239_com roksidark weatherforecast (1)](https://user-images.githubusercontent.com/90980503/183705026-5d8c80bd-512a-430c-a1f3-ae9d44cb4054.png)
![Screenshot_20220809_183249_com roksidark weatherforecast (1)](https://user-images.githubusercontent.com/90980503/183705098-c9dc0358-5606-41b0-bf5a-72e0afc37210.png)
![Screenshot_20220809_183259_com roksidark weatherforecast (1)](https://user-images.githubusercontent.com/90980503/183705155-f58af837-a5b5-46d4-949d-dd0b3fb8c123.png)

# Tech stack:

- Hilt
- Room
- Jetpack
- Coroutines
- Retrofit
- Navigation 
- LiveData 
- Flow
- Lifecycle 
- ViewModel
- Compose
- Tests

# Architecture:

- Model-View-ViewModel

# Project Setup
Clone the project and open it using Android Studio. Then open your local.properties file under Gradle Scripts. You need to specify the PLACE_API_KEY and API_KEY in your local.properties file. 

Application uses the location function provided by Google Place API (PLACE_API_KEY) to obtain the current location. Application displays the weather forecast according to the current location by https://www.weatherbit.io/(API_KEY).

