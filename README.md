# disasterassistant-client
An Android Kotlin frontend client created for DisasterAssistant, an Android application.

You can find the backend for this project here: [DisasterAssistant Java Backend (Server)](https://github.com/mansoorhu10/disasterassistant-server).

## How I worked on this project
My goal was to create a simple and user-friendly mobile application with user authentication.
* I worked with scheduled tasks on a Trello board to keep myself on track.
* I built the frontend for the app using Android Studio and programmed it using Kotlin.

## What I have learned
I learned lots of valuable skills in client-side Android application development with Kotlin.
* I learned how to configure an [AndroidManifest.xml](app/src/main/AndroidManifest.xml) to set up different views and activities in the app.
* I created [data models](app/src/main/java/com/mansoorsyed/disasterassistantclient/model) to organize and format data being stored in the MySQL database.
* I understood how to use Retrofit, an HTTP Client for Android, to create API endpoints for storing and retrieving data.
* I learned how to create an aesthetically-appealing user interface with XML by using a floating action button, custom components, custom color theme.
* I implemented an authentication page that allows the user to swipe between [login](app/src/main/res/layout/fragment_login.xml) and [register](app/src/main/res/layout/fragment_register.xml) fragments.
* I implemented an authentication interceptor in [AuthInterceptor.kt](app/src/main/java/com/mansoorsyed/disasterassistantclient/retrofit/AuthInterceptor.kt) that ensures that every REST API request is authenticated with a valid JSON Web Token.
* I created a function that automatically checks if the user has previously logged in and if their token is still valid to automatically login.

## Future Improvements
Once I have enough time to continue working on this project, these are the primary changes and improvements I will make to the app:
* Change the data model for floods being stored in the database so that different types of disasters can be tracked.
* Improve the UI to fit modern mobile UI development standards and use Espresso to create reliable UI tests.
* Utilize the Google Maps API to show the location of each disaster on a map.

## Dependencies
* retrofit2
* converter-gson
* [dotenv-kotlin](https://github.com/cdimascio/dotenv-kotlin)
* jsonwebtoken
* okhttp3