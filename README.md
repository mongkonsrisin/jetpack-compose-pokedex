# Jetpack Compose Pokedex (Clean Architecture)

A Pokedex Android application built with **Jetpack Compose** following **Clean Architecture** principles.

## Architecture Layers

- **Data Layer**: Handles network requests using Retrofit, data mapping from API models to Domain models, and repository implementations.
- **Domain Layer**: Contains business logic, domain models, repository interfaces, and Use Cases (interactors).
- **Presentation Layer**: UI components built with Jetpack Compose, state management with ViewModels, and UI state wrappers.

## Tech Stack
- Jetpack Compose
- Retrofit & OkHttp
- Kotlin Coroutines & Flow
- Clean Architecture
- Material 3 Design
