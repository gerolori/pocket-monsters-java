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

## How to Play

1. **Explore**: Open the app and allow location permissions to see the map
2. **Discover**: Move around in the real world to find monsters and items on the map
3. **Interact**: Tap on map markers to interact with nearby objects
4. **Combat**: Battle monsters to earn experience points and collect candys
5. **Equip**: Visit your profile to equip weapons, armor, and amulets
6. **Compete**: Check the leaderboard to see how you rank against other players

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
