# Smatech POS Mobile App  

**Smatech POS** is a Kotlin-based Point of Sale (POS) application that leverages a simple implementation of the **MVVM architecture** for clean separation of concerns and efficient state management. This app is designed to provide a seamless experience for managing products, orders, and receipts. 

---

## Features  

### 1. **Product Management**  
- Fetch a dynamic list of products from a REST API.  
- Search for specific products using intuitive search functionality.  
- View detailed information for each product, including price and descriptions.  

### 2. **Cart Functionality**  
- Add selected products to a shopping cart.  
- Easily view and manage items in the cart before placing an order.  

### 3. **Order Placement**  
- Place orders directly to the server for real-time processing.  

### 4. **Receipt Management**  
- View a comprehensive history of past orders with itemized details.  
- Generate professional PDF receipts for easy sharing or printing.  

---

## Dependencies

The following dependencies are used in this project:

### androidx.core:core-ktx
Provides Kotlin extensions for Android APIs, simplifying tasks such as property delegation and lifecycle-aware components.

### androidx.lifecycle:lifecycle-runtime-ktx
Adds Kotlin extensions for lifecycle-aware components, making it easier to handle lifecycle events in a reactive manner.

### androidx.activity:activity-compose
Supports Jetpack Compose integration with Activity for seamless navigation and UI handling.

### androidx.compose:compose-bom
Ensures consistent versions of Compose libraries by using the Bill of Materials (BOM).

### androidx.ui
Enables declarative UI development with Jetpack Compose.

### androidx.ui:ui-graphics
Provides graphics utilities for Jetpack Compose, including rendering and drawing functionalities.

### androidx.ui:ui-tooling-preview
Allows previewing Compose UIs directly in Android Studio without running the app.

### androidx.compose.material3:material3
Supports Material Design 3 components and theming for Jetpack Compose.

### androidx.compose.material:material
Implements Material Design 2 components for Jetpack Compose.

### androidx.compose.material:material-icons-extended
Provides an extended set of Material Design icons for Compose-based UI.

### androidx.navigation:navigation-compose
Adds support for navigation in Compose applications with the Jetpack Navigation component.

### junit:junit
Includes the JUnit library for unit testing.

### androidx.test.ext:junit
Provides Android-specific JUnit extensions for UI testing.

### androidx.test.espresso:espresso-core
Facilitates Android UI testing with Espresso, allowing simulation and validation of user interactions.

### androidx.compose:ui-test-junit4
Enables JUnit4-based UI testing for Compose applications.

### androidx.ui:ui-tooling
Supports debugging and inspection of Compose UIs in debug builds.

### androidx.ui:ui-test-manifest
Helps debug issues related to the AndroidManifest file during UI testing.

### com.squareup.retrofit2:retrofit
A type-safe HTTP client for making REST API requests.

### com.squareup.retrofit2:converter-gson
Adds Gson support for serialization and deserialization of JSON data in Retrofit.

### androidx.runtime:runtime-livedata
Enables the use of LiveData in Jetpack Compose for reactive data handling.

### androidx.room
Provides the essential runtime functionality for Room, a persistence library that simplifies SQLite database integration in Android apps. It helps developers work with structured data seamlessly while leveraging compile-time checks for SQL queries.

### androidx.room
Enables Kotlin-specific extensions for Room, such as coroutines support for database operations, making asynchronous programming more intuitive and concise.

---

## Installation  
To run the Kotlin App on your local machine, follow these steps:  

1. **Ensure you have the required tools installed**  
   - Install the latest version of Android Studio: [Android Studio Download](https://developer.android.com/studio)  
   - Ensure you have the Java Development Kit (JDK) 17 or later installed: [JDK Installation](https://adoptium.net/)  

2. **Clone the repository**  
   ```bash  
   git clone https://github.com/ishe19/Smatech_POS_Mobile.git  
   ```  

3. **Navigate to the project directory**  
   ```bash  
   cd Smatech_POS_Mobile  
   ```  

4. **Open the project in Android Studio**  
   - Launch Android Studio and select **File > Open**, then navigate to the project directory and open it.  

5. **Sync dependencies**  
   - Once the project is loaded, Android Studio will prompt you to sync the Gradle files.  
   - Click **Sync Now** to install all required dependencies.  

6. **Run the application**  
   - Connect an Android device or start an emulator.  
   - Select the **Run** button in Android Studio to build and run the app.

  ---
  
## Important Notes:

1. **Android SDK Requirement**:  
   This application has a minimum Android SDK requirement of Android 12. Devices running versions lower than this may not be able to run the app. The limitation arises from a known compatibility issue with the Splash Screen API. While I am actively working on a fix, this note likely means the issue is still unresolved. (Yes, I know — still a work in progress! 😅)

2. **API Hosting on Free Tier**:  
   The REST API for this project is hosted on Render's free tier, which means the server instance may enter a "spin-down" state when idle. This can temporarily affect the app's ability to fetch products or process orders. To resolve this, simply visit the API documentation at [Swagger](https://smatech-pos-api.onrender.com/smatech_api/swagger-ui/index.html). Accessing the link should wake up the instance, allowing the app to function normally again.

3. **REST API Documentation**:  
   As part of the assignment requirements, I was tasked with showcasing the API requests and responses. The documentation linked above provides detailed information on all REST API endpoints, including their requests and responses.



---

## Usage  

Upon successfully launching the POS application, users are greeted with the **Home Screen**, which displays the available products fetched dynamically from the server. Here's a breakdown of the app's main features:  

### 1. **Product Management**  
- **Search Products:** Use the search bar on the Home Screen to locate products by name or SKU.  
- **View Product Details:** Tap on a product to open its detailed view, showcasing information such as price, description, and stock availability.  

### 2. **Cart Functionality**  
- **Add to Cart:** Select desired products and add them to the shopping cart with a single tap.  
- **View Cart:** Navigate to the **Cart Tab** to view a comprehensive list of all selected products, including their quantities and total price.  
- **Modify Cart Items:** Update quantities or remove items from the cart before proceeding to checkout.  

### 3. **Order Processing**  
- **Place an Order:** Once satisfied with the selected items, proceed to finalize the order. The app confirms successful order placement.  

### 4. **Receipts Management**  
- **View Receipts:** Navigate to the **Receipts Tab** to access a list of all previous orders. Each entry displays the order date, total cost, and order ID for easy reference.  
- **Receipt Details:** Tap on a specific receipt to view its full details, including the purchased items, individual prices, and quantities.  
- **Generate PDF Receipt:** Export a digital copy of the receipt in PDF format for easy sharing or record-keeping.  

The POS app is designed to streamline product selection, simplify the checkout process, and ensure efficient order tracking, all while offering users a seamless and intuitive experience.  

---

## Screenshots

![Home Tab](https://github.com/ishe19/Smatech_POS_Mobile/blob/main/screenshots/Smatech1.png)

![Product Details](https://github.com/ishe19/Smatech_POS_Mobile/blob/main/screenshots/Smatech2.png)

![Shopping Cart](https://github.com/ishe19/Smatech_POS_Mobile/blob/main/screenshots/Smatech3.png)

![Receipts Tab](https://github.com/ishe19/Smatech_POS_Mobile/blob/main/screenshots/Smatech4.png)

![Receipt Details](https://github.com/ishe19/Smatech_POS_Mobile/blob/main/screenshots/Smatech5.png)
