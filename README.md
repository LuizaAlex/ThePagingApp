# ThePagingApp

This Android application showcases popular movies by fetching data from the IMDb API and presenting it in a user-friendly, scrollable list. The app leverages the power of the **Paging Library** to optimize data requests and display, making it efficient and responsive. 

## Project Overview

This app is designed with **MVP architecture** to maintain a clean separation of concerns. Key technologies and libraries include:
- **Paging Library**: Main focus of the app, fetching data dynamically based on user scrolling behavior.
- **Retrofit**: For HTTP requests to the IMDb API to retrieve popular movies.
- **Dagger Hilt**: For dependency injection, simplifying the management of app components.
- **Glide**: For loading and caching images, enhancing user experience with efficient image handling.
- **ViewModel** and **LiveData**: To handle UI-related data in a lifecycle-conscious way.

## Key Features

1. **Popular Movies Fetching**: Fetches a list of popular movies from IMDb and displays them in a RecyclerView.
2. **Paging Integration**: Loads data on demand based on user scrolling, reducing unnecessary network calls and optimizing memory usage.
3. **Dependency Injection with Dagger Hilt**: Manages dependencies across app layers, making code more modular and testable.
4. **Efficient Image Loading**: Uses Glide to efficiently handle images and reduce loading time.

## Why Use Paging?

Traditional applications often fetch all data at once, which can lead to high memory usage and unnecessary data loads. ThePagingApp fetches only the data the user needs, based on scroll position. For example, if the user scrolls to page six, only the first six pages are loaded, without prefetching unused data, saving memory and bandwidth.
