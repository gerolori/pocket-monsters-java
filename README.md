# Pocket Monsters - Java Mobile Dev Project

An Android location-based game where you can **combat monsters**, **collect candys**, and **equip artifacts**! Explore the real world to discover virtual creatures and items, battle them, and climb the leaderboards.

## About

Pocket Monsters is a modern Android application built with Java that combines real-world exploration with virtual monster collection. Using GPS and Google Maps integration, players can discover monsters and items at real-world locations, engage in combat, collect resources, and customize their character with powerful equipment.

### Features

- **Location-Based Gameplay**: Use Google Maps to find monsters and items in your area
- **Combat System**: Battle monsters to gain experience and collect rewards
- **Candy Collection**: Gather candys from defeated monsters and discovered items
- **Equipment System**: Equip weapons, armor, and amulets to enhance your character
- **Leaderboards**: Compete with other players and climb the rankings
- **Profile Management**: Track your life points, experience, and customize your avatar
- **Real-Time Updates**: See nearby players and their positions on the map

## Technology Stack

- **Language**: Java 17
- **Platform**: Android (Min SDK 34, Target SDK 36)
- **Architecture**: MVVM (Model-View-ViewModel)
- **Maps**: Google Maps Android API
- **Location**: Google Play Services Location
- **Networking**: Retrofit 3.0.0 + OkHttp 5.3.2
- **Local Database**: Room 2.8.4
- **Analytics**: Firebase Crashlytics & Analytics
- **UI**: Material Design Components, View Binding

## Prerequisites

Before you begin, ensure you have:

- **Android Studio** (latest version recommended)
- **JDK 17** or higher
- **Android SDK** with API level 34+
- A **Google Maps API key** (instructions below)
- Access to the **backend API** (API URL needed)

## Setup

### 1. Get a Google Maps API Key

1. Visit the [Google Maps Platform](https://developers.google.com/maps/documentation/android-sdk/get-api-key)
2. Create a new project or select an existing one
3. Enable the **Maps SDK for Android**
4. Create credentials (API key)
5. Restrict the API key to Android apps (optional but recommended)

### 2. Configure API Keys

1. Copy the default secrets file:
   ```bash
   cp secrets.defaults.properties secrets.properties
   ```
   
2. Edit `secrets.properties` and add your keys:
   ```properties
   MAPS_API_KEY=your_actual_google_maps_api_key_here
   api.url=your_backend_api_url_here
   ```
   
   **Note**: You can also add these to `local.properties` if preferred.

### 3. Build and Run

1. Open the project in Android Studio
2. Sync Gradle files
3. Connect an Android device or start an emulator
4. Click **Run** or use:
   ```bash
   ./gradlew installDebug
   ```

## Architecture

Pocket Monsters follows the **MVVM (Model-View-ViewModel)** architectural pattern, providing clear separation of concerns and maintainability. The application is structured into three primary layers:

### Presentation Layer

The presentation layer handles all UI logic and user interactions through **Fragments** and **ViewModels**:

- **Fragments**: Act as Views in the MVVM pattern
  - `MapFragment`: Displays the Google Map with nearby monsters, items, and other players
  - `ProfileFragment`: Shows user profile, stats (life points, experience), and equipment management
  - `LeaderboardFragment`: Displays player rankings based on experience points
  - `ObjectInteractionFragment`: Dialog for interacting with monsters and items (combat/collection)
  - `ItemListDialogFragment`: Shows list of nearby virtual items

- **ViewModels**: Manage UI-related data and business logic
  - Extend `androidx.lifecycle.ViewModel` for lifecycle-aware data handling
  - Examples: `MapViewModel`, `ProfileFragmentViewModel`, `LeaderboardViewModel`
  - Handle data transformations between the data layer and UI

- **MainActivity**: Single activity that hosts all fragments and manages bottom navigation

### Data Layer

The data layer is divided into **remote** and **local** components:

#### Remote Data Source
- **ApiInterface**: Retrofit interface defining all REST API endpoints
  - User management (`POST /users`, `GET /users`, `PATCH /users/{uid}`)
  - Virtual items (`GET /objects`, `GET /objects/{id}`, `POST /objects/{id}/activate`)
  - Leaderboard rankings (`GET /ranking`)
  
- **InterfaceConverter**: Centralized API client manager
  - Creates Retrofit instances with base URL from BuildConfig
  - Manages session ID (sid) and user ID (uid) in SharedPreferences
  - Provides callback-based methods for all API operations
  - Implements caching strategies for user credentials

- **Callbacks**: Asynchronous response handlers for API calls
  - `UserIDCallback`, `VirtualItemDetailCallback`, `RankingCallback`, etc.
  - Enable non-blocking network operations

#### Local Data Source
- **Room Database**: SQLite-based local persistence
  - `UserDB`: Stores user profile data locally
    - Entity: `User` (profile picture, name, life points, experience, equipped items)
    - DAO: `UserDAO` for CRUD operations
  
  - `VirtualItemDB`: Caches virtual items for offline access
    - Entity: `VirtualItem` (id, name, image, level, location, type)
    - DAO: `VirtualItemDAO` for managing cached items

### Model Layer

Data Transfer Objects (DTOs) and entities for data representation:

- **API Response Models**: Plain Java objects (POJOs) for API responses
  - `UserID`, `UserDetail`, `UserNearby`, `UserRanking`
  - `VirtualItemNearby`, `VirtualItemDetail`, `VirtualItemActivated`
  - Parsed from JSON using Gson converter

- **Database Entities**: Room entities with annotations
  - `@Entity`, `@PrimaryKey`, `@NonNull` for schema definition
  - Stored in local SQLite database for offline functionality

### Data Flow

1. **App Launch**:
   - `MainActivity.onCreate()` requests user ID from API via `InterfaceConverter.requestUserID()`
   - Session credentials (sid, uid) are stored in SharedPreferences
   - Default `MapFragment` is loaded

2. **Location-Based Discovery**:
   - User's GPS coordinates are captured using Google Play Services Location API
   - `MapFragment` periodically calls `ApiInterface.getVirtualItemsNearby()` with current location
   - Nearby monsters, items, and players are displayed as markers on Google Map

3. **User Interaction**:
   - User taps on map marker or list item
   - Fragment launches `ObjectInteractionFragment` dialog
   - Dialog fetches detailed information via `ApiInterface.getVirtualItemDetail()`
   - User can activate (combat/collect) the item via `ApiInterface.activatedVirtualItem()`

4. **Data Persistence**:
   - API responses are cached in Room database (`UserDB`, `VirtualItemDB`)
   - UI first checks local database, then syncs with remote API
   - Reduces network calls and enables offline browsing

5. **Profile & Equipment**:
   - User data fetched from `ApiInterface.getUserDetail()`
   - Profile updates (name, picture) sent via `PATCH /users/{uid}`
   - Equipment changes immediately reflected in local database and synced to API

6. **Leaderboard**:
   - Rankings fetched from `ApiInterface.getRanking()`
   - RecyclerView with custom adapter displays sorted player list
   - Refresh pulls latest data from server

### Key Integration Points

- **Google Maps SDK**: Integrated in `MapFragment` for location visualization
- **Retrofit + OkHttp**: HTTP client for RESTful API communication
- **Room Database**: Type-safe database access with compile-time verification
- **Material Components**: Consistent UI with bottom navigation and dialogs
- **Firebase**: Crashlytics for error reporting, Analytics for user behavior tracking
- **View Binding**: Type-safe view access eliminating findViewById calls

## Project Structure

```
app/src/main/
├── java/com/example/pocketmonsters/
│   ├── MainActivity.java              # Main entry point
│   ├── presentation/                  # UI Layer
│   │   ├── map/                       # Map screen (main gameplay)
│   │   ├── profile/                   # User profile & equipment
│   │   ├── leaderboard/               # Rankings & competition
│   │   └── iteminteraction/           # Monster/item interactions
│   ├── data/
│   │   ├── remote/                    # API integration (Retrofit)
│   │   └── local/                     # Local database (Room)
│   └── model/                         # Data models (POJOs/DTOs)
└── res/                               # Resources (layouts, drawables, etc.)
```

## Development

### Building for Release

```bash
./gradlew assembleRelease
```

### Running Tests

```bash
./gradlew test
./gradlew connectedAndroidTest
```
