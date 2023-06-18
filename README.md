# Countries Application
## List of countries info are retrieved through an open source RESTful Web API and presented.

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
