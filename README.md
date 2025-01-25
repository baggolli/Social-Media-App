# Social-Media-App [League]

**Overview**
This project demonstrates an Android application that displays a list of user posts. It follows Clean Architecture, uses Jetpack Compose for the UI, and leverages Hilt for dependency injection. The project fetches data from a REST API, processes it, and displays it in a responsive UI.

# Clean Architecture

**Why Clean Architecture Was Chosen:**
Clean Architecture was chosen for this project to ensure maintainability, scalability, and testability. Clean Architecture separates the app into distinct layers, each with its own responsibility:

1. Presentation Layer: Handles UI and user interaction. It contains the ViewModel that communicates with the UseCase to process UI-related logic.

2. Domain Layer: Contains the business logic. It includes the UseCase, which defines the operations the app can perform and calls the Repository to fetch or process data.

3. Data Layer: Handles data operations, such as interacting with APIs or databases. It contains the Repository and ApiService for data fetching.

**Using Clean Architecture ensures:**

1. Testability: Layers are isolated, making it easy to test each component independently.

2. Maintainability: Code is modular, and changes in one layer don't affect others.

3. Scalability: The application can grow without making the codebase unmanageable.

# Unit Test Cases

Unit tests have been implemented to verify the functionality of the repository, use case, and view model layers.

**1. Repository Tests:**
The repository tests ensure that the repository is interacting with the API service correctly. For example:
Test cases check that the UserWithPostRepository fetches the expected data from the API.

**2. UseCase Tests:**
Use case tests verify that business logic is implemented correctly. They test that the use case transforms and combines data properly before returning it to the view model.

**3. ViewModel Tests:**
ViewModel tests ensure that the UI state updates correctly based on the data received from the use case. For example:
Test cases verify that the view model emits the correct state (Loading, Success, or Error) when data is being fetched.


# The Logic Implemented

**1. Data Fetching:**
Data is fetched from two API endpoints: one for user data and one for post data. The Repository interacts with the ApiService to retrieve this data.

Users: Fetches user details (e.g., name, avatar, email, etc.).
Posts: Fetches posts associated with each user.

**2. Combining Data:**
The UseCase processes the fetched data and combines the user and post details. It matches each user with their corresponding post and filters out posts with invalid data (such as an "Unknown" username or missing avatar).

**3. Filtering:**
The UseCase filters out posts that do not have a valid avatar or have a username labeled as "Unknown". Only posts with a valid avatar and a non-"Unknown" username are included in the final list.


# ViewModel in the Presentation Layer
The ViewModel acts as the intermediary between the UI and the business logic. It collects the result of the use case, processes it, and updates the UI state.

The ViewModel has a StateFlow that holds the current UI state:

1. Loading: When data is being fetched.
2. Success: When data is successfully fetched.
3. Error: When there is an error fetching data.

ViewModel Flow:
1. The ViewModel calls the UseCase.
2. The UseCase fetches data from the Repository.
3. The ViewModel emits the appropriate state (Loading, Success, or Error) based on the result.

# Compose for Rendering the UI

Jetpack Compose is used for building the UI in a declarative manner. This simplifies UI development and allows for better management of state changes.

Key Benefits of Jetpack Compose:

1. Declarative Syntax: The UI is defined based on the data it represents, making it easier to read and maintain.
2. State Management: Compose integrates seamlessly with StateFlow, ensuring that UI components react to state changes.
3. Reactivity: The UI automatically updates when the state changes (e.g., when data is loaded or an error occurs).

UI Components:

1. Loader Screen: Displays a rotating loader while data is being fetched.
2. Error Screen: Displays an error message if something goes wrong.
3. Posts List: Displays a list of user posts, each with an avatar, title, and description.

# Conclusion:
This project demonstrates the use of Clean Architecture, Jetpack Compose, and Hilt to create a maintainable, testable, and scalable Android application. By separating concerns into different layers, this approach ensures that the app is easy to extend and maintain. Jetpack Compose simplifies the UI creation and makes the app reactive to state changes, while Hilt handles dependency injection for better modularity and testability.

