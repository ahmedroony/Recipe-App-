# Walkthrough - Fix "Unresolved reference 'retrofit2'"

I have resolved the "Unresolved reference 'retrofit2'" error in `ApiService.kt` by adding the missing Retrofit dependencies to the project.

## Changes Made

### Build Configuration

#### [libs.versions.toml](file:///S:/MyWork/Android_Projects/Recipe-App-/gradle/libs.versions.toml)
- Added `retrofit = "3.0.0"` to the `[versions]` block.
- Added `retrofit` and `retrofit-converter-gson` library definitions to the `[libraries]` block.

#### [build.gradle.kts (app)](file:///S:/MyWork/Android_Projects/Recipe-App-/app/build.gradle.kts)
- Added `implementation(libs.retrofit)` and `implementation(libs.retrofit.converter.gson)` to the `dependencies` block.

## Verification Results

### Automated Tests
- Ran `./gradlew :app:compileDebugKotlin`: **Success**
- Gradle Sync: **Success**

The build now completes without the "Unresolved reference 'retrofit2'" error.
