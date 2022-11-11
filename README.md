# MovieappMVVM_Algo
Movie App to Display movies from TMDB

Movies Display App
The project aims to list the movies that are on the air and the movies that are coming soon. you can click on a single movie to get more information about the 
particular movie



# Libraries Used are 
Glide
Fragment Lifecycle
Dagger Hilt
ViewBinding
Navigation Component
Navigation SafeArgs
Retrofit
JSON Converter with Gson
Logging Interceptor

# MVVM Architecture Use because:
Testability
With MVVM each piece of code is more granular and if it is implemented right your external and internal dependences are in separate pieces of code from the parts with the core logic that you would like to test.

Extensibility
It sometimes overlaps with maintainability, because of the clean separation boundaries and more granular pieces of code.

The ViewModel is easier to unit test than code-behind or event-driven code.

The presentation layer and the logic is loosely coupled.
