# 🍲 Recipe App

A modern Android application built with Kotlin and MVVM architecture that allows users to discover, search, and save their favorite recipes from around the world.

## 📱 Screenshots
<img width="568" height="952" alt="Screenshot 2026-09-01 222039" src="https://github.com/user-attachments/assets/8f9af031-8acb-45b3-abc8-45db0eddae3a" />
<img width="568" height="939" alt="Screenshot 2026-09-01 222307" src="https://github.com/user-attachments/assets/05e6e2d8-3e62-4fa4-ba4b-51cb3d4c91b1" />
<img width="564" height="950" alt="Screenshot 2026-09-01 222119" src="https://github.com/user-attachments/assets/89c3e720-9349-46a5-906e-e6abe5d147e4" />
---

## 🚀 Features

* **Recipe Discovery:** Browse recipes categorized by types (Beef, Chicken, Seafood, etc.).
* **Smart Search:** Real-time search for recipes by name or main ingredient.
* **Favorites & Local Caching:** Save recipes locally using Room Database associated with user session.
* **Video Tutorials:** Watch cooking instructions via floating interactive video player.
* **Modern UI:** Responsive screens built with ConstraintLayout, ViewBinding, and Material Design 3.

---
## 🛠 Tech Stack & Libraries

* **Language:** Kotlin
* **Architecture:** MVVM (Model-View-ViewModel)
* **API & Networking:** Retrofit 2 & OkHttp 3
* **Database:** Room Persistence Library
* **Asynchronous Calls:** Kotlin Coroutines & Flow
* **Image Loading:** Glide
* **Navigation:** Jetpack Navigation Component
* **UI Components:** ViewBinding, RecyclerView, Lottie Animations


## 🏗 Project & Package Structure

```text
com.example.recipeapp/
 ├── adapter/             # RecyclerView Adapters (RecipeAdapter, FavouriteAdapter)
 ├── database/
 │    ├── local/          # Local Data Access Objects (DAOs)
 │    ├── model/          # Data Models & Entities
 │    ├── remote/         # Retrofit API Interfaces & Services
 │    ├── repository/     # Data Repository layer
 │    ├── RecipeDatabase.kt
 │    └── UserDatabase.kt
 └── Fragments/           # UI Layer Fragments (Home, Details, Favorites, Auth, About)


📖 How to Run & Setup
Clone the repository:

Bash
git clone [https://github.com/your-username/Recipe-App.git](https://github.com/your-username/Recipe-App.git)
Open the project in Android Studio (Ladybug or newer).

Allow Gradle to sync and download dependencies.

Run the app module on an Android emulator or connected device.


---
