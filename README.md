# Quitter #

#### Video demonstration: https://www.youtube.com/watch?v=jNzEbubeBMA&ab_channel=JohnRichardEspedal

This project is an android app with the purpose of helping people quit smoking.  
It was initially inspired by the app "Slutta" made by the Norwegian Directorate of Health. 

I myself have previously used "Slutta" and seen room for improvement.  
This is my attempt at implementing those improvements in a new app.

The core functionalities of the app:
1. A timer that persistently keeps track of how long you've gone without smoking.
2. A Craving button that the user can click when they have the urge to smoke which sends them to the New York Times' games/puzzles section.
3. An achievements page which keeps track of all previously earned milestones, such as "24 hours" without smokes, "1 week" etc.
4. A settings menu which in its current state only allows the user to change from light mode to dark mode.

#### Early concept design:  
<img width="250" src="https://github.com/Johnricharde/Quitter/assets/117681128/0c3dfb84-ae3b-4f5a-aa45-ff4aebd9f475">

## Tech: ##
- Android Studio
- Kotlin
- Jetpack Compose

## Acknowledgements: ##
- Inspired by the mobile app "Slutta", which was created by the Norwegian Directorate of Health.

## Lessons learned: ##
- Learnt more about developing android applications
- Learnt about Jetpack Compose
- Learnt how to use SharedPreferences to create a persistent timer, settings and achievements

- ## Core Components/Files Explained ##
1. ### Views: ###
   - #### MainPage.kt ####
      The main entry point for the UI, which arranges the components in a column layout. It contains a timer display, a logo, and navigation buttons.
      1. DisplayTimer: Displays a timer inside a rounded rectangle surface.
      2. DisplayLogo: Shows the app's title and logo.
      3. DisplayNavButtons: Provides navigation buttons for different sections (Craving, Achievements, Settings).
   - #### AchievementsPage.kt ####
      A composable function that displays the achievements view in the app.
      1. LocalBadgesPreferences: Provides shared preferences for managing badge achievements.
      2. AchievementsPage: The main composable function that arranges the achievements in a grid layout.
         - Retrieves shared preferences.
         - Displays a grid of badges using LazyVerticalGrid.
         - Includes a "Go Back" button for navigation.
      3. AchievementItem: A composable function representing each badge in the grid.
         - Checks if a badge is earned using shared preferences.
         - Displays the badge title and its earned status in a styled Surface.
   - #### SettingsPage.kt ####
      A composable function that displays the settings view in the app.
      1. SettingsPage: The main composable function that arranges the settings options.
         - Includes a switch to toggle between dark and light themes.
         - Uses a ThemeViewModel to manage the theme state.
         - Contains a "Go Back" button for navigation.
      2. ThemeSwitcher: A composable function for the theme toggle switch.
         - Displays a switch to toggle dark mode.
         - Updates the theme state based on the switch position.
2. ### Tools: ###
     - #### TimeCounter.kt ####
     - #### ThemeView.kt ####
     - #### Badge.kt ####
     - #### buttons.kt ####

## MIT License ##
Copyright (c) 2024 John Richard Espedal

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
