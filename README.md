# Countries Application

The app allows to fetch the whole countries' info and present them to the users. The used principle is **Single Source of Truth**.
* First of all,the flow configuration between database and local memory is built using **kotlin coroutines flow**.
* Second, all of the countries and their info are loaded into local database, employing **kotlin coroutines suspend** method.
* When the data is loaded (or reloaded) into the local database, the flow is triggered and the local memory absorbs the updated data from local database.

## Sample
<img src="GIF/record1.gif" width="200" height="400"/> <img src="GIF/record2.gif" width="200" height="400"/> <img src="GIF/record3.gif" width="200" height="400"/>

## Approach
* Implemented in **Android Studio** IDE using 100% **Kotlin**.
* Utilized **MVVM** clean architecture by using **Koin** dependency injection and by following **multi-module** approach (app, data and domain).
* Viewmodel directly communicates with the repository interface without an extra use-case layer.
* Api-calls: **Kotlin Coroutines**.
* Network: **Retrofit, OkHttp, LoggingInterceptor**
* Database: **Room**
* Extension functions: to convert from data model to entity model and from entity model to domain model.
* UI: **Jetpack Compose, Activity, LiveData, Coil**. 
