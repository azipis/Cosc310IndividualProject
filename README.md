# Cosc 310 Assignment 3

The project is an Android app designed for Coffee shops to help manage their business. The app tracks inventory and sales information to help managers with their daily tasks and provide them with reports to make business decisions.

---

## Running the app

The app can be run in 2 different ways:  

1. Android Studio

    - Download, run, and update android studio. Open the entire app project inside Android studio to look at code, files, and layouts. You can run the app on an emulator inside the studio by clicking the "run app" button at the top right or through the dropdown menu (Run > Run App).  
    
1. Installed on an Android phone

    - Download the APK file to your android phone. Select the APK file and press open and then press install.


### Please note: As this app is designed to be used internally, there is no option to create a personal account. Accounts must be created by and given permission by admins.
### *App tested on Google Pixel 3a, may not look correct on all mobile devices or tablets.*

---

## Class Organization

Classes are organized in the java folder of the app project. These classes are then organized by android activities and include everything that activity needs. For example, all classes related to logging in are found in one folder. Images, fonts, and layouts are found in the res folder. The AndroidManifest is found in the manifest folder.

~~~

`-- javabucksim
    |-- MainActivity.java
    |-- StatisticsReport.java
    |-- listItems
    |   |-- Categories.java
    |   |-- coldCoffee
    |   |   |-- ColdCof.java
    |   |   |-- ColdCof1.java
    |   |   |-- ColdCof2.java
    |   |   |-- ColdCof3.java
    |   |   |-- ColdCof4.java
    |   |   `-- ColdCof5.java
    |   |-- coldDrink
    |   |   |-- ColdD.java
    |   |   |-- ColdD1.java
    |   |   |-- ColdD2.java
    |   |   |-- ColdD3.java
    |   |   |-- ColdD4.java
    |   |   `-- ColdD5.java
    |   |-- hotCoffee
    |   |   |-- HotCof.java
    |   |   |-- HotCof1.java
    |   |   |-- HotCof2.java
    |   |   |-- HotCof3.java
    |   |   |-- HotCof4.java
    |   |   `-- HotCof5.java
    |   |-- hotDrink
    |   |   |-- HotD.java
    |   |   |-- HotD1.java
    |   |   |-- HotD2.java
    |   |   |-- HotD3.java
    |   |   |-- HotD4.java
    |   |   `-- HotD5.java
    |   `-- otherDrinks
    |       |-- Other.java
    |       |-- Other1.java
    |       |-- Other2.java
    |       |-- Other3.java
    |       |-- Other4.java
    |       `-- Other5.java
    |-- login
    |   |-- forgotPassword.java
    |   `-- loginActivity.java
    |-- orders
    |   |-- autoOrder.java
    |   |-- orderTile.java
    |   `-- order_RVAdapter.java
    |-- reportActivity.java
    `-- settings
        |-- User.java
        |-- UserRVAdapter.java
        |-- createAccount.java
        |-- deleteAccount.java
        |-- editAccount.java
        |-- editUserAccounts.java
        |-- editUsers.java
        `-- settingsActivity.java

10 directories, 48 files
~~~


---

## Features Added for Assignment 3

1. Delete user and user data
    - Allows users to wipe their data from the database if they are no longer using the app.
1. Edit other users
    - Allows admins to edit the data of other users, or sent a recovery email in case they are locked out of their accounts.
1. Descriptive toast messages (Open Source Library)
    - Toasts design to provide better feedback to users when using the app. Toasts are green when an action is successful, red when action failed or there was an error, yellow for a warning, and blue when giving information to the user.
1. Saving report as a PDF on the device
    - Allows saving of sales and operations report as a PDF on the device's Documents folder when the user clicks the Save Report button. Added PdfBox-Android library (https://github.com/TomRoush/PdfBox-Android) to allow creating and saving PDF files in android.
1. Auto Order that now orders now inventory
   - the auto order functionality now updates the database when orders are placed. Also low stock warning integration so that users won't accidentally     double order.
1. Item details data
   - The listitems package to only include the Categories, Choices, and Item classes. The classes creates better functionality and occupy less space, so more efficient. Added different titles for specific items, to make it easier to understand and use.

