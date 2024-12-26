# Crypto.com Exam
# Overview
●  The CurrencyListFragment is expected to receive an ArrayList of CurrencyInfo
objects to create the UI.

●  The DemoActivity should provide two datasets, Currency List A and Currency List B,
  which contain CurrencyInfo objects to be queried from the local database.

●  The DemoActivity should also include five buttons for demonstrating various
  functionalities:

    ○ The first button is responsible for clearing the data in the local database.
    ○ The second button is used to insert the data into the local database.
    ○ The third button changes the CurrencyListFragment to use Currency List A - Crypto.
    ○ The fourth button changes the CurrencyListFragment to use Currency List B - Fiat.
    ○ The fifth button displays all CurrencyInfo objects that can be purchased from Currency List A and B.

●  Additionally, the CurrencyListFragment should provide a search feature that can be
  cancelled when the user clicks the back or close button.

●  Furthermore, the CurrencyListFragment should include an empty view for displaying an empty list.

●  Lastly, it is crucial that all IO operations, including database or network access, are
  not performed on the UI Thread to ensure smooth execution.

## Architecture
<img width="800" src="https://github.com/user-attachments/assets/d1a1ae08-292a-4910-ace0-cf77dc604600" />


## Demo
<img width="200" src="https://github.com/user-attachments/assets/640af547-d733-4f56-806d-7fcfc6665a97"/>



## Unit test
<img width="810" alt="test" src="https://github.com/user-attachments/assets/eeb212eb-cd64-4d7e-bef5-4d8d2c7ed2c8" />


## Use Skill
1. Jetpack Compose<br/>
2. MVVM<br/>
3. Room<br/>
5. Coroutine<br/>
6. Repository pattern<br/>
7. Unit test(JUnit/Mockito)
<br/><br/>

# Task

Implement the following functionalities:

- The CurrencyListFragment is expected to receive an ArrayList of CurrencyInfo
  objects to create the UI.
  ```diff
  + Created a native Android app, and use jetpack compose replace CurrencyListFragment
  ```

- The DemoActivity should provide two datasets, Currency List A and Currency List B,
  which contain CurrencyInfo objects to be queried from the local database.
  ```diff
  + Use room db to add two local datasets 
  ```

- The DemoActivity should also include five buttons for demonstrating
  ```diff
  + Use BottomNavigation implementation finish.
  ```

- Additionally, the CurrencyListFragment should provide a search feature that can be
  cancelled when the user clicks the back or close button.
  ```diff
  + Use compose TopAppBar and TextField
  ```

- Furthermore, the CurrencyListFragment should include an empty view for displaying an empty list.
  ```diff
  + Implementation empty view when list is empty show.
  ```
