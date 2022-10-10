# Aplicativo Chuck Norris' Jokes - ## Desafio Mobile da Pulsus

Study application made to take advantage of the best programming practices using chuck norris'
public api. Shows a chuck norris' random joke, random joke by category, or you can search for a joke
using a keyword.

[APK](https://github.com/rafaelmfer/ChuckNorris-Jokes)
|| [VIDEO](https://github.com/rafaelmfer/ChuckNorris-Jokes)
|| [YOUTUBE](https://github.com/rafaelmfer/ChuckNorris-Jokes)

<table>
    <thead>
        <tr>
            <th>BASE</th>
            <th>Architecture</th>
            <th>IU</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>AppCompat</td>
            <td>ViewBinding</td>
            <td>Material Components</td>
        </tr>
        <tr>
            <td>Android KTX</td>
            <td>Lifecycles</td>
        </tr>
        <tr>
            <td>Android Arch</td>
            <td>LiveData</td>
        </tr>
        <tr>
            <td>Room</td>
            <td>ViewModel</td>
        </tr>
    </tbody>
</table>


**Screens**
<table>  
    <th>Home Light</th>
    <th>Random Joke Light</th>
    <th>Random Joke By Category Light</th>
    <th>Search Jokes Light</th>
    <th>Favorites Light</th>
    <tr>
        <td>
            <img src="https://github.com/rafaelmfer/ChuckNorris-Jokes"/>
        </td>
        <td>
            <img src="https://github.com/rafaelmfer/ChuckNorris-Jokes"/>
        </td>
        <td>
            <img src="https://github.com/rafaelmfer/ChuckNorris-Jokes"/>
        </td>
        <td>
            <img src="https://github.com/rafaelmfer/ChuckNorris-Jokes"/>
        </td>
        <td>
            <img src="https://github.com/rafaelmfer/ChuckNorris-Jokes"/>
        </td>
    </tr>
</table>

<table>
    <th>Home Dark</th>
    <th>Random Joke Dark</th>
    <th>Random Joke By Category Dark</th>
    <th>Search Jokes Dark</th>
    <th>Favorites Dark</th>
    <tr>
        <td>
            <img src="https://github.com/rafaelmfer/ChuckNorris-Jokes"/>
        </td>
        <td>
            <img src="https://github.com/rafaelmfer/ChuckNorris-Jokes"/>
        </td>
        <td>
            <img src="https://github.com/rafaelmfer/ChuckNorris-Jokes"/>
        </td>
        <td>
            <img src="https://github.com/rafaelmfer/ChuckNorris-Jokes"/>
        </td>
        <td>
            <img src="https://github.com/rafaelmfer/ChuckNorris-Jokes"/>
        </td>
    </tr>
</table>

## Base project

- **Dependency injection:**
  With Koin, a practical dependency injection library, the code will not be coupled and it'll still
  be easy to resolve automatically the dependencies on the runtime and mock them during the tests.

- **Coroutines:**
  With coroutines it is possible to perform asynchronous tasks without changing the code flow of the
  application. Simplifies code by abstracting all the complexity of using threads

- **Room:**
  Room Database is one of the existing libraries within the “Android JetPack” suite, it helps developers
  creating an abstraction of database layers (SQLite) to store information.

- **Kotlin KTS:**
  Using Kotlin KTS we can take advantage of the application configuration using the kotlin language
  in our gradle file. This makes our configuration even easier


## Tests

- **Unit Tests**: RandomJokeViewModelTest, RandomJokeCategoryViewModelTest, SearchJokesViewModelTest, FavoritesViewModelTest
<table>
    <th>RandomJokeViewModelTest</th>
    <th>RandomJokeCategoryViewModelTest</th>
    <th>SearchJokesViewModelTest</th>
    <th>FavoritesViewModelTest</th>
    <tr>
        <td>
            <img src="https://github.com/rafaelmfer/ChuckNorris-Jokes"/>
        </td>
        <td>
            <img src="https://github.com/rafaelmfer/ChuckNorris-Jokes"/>
        </td>
        <td>
            <img src="https://github.com/rafaelmfer/ChuckNorris-Jokes"/>
        </td>
        <td>
            <img src="https://github.com/rafaelmfer/ChuckNorris-Jokes"/>
        </td>
    </tr>
</table>

- **Instrumented Tests**:
<table>
    <th>JokeDaoTest</th>
    <tr>
        <td>
            <img src="https://github.com/rafaelmfer/ChuckNorris-Jokes"/>
        </td>
    </tr>
</table>


## Quick start

1. Clone the repository with `git clone https://github.com/rafaelmfer/ChuckNorris-Jokes`
2. Run the application and be happy

## CODE

- **IDE - Android Studio Dolphin 2021.3.1**

- **Gradle 7.3.0**

- **Kotlin 1.7.10**

- **AAC Android Architecture Components** *using guide Google JetPack*

- **MVVM Architecture** *for apply SOLID*

- **ViewBinding** *bind view*

- **Retrofit** *for make the communication to API*

- **Coroutines** *for asynchronous calls and operations*

- **ViewModel** *for interact view with business rules*

- **JUnit / Espresso** *for unit and instrumented tests*

## API

Chuck Norris' API Documentation: https://api.chucknorris.io/

## DESIGN

**Material Components**

https://github.com/material-components

- RecyclerView
- MaterialButton