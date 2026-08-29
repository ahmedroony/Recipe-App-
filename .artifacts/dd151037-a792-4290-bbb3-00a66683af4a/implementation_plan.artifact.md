# Fix "Unresolved reference 'retrofit2'"

The project is missing the Retrofit dependency, which is required by `ApiService.kt`. This plan adds Retrofit and the Gson converter to the project's dependencies.

## Proposed Changes

### Build Configuration

#### [MODIFY] [libs.versions.toml](file:///S:/MyWork/Android_Projects/Recipe-App-/gradle/libs.versions.toml)
- Add Retrofit and Gson converter versions.
- Add Retrofit and Gson converter library definitions.

#### [MODIFY] [build.gradle.kts (app)](file:///S:/MyWork/Android_Projects/Recipe-App-/app/build.gradle.kts)
- Add Retrofit and Gson converter to the `dependencies` block.

## Verification Plan

### Automated Tests
- Run `./gradlew :app:compileDebugKotlin` to verify that the unresolved reference error is resolved.

### Manual Verification
- Perform a Gradle Sync in Android Studio to ensure the IDE recognizes the new dependencies.
