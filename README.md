# Countries Application

The app allows to fetch the whole countries' info and present them to the users. The used principle is **Single Source of Truth**.
* First of all, flow of data from local database into local memory (list) is configured using **kotlin coroutines flow**.
* Second, all of the countries and their info are loaded into local database, employing **kotlin coroutines suspend** method.
* When the data is loaded (or reloaded) into the local database, the flow is triggered and the list absorbs the updated data from the local database.

## Sample
<img src="GIF/record1.gif" width="200" height="400"/> <img src="GIF/record2.gif" width="200" height="400"/> <img src="GIF/record3.gif" width="200" height="400"/>

## Approach
* Implemented in **Android Studio** IDE using 100% **Kotlin** and **Coroutines** (for the business logic).
* Dependency Injection **Koin** (note: will be changed into something else.
* Architecture: **MVVM** (viewmodel communicates with the repository interface directly, without any use case)
* Approach: **Multi-module - app, data and domain** (currently all are in app module but will be splited to separate modules)
* Network issues: **Retrofit, OkHttp, LoggingInterceptor**
* Database issues: **Room**
* Extension functions: to convert from data model to entity model and from entity model to domain model.
* UI: Activity, LiveData, RecyclerView, Adapter, onItemClick handling. 
