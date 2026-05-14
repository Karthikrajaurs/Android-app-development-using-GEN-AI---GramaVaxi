╔══════════════════════════════════════════════════════════════════════════════════╗
║                                                                                  ║
║                         GRAMA-VAXI | ಗ್ರಾಮ-ವ್ಯಾಕ್ಸಿ                            ║
║                      Livestock Health Alert Android App                          ║
║                                                                                  ║
╚══════════════════════════════════════════════════════════════════════════════════╝

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

                          GRAMA-VAXI — ಗ್ರಾಮ-ವ್ಯಾಕ್ಸಿ
                       Livestock Health Assistant Application

   "Preventing animal loss, one vaccination at a time."

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

[ BADGES ]

  Platform   : Android (API 24+)
  Language   : Kotlin
  Database   : Room (SQLite)
  UI         : Material Design 3 (Views / XML)
  Background : WorkManager
  License    : MIT
  Status     : Active Development

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

TABLE OF CONTENTS

  1.  About the Project
  2.  The Problem Statement
  3.  The Vision
  4.  Key Features
  5.  Screenshots
  6.  Tech Stack
  7.  Architecture
  8.  Project Structure
  9.  Getting Started
  10. Installation
  11. How to Use
  12. Vaccination Schedule Reference
  13. Notification System
  14. Kannada Localization
  15. Database Schema
  16. WorkManager Setup
  17. Contributing
  18. Impact Goals
  19. Future Roadmap
  20. License
  21. Acknowledgements
  22. Contact

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

1. ABOUT THE PROJECT
━━━━━━━━━━━━━━━━━━━

Grama-Vaxi (ಗ್ರಾಮ-ವ್ಯಾಕ್ಸಿ) is a bilingual (Kannada + English) Android application
designed for rural farmers in Karnataka, India. It serves as a Digital Health Card
for livestock — tracking every sheep, goat, cow, and buffalo owned by village farmers.

The app solves a critical rural healthcare problem: livestock in Indian villages
often die from preventable disease outbreaks because farmers miss vaccination camp
dates that are announced only through local loudspeakers, which many farmers never
hear in time.

Grama-Vaxi ensures that:
  → Every farmer knows exactly when a vaccination camp is coming
  → Every animal has a complete digital health record
  → Loud notifications are sent 3 days before a camp reaches the village
  → Sick animals can be reported to the local vet instantly

This project was developed as part of an Android App Development using GenAI
coursework, focusing on rural healthcare digitization.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

2. THE PROBLEM STATEMENT
━━━━━━━━━━━━━━━━━━━━━━━━

REAL-WORLD IMPACT:
  - Livestock is the primary "savings account" of rural Indian farmers
  - Disease outbreaks like FMD, PPR, HS can wipe out entire herds within days
  - Government vaccination camps visit villages periodically but with minimal notice
  - Announcements are made only via loudspeakers — easily missed by farmers in fields
  - No centralized health record system exists for individual village livestock
  - Farmers have no way to track when each animal's next shot is due

RESULT:
  - Preventable deaths cause severe financial loss to farming families
  - Disease spreads between animals and across villages uncontrolled
  - No data exists to help government plan effective vaccination campaigns

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

3. THE VISION
━━━━━━━━━━━━━

Grama-Vaxi envisions a Karnataka where:

  ✦ Every village animal has a Digital Health Card
  ✦ Every farmer receives timely vaccination alerts on their phone
  ✦ Government vets have access to village animal health data
  ✦ Disease outbreaks are detected and reported instantly
  ✦ Livestock wealth is protected through technology

The name "Grama-Vaxi" combines:
  → "Grama" (ಗ್ರಾಮ) = Village in Kannada
  → "Vaxi"             = Short for Vaccination

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

4. KEY FEATURES
━━━━━━━━━━━━━━━

CORE FEATURES:

  [1] ANIMAL LEDGER (ಪ್ರಾಣಿ ಹಿಸಾಬು)
      ● Register sheep, goat, cow, buffalo with photo
      ● Store breed, age, tag number, and owner details
      ● Visual animal cards with large icons
      ● Complete offline storage with Room Database

  [2] DIGITAL HEALTH CARD (ಆರೋಗ್ಯ ಕಾರ್ಡ್)
      ● Auto-generated vaccination schedule per animal type
      ● Complete vaccination history timeline
      ● Track FMD, PPR, HS, BQ, Anthrax vaccines
      ● Status badges: Done / Due / Overdue

  [3] VACCINE CALENDAR (ಲಸಿಕೆ ಕ್ಯಾಲೆಂಡರ್)
      ● View all upcoming vaccinations across all animals
      ● Filter by: All / Pending / Overdue
      ● Color-coded status indicators
      ● Sorted by due date

  [4] CAMP ALERTS (ಶಿಬಿರ ಎಚ್ಚರಿಕೆ)
      ● 🚨 LOUD notification 3 days before government camp
      ● Bilingual alert: "ವೈದ್ಯರು ನಾಳೆ Temple Square ಗೆ ಬರುತ್ತಿದ್ದಾರೆ"
      ● Camp details: date, time, location, veterinarian, vaccines available
      ● WorkManager ensures notifications fire even if app is closed for days

  [5] DISEASE REPORTING (ರೋಗ ವರದಿ)
      ● Report sick animals with symptom checklist
      ● Auto-severity detection (Mild / Moderate / Severe)
      ● Simulated vet notification system
      ● Track report status: Pending / Treated / Resolved

  [6] BILINGUAL UI (ಕನ್ನಡ + English)
      ● Every label shown in both Kannada and English
      ● Works regardless of phone language setting
      ● Full values-kn resource support
      ● Designed for low-literacy farmers with visual icons

  [7] OFFLINE FIRST
      ● 100% functional without internet connection
      ● All data stored locally in Room (SQLite) database
      ● No cloud dependency for core features

  [8] BACKGROUND NOTIFICATIONS
      ● WorkManager schedules reminders months in advance
      ● Fires even when app hasn't been opened for days
      ● Vibration + alarm sound for camp alerts
      ● Separate channels for vaccination reminders and camp alerts

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

5. SCREENSHOTS
━━━━━━━━━━━━━━

  Screen 1: Login / Splash Screen
  → Grama-Vaxi logo with cow illustration
  → Bilingual login form (ಲಾಗಿನ್ / Login)
  → Green theme with Kannada text

  Screen 2: Main Dashboard
  → Camp Alert Banner at top (yellow warning)
  → Stats: Total Animals | Pending Vaccines | Camps
  → 6-card grid menu with Kannada + English labels:
     ನನ್ನ ಪ್ರಾಣಿಗಳು / My Animals
     ಲಸಿಕೆ ಕ್ಯಾಲೆಂಡರ್ / Vaccine Calendar
     ಲಸಿಕೆ ಶಿಬಿರಗಳು / Camp Alerts
     ರೋಗ ವರದಿ / Disease Report
     ಆರೋಗ್ಯ ಇತಿಹಾಸ / Health History
     ವೈದ್ಯ ಸಂಪರ್ಕ / Vet Contact

  Screen 3: Vaccine Calendar
  → List of all animals with upcoming vaccines
  → Countdown badges: "3 ದಿನಗಳು ಬಾಕಿ" (3 days left)
  → Filter tabs: ಎಲ್ಲಾ / ಬಾಕಿ ಇರುವ / ಪ್ರೋತ್ಯಾದ

  Screen 4: Camp Alert Detail
  → Full camp details in Kannada + English
  → Location, Date, Time, Doctor name, Phone
  → "ಧ್ವನಿ ಮೂಲಕ ಕೇಳಿ / Listen" audio button

  (Add actual screenshot images to /screenshots/ folder in your repository)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

6. TECH STACK
━━━━━━━━━━━━━

  Category              Technology / Library           Version
  ──────────────────────────────────────────────────────────────
  Language              Kotlin                         1.9.20
  Platform              Android SDK                    API 24 - 34
  UI Framework          Views + ViewBinding (XML)      -
  Material Design       Material Components 3          1.11.0
  Architecture          MVVM                           -
  Database              Room (SQLite)                  2.6.1
  Database Compiler     KSP (Kotlin Symbol Processing) 1.9.20-1.0.14
  Background Tasks      WorkManager                    2.9.0
  Lifecycle             ViewModel + LiveData           2.7.0
  Async                 Kotlin Coroutines              1.7.3
  Image Loading         Coil                           2.5.0
  Build System          Gradle (Kotlin DSL)            8.2.0
  Min SDK               Android 7.0 (Nougat)           API 24
  Target SDK            Android 14                     API 34

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

7. ARCHITECTURE
━━━━━━━━━━━━━━━

This app follows MVVM (Model-View-ViewModel) architecture with Repository pattern:

  ┌─────────────────────────────────────────────────────────┐
  │                        UI LAYER                          │
  │   Activities / Dialogs / Adapters (XML Views)            │
  │   SplashActivity → MainActivity → RegisterAnimalActivity │
  │   AnimalDetailsActivity → VaccinationCalendarActivity    │
  │   CampListActivity → DiseaseReportListActivity           │
  └────────────────────┬────────────────────────────────────┘
                       │ observes LiveData
  ┌────────────────────▼────────────────────────────────────┐
  │                    VIEWMODEL LAYER                       │
  │              AnimalViewModel (AndroidViewModel)          │
  │   - allAnimals, upcomingCamps, nextVaccinations          │
  │   - insertAnimalAndScheduleVaccinations()                │
  └────────────────────┬────────────────────────────────────┘
                       │ calls suspend functions
  ┌────────────────────▼────────────────────────────────────┐
  │                   REPOSITORY LAYER                       │
  │                   AnimalRepository                       │
  │   - Single source of truth for all data operations       │
  └────────────────────┬────────────────────────────────────┘
                       │ queries / inserts
  ┌────────────────────▼────────────────────────────────────┐
  │                  DATA LAYER (Room DB)                    │
  │   AppDatabase (gramavaxi_database)                       │
  │   ┌─────────┐  ┌────────────┐  ┌────────────────────┐   │
  │   │ Animal  │  │Vaccination │  │ VaccinationCamp    │   │
  │   │   DAO   │  │    DAO     │  │      DAO           │   │
  │   └─────────┘  └────────────┘  └────────────────────┘   │
  │                ┌──────────────────┐                      │
  │                │  DiseaseReport   │                      │
  │                │      DAO         │                      │
  │                └──────────────────┘                      │
  └─────────────────────────────────────────────────────────┘
                       │
  ┌────────────────────▼────────────────────────────────────┐
  │               BACKGROUND LAYER (WorkManager)             │
  │   VaccinationReminderWorker → checks every 24 hours      │
  │   CampAlertWorker           → checks every 12 hours      │
  │   NotificationHelper        → shows loud notifications   │
  └─────────────────────────────────────────────────────────┘

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

8. PROJECT STRUCTURE
━━━━━━━━━━━━━━━━━━━━

  GramaVaxi/
  ├── app/
  │   ├── src/main/
  │   │   ├── java/com/village/gramavaxi/
  │   │   │   ├── GramaVaxiApplication.kt        ← App init, WorkManager setup
  │   │   │   ├── data/
  │   │   │   │   ├── entity/
  │   │   │   │   │   ├── Animal.kt              ← Animal data model
  │   │   │   │   │   ├── Vaccination.kt         ← Vaccination record model
  │   │   │   │   │   ├── VaccinationCamp.kt     ← Camp schedule model
  │   │   │   │   │   └── DiseaseReport.kt       ← Disease report model
  │   │   │   │   ├── dao/
  │   │   │   │   │   ├── AnimalDao.kt           ← Animal DB queries
  │   │   │   │   │   ├── VaccinationDao.kt      ← Vaccination DB queries
  │   │   │   │   │   ├── VaccinationCampDao.kt  ← Camp DB queries
  │   │   │   │   │   └── DiseaseReportDao.kt    ← Report DB queries
  │   │   │   │   └── database/
  │   │   │   │       └── AppDatabase.kt         ← Room DB instance
  │   │   │   ├── repository/
  │   │   │   │   └── AnimalRepository.kt        ← Data source abstraction
  │   │   │   ├── viewmodel/
  │   │   │   │   └── AnimalViewModel.kt         ← UI state + business logic
  │   │   │   ├── ui/
  │   │   │   │   ├── main/
  │   │   │   │   │   ├── SplashActivity.kt      ← Login screen
  │   │   │   │   │   ├── MainActivity.kt        ← Dashboard
  │   │   │   │   │   ├── AnimalAdapter.kt       ← RecyclerView adapter
  │   │   │   │   │   ├── VaccinationCalendarActivity.kt
  │   │   │   │   │   ├── CampListActivity.kt
  │   │   │   │   │   ├── AddCampDialog.kt
  │   │   │   │   │   └── DiseaseReportListActivity.kt
  │   │   │   │   ├── register/
  │   │   │   │   │   └── RegisterAnimalActivity.kt  ← Add new animal
  │   │   │   │   └── details/
  │   │   │   │       ├── AnimalDetailsActivity.kt   ← Animal profile
  │   │   │   │       ├── VaccinationAdapter.kt      ← Vaccination list
  │   │   │   │       └── AddVaccinationDialog.kt    ← Add vaccination
  │   │   │   ├── worker/
  │   │   │   │   ├── VaccinationReminderWorker.kt   ← Daily check
  │   │   │   │   └── CampAlertWorker.kt             ← Camp notifications
  │   │   │   └── utils/
  │   │   │       ├── NotificationHelper.kt          ← Notification builder
  │   │   │       ├── DateUtils.kt                   ← Date formatting
  │   │   │       ├── VaccinationScheduler.kt        ← Auto-schedule logic
  │   │   │       └── ImageHelper.kt                 ← Camera/gallery utils
  │   │   ├── res/
  │   │   │   ├── layout/
  │   │   │   │   ├── activity_splash.xml
  │   │   │   │   ├── activity_main.xml
  │   │   │   │   ├── activity_register_animal.xml
  │   │   │   │   ├── activity_animal_details.xml
  │   │   │   │   ├── activity_list.xml
  │   │   │   │   ├── dialog_add_vaccination.xml
  │   │   │   │   ├── dialog_add_camp.xml
  │   │   │   │   ├── item_animal.xml
  │   │   │   │   ├── item_vaccination.xml
  │   │   │   │   ├── item_vaccination_simple.xml
  │   │   │   │   ├── item_camp.xml
  │   │   │   │   └── item_disease_report.xml
  │   │   │   ├── values/
  │   │   │   │   ├── strings.xml                ← English strings
  │   │   │   │   ├── colors.xml
  │   │   │   │   └── themes.xml
  │   │   │   ├── values-kn/
  │   │   │   │   └── strings.xml                ← Kannada strings
  │   │   │   ├── drawable/
  │   │   │   │   ├── ic_cow.xml
  │   │   │   │   ├── ic_sheep.xml
  │   │   │   │   ├── ic_goat.xml
  │   │   │   │   ├── ic_buffalo.xml
  │   │   │   │   ├── ic_notification.xml
  │   │   │   │   ├── ic_calendar.xml
  │   │   │   │   ├── ic_health.xml
  │   │   │   │   ├── ic_add.xml
  │   │   │   │   ├── ic_back.xml
  │   │   │   │   ├── ic_camera.xml
  │   │   │   │   ├── circle_dot.xml
  │   │   │   │   └── badge_background.xml
  │   │   │   └── xml/
  │   │   │       ├── file_paths.xml
  │   │   │       ├── backup_rules.xml
  │   │   │       └── data_extraction_rules.xml
  │   │   └── AndroidManifest.xml
  │   ├── build.gradle.kts                       ← App-level dependencies
  └── build.gradle.kts                           ← Project-level plugins

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

9. GETTING STARTED
━━━━━━━━━━━━━━━━━━

PREREQUISITES:

  ● Android Studio Hedgehog (2023.1.1) or newer
  ● JDK 17
  ● Android SDK (API 24 minimum, API 34 target)
  ● Git installed on your system
  ● Physical Android device (API 24+) OR Android Emulator

SYSTEM REQUIREMENTS:

  ● OS: Windows 10/11, macOS 12+, or Ubuntu 20.04+
  ● RAM: 8 GB minimum (16 GB recommended)
  ● Disk: 4 GB free space for project + Android SDK
  ● Android Studio: ~2.5 GB installation

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

10. INSTALLATION
━━━━━━━━━━━━━━━━

STEP 1: Clone the repository

  git clone https://github.com/YOUR_USERNAME/GramaVaxi.git
  cd GramaVaxi

STEP 2: Open in Android Studio

  → Launch Android Studio
  → Click "Open"
  → Navigate to the cloned GramaVaxi folder
  → Click "OK"
  → Wait for Gradle sync to complete (2-5 minutes first time)

STEP 3: Verify Gradle sync

  → You should see "BUILD SUCCESSFUL" in the Build output
  → No red underlines in any Kotlin files

STEP 4: Run the app

  → Connect your Android device via USB (enable Developer Mode + USB Debugging)
  → OR create an Android Virtual Device (AVD) via Tools → Device Manager
  → Click the Run ▶️ button or press Shift+F10
  → Select your device
  → Wait for app to install and launch

STEP 5: First launch

  → You will see the Grama-Vaxi login screen
  → Enter any mobile number and password (simulated login)
  → Tap "ಲಾಗಿನ್ / LOGIN" to enter the dashboard
  → Grant notification permission when prompted (required for alerts)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

11. HOW TO USE
━━━━━━━━━━━━━━

REGISTERING AN ANIMAL:
  1. Tap the floating "+" button or "ನನ್ನ ಪ್ರಾಣಿಗಳು / My Animals" card
  2. Fill in animal details:
     - Name (e.g., Lakshmi, Ramu)
     - Type: Sheep / Goat / Cow / Buffalo
     - Breed (e.g., Bannur, Hallikar)
     - Age in months
     - Unique Tag Number (e.g., ANM001)
     - Owner Name and Phone
  3. Optionally take a photo with camera or choose from gallery
  4. Tap "Save" — vaccination schedule is auto-generated!

VIEWING ANIMAL HEALTH CARD:
  1. Tap any animal from the home screen list
  2. View complete profile with photo, owner details
  3. See full vaccination history timeline
  4. Tap "+ Add" to manually add a vaccination record
  5. Tap the red "Report Sick Animal" button to report illness

ADDING A VACCINATION CAMP:
  1. Tap "ಲಸಿಕೆ ಶಿಬಿರಗಳು / Camp Alerts" from main menu
  2. Tap the "+" floating button
  3. Fill in camp date, location, timing, vet name, vaccines
  4. Save — WorkManager will auto-alert 3 days before the camp date

REPORTING A SICK ANIMAL:
  1. Open any animal's details
  2. Tap the red "🚨 Report Sick Animal" button
  3. Select symptoms from the checklist
  4. Severity is auto-detected based on symptom count
  5. Tap "Report to Vet" — report is saved and simulated vet alert is sent

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

12. VACCINATION SCHEDULE REFERENCE
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

The app auto-generates vaccination schedules using these standard intervals:

SHEEP / GOAT (ಕುರಿ / ಆಡು):
  ┌──────────────────────────────────────────────────────────┐
  │ Vaccine     │ First Shot    │ Next Shot   │ Repeat       │
  ├──────────────────────────────────────────────────────────┤
  │ PPR         │ 3 months      │ 15 months   │ Annual       │
  │ FMD         │ 4 months      │ 10 months   │ 6-monthly    │
  │ HS          │ 6 months      │ 18 months   │ Annual       │
  │ Anthrax     │ 6 months      │ 18 months   │ Annual       │
  └──────────────────────────────────────────────────────────┘

COW / BUFFALO (ಹಸು / ಎಮ್ಮೆ):
  ┌──────────────────────────────────────────────────────────┐
  │ Vaccine     │ First Shot    │ Next Shot   │ Repeat       │
  ├──────────────────────────────────────────────────────────┤
  │ FMD         │ 4 months      │ 10 months   │ 6-monthly    │
  │ HS          │ 6 months      │ 18 months   │ Annual       │
  │ BQ          │ 6 months      │ 18 months   │ Annual       │
  │ Anthrax     │ 6 months      │ 18 months   │ Annual       │
  └──────────────────────────────────────────────────────────┘

KEY:
  FMD  = Foot and Mouth Disease (ಕಾಲು ಬಾಯಿ ರೋಗ)
  PPR  = Peste des Petits Ruminants (ಮೇಕೆ ಜ್ವರ)
  HS   = Haemorrhagic Septicaemia (ರಕ್ತಸ್ರಾವ ರೋಗ)
  BQ   = Black Quarter (ಕಪ್ಪು ಕಾಲು ರೋಗ)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

13. NOTIFICATION SYSTEM
━━━━━━━━━━━━━━━━━━━━━━━

The app has two notification channels:

  CHANNEL 1: Vaccination Alerts (vaccination_alerts)
  → Priority: HIGH
  → Sound: Default notification sound
  → Vibration: Short pattern
  → Fires: Daily WorkManager check for animals due in next 3 days
  → Message example: "Lakshmi's FMD shot is due on 15 May 2025"

  CHANNEL 2: Camp Notifications (camp_alerts)
  → Priority: MAX (ALARM category)
  → Sound: Alarm sound (loud)
  → Vibration: Long strong pattern
  → Fires: 12-hourly WorkManager check for camps in next 3 days
  → Message example: "ವೈದ್ಯರು ನಾಳೆ Temple Square ಗೆ ಬರುತ್ತಿದ್ದಾರೆ"

WorkManager ensures notifications fire even when:
  ✓ App is closed / not running
  ✓ Phone is restarted
  ✓ App hasn't been opened in days/weeks

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

14. KANNADA LOCALIZATION
━━━━━━━━━━━━━━━━━━━━━━━━

The app supports Kannada in two ways:

  METHOD 1: System Language (values-kn/)
  → If phone language is set to Kannada, all strings switch to Kannada
  → Located at: app/src/main/res/values-kn/strings.xml

  METHOD 2: Hardcoded Bilingual Text (Recommended for rural use)
  → All key labels show both Kannada + English regardless of phone language
  → Example: "ನನ್ನ ಪ್ರಾಣಿಗಳು / My Animals"
  → This approach works for all farmers regardless of phone settings
  → Especially important since many rural users may not know how to
    change phone language settings

  FONT SUPPORT:
  → Android natively supports Kannada Unicode rendering
  → No external font library required
  → Noto Sans Kannada is pre-installed on Android 5.0+

  TO TEST KANNADA MODE:
  → Settings → Language & Input → Language → ಕನ್ನಡ (Kannada)
  → Relaunch the app — all strings switch to Kannada

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

15. DATABASE SCHEMA
━━━━━━━━━━━━━━━━━━━

DATABASE NAME: gramavaxi_database

TABLE 1: animals
  ┌─────────────────┬────────────┬──────────────────────────┐
  │ Column          │ Type       │ Description              │
  ├─────────────────┼────────────┼──────────────────────────┤
  │ id              │ INTEGER PK │ Auto-generated ID         │
  │ name            │ TEXT       │ Animal name               │
  │ type            │ TEXT       │ Sheep/Goat/Cow/Buffalo    │
  │ breed           │ TEXT       │ Breed name                │
  │ age             │ INTEGER    │ Age in months             │
  │ photoPath       │ TEXT       │ Internal storage path     │
  │ tagNumber       │ TEXT       │ Unique tag (e.g. ANM001)  │
  │ registrationDate│ INTEGER    │ Unix timestamp            │
  │ ownerName       │ TEXT       │ Farmer's name             │
  │ ownerPhone      │ TEXT       │ Farmer's phone number     │
  └─────────────────┴────────────┴──────────────────────────┘

TABLE 2: vaccinations
  ┌─────────────────┬────────────┬──────────────────────────┐
  │ Column          │ Type       │ Description              │
  ├─────────────────┼────────────┼──────────────────────────┤
  │ id              │ INTEGER PK │ Auto-generated ID         │
  │ animalId        │ INTEGER FK │ References animals.id     │
  │ vaccineName     │ TEXT       │ Full vaccine name         │
  │ vaccinationType │ TEXT       │ FMD/PPR/HS/BQ/Anthrax    │
  │ administeredDate│ INTEGER    │ When given (timestamp)    │
  │ nextDueDate     │ INTEGER    │ Next shot date (timestamp)│
  │ campLocation    │ TEXT       │ Where to get the shot     │
  │ veterinarianName│ TEXT       │ Doctor name (optional)    │
  │ batchNumber     │ TEXT       │ Vaccine batch (optional)  │
  │ notes           │ TEXT       │ Additional notes          │
  │ isCompleted     │ INTEGER    │ 1 = done, 0 = pending     │
  └─────────────────┴────────────┴──────────────────────────┘

TABLE 3: vaccination_camps
  ┌─────────────────┬────────────┬──────────────────────────┐
  │ Column          │ Type       │ Description              │
  ├─────────────────┼────────────┼──────────────────────────┤
  │ id              │ INTEGER PK │ Auto-generated ID         │
  │ campDate        │ INTEGER    │ Camp date (timestamp)     │
  │ location        │ TEXT       │ Camp location name        │
  │ vaccineTypes    │ TEXT       │ Comma-separated vaccines  │
  │ startTime       │ TEXT       │ e.g. "09:00 AM"          │
  │ endTime         │ TEXT       │ e.g. "05:00 PM"          │
  │ veterinarianName│ TEXT       │ Doctor name               │
  │ contactNumber   │ TEXT       │ Doctor phone              │
  │ notificationSent│ INTEGER    │ Alert already sent?       │
  │ notes           │ TEXT       │ Additional notes          │
  └─────────────────┴────────────┴──────────────────────────┘

TABLE 4: disease_reports
  ┌─────────────────┬────────────┬──────────────────────────┐
  │ Column          │ Type       │ Description              │
  ├─────────────────┼────────────┼──────────────────────────┤
  │ id              │ INTEGER PK │ Auto-generated ID         │
  │ animalId        │ INTEGER FK │ References animals.id     │
  │ symptoms        │ TEXT       │ Comma-separated symptoms  │
  │ reportDate      │ INTEGER    │ When reported (timestamp) │
  │ severity        │ TEXT       │ Mild/Moderate/Severe      │
  │ vetNotified     │ INTEGER    │ 1 = yes, 0 = no           │
  │ vetResponse     │ TEXT       │ Vet's response (optional) │
  │ status          │ TEXT       │ PENDING/TREATED/RESOLVED  │
  │ photoPath       │ TEXT       │ Photo of sick animal      │
  └─────────────────┴────────────┴──────────────────────────┘

  FOREIGN KEYS:
  → vaccinations.animalId → animals.id (CASCADE DELETE)
  → disease_reports.animalId → animals.id (CASCADE DELETE)

  INDEXES:
  → vaccinations(animalId) — for fast joins
  → disease_reports(animalId) — for fast joins

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

16. WORKMANAGER SETUP
━━━━━━━━━━━━━━━━━━━━━

WorkManager is configured in GramaVaxiApplication.kt on app startup:

  VACCINATION REMINDER WORKER:
  → Repeat interval: Every 24 hours
  → Flex interval: 30 minutes
  → Initial delay: 1 hour after app install
  → Constraints: No network required, battery not required
  → Unique name: "vaccination_reminders" (KEEP policy)
  → Logic: Queries vaccinations due within next 3 days and notifies

  CAMP ALERT WORKER:
  → Repeat interval: Every 12 hours
  → Flex interval: 30 minutes
  → Initial delay: 30 minutes after app install
  → Constraints: No network required
  → Unique name: "camp_alerts" (KEEP policy)
  → Logic: Queries camps within next 3 days and fires loud alarm notification

  KEY FEATURE:
  WorkManager persists across device reboots. Even if the phone is
  restarted, WorkManager re-registers workers and continues firing
  notifications on schedule — critical for rural users who may not
  open the app regularly.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

17. CONTRIBUTING
━━━━━━━━━━━━━━━━

Contributions are welcome! Here's how to contribute:

  STEP 1: Fork the repository
  → Click "Fork" on the GitHub repository page

  STEP 2: Create your feature branch
  → git checkout -b feature/your-feature-name

  STEP 3: Make your changes
  → Follow the existing MVVM architecture
  → Add Kannada strings for any new UI text
  → Test on a physical device or emulator

  STEP 4: Commit your changes
  → git commit -m "Add: brief description of what you added"

  STEP 5: Push to your branch
  → git push origin feature/your-feature-name

  STEP 6: Open a Pull Request
  → Go to GitHub → your fork → "New Pull Request"
  → Describe what you changed and why

  CONTRIBUTION GUIDELINES:
  ● All new UI text must have both English and Kannada versions
  ● Follow MVVM pattern — no direct DB calls from Activities
  ● Test notifications manually before submitting
  ● Add comments in English for complex logic
  ● Keep animal health data accuracy — verify vaccine schedules

  GOOD FIRST ISSUES:
  ● Add SMS notification fallback (no internet needed)
  ● Add voice announcement of alerts in Kannada (TTS)
  ● Add cattle weight and milk production tracking
  ● Add village-level herd statistics dashboard
  ● Add export to PDF for animal health card

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

18. IMPACT GOALS
━━━━━━━━━━━━━━━━

  LIVESTOCK WEALTH PROTECTION:
  → Livestock is the primary savings and insurance for rural farming families
  → A single FMD outbreak can kill 30-50% of a farmer's herd in days
  → Timely vaccination costs ₹10-50 per animal vs ₹5,000-15,000 loss per animal
  → Target: Zero preventable livestock deaths in villages using this app

  ANIMAL WELFARE:
  → Every animal deserves timely healthcare
  → Sick animals should be seen by a vet within 24 hours of symptoms
  → Vaccination should be systematic, not random

  HEALTH DIGITIZATION:
  → Create the first-ever village-level livestock health database
  → Data can help government plan vaccination camps more efficiently
  → Track disease outbreak patterns geographically

  FARMER EMPOWERMENT:
  → Farmers should not lose income because they missed a loudspeaker announcement
  → Technology should work for people with low literacy and low tech exposure
  → Simple, visual, local-language interface is non-negotiable

  SUCCESS METRICS:
  ✓ Reminders trigger even if app hasn't been opened for 7+ days
  ✓ Register animal form completable in under 2 minutes
  ✓ Kannada text visible without changing phone language
  ✓ Works fully offline with no internet connection
  ✓ Supports Android 7.0+ (covers 95%+ of rural Android devices)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

19. FUTURE ROADMAP
━━━━━━━━━━━━━━━━━━

  VERSION 1.1 (Planned):
  ● SMS alerts for farmers without smartphones
  ● Kannada voice announcements (Text-to-Speech)
  ● QR code animal tag printing
  ● Export animal health card as PDF

  VERSION 1.2 (Planned):
  ● Government vet dashboard (web portal)
  ● Village-level herd health statistics
  ● Multi-village support for veterinary officers
  ● Firebase sync for data backup

  VERSION 2.0 (Vision):
  ● AI disease detection from animal photos
  ● GPS-based camp location tracking
  ● Integration with Karnataka Animal Husbandry Department API
  ● Telemedicine: live video consultation with vet
  ● Multi-language support: Telugu, Tamil, Hindi

  COMMUNITY REQUESTS:
  ● Milk production tracker
  ● Breeding cycle tracker
  ● Feed and nutrition log
  ● Insurance claim support

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

20. LICENSE
━━━━━━━━━━━

  MIT License

  Copyright (c) 2025 Grama-Vaxi Contributors

  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:

  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  SOFTWARE.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

21. ACKNOWLEDGEMENTS
━━━━━━━━━━━━━━━━━━━━

  ● Karnataka Department of Animal Husbandry & Veterinary Services
    → For vaccination schedule reference data

  ● Google Android Team
    → For WorkManager, Room, and Material Design 3 libraries

  ● JetBrains
    → For the Kotlin programming language

  ● Rural farming communities of Karnataka
    → For inspiring this solution and being the reason it exists

  ● Android Developer Documentation
    → https://developer.android.com

  ● Material Design Guidelines
    → https://m3.material.io

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

22. CONTACT
━━━━━━━━━━━

  Project Repository : https://github.com/YOUR_USERNAME/GramaVaxi
  Issues & Bugs      : https://github.com/YOUR_USERNAME/GramaVaxi/issues
  Email              : your.email@example.com

  If you are a Karnataka government official or NGO interested in
  deploying this app for village livestock health programs, please
  reach out — we would love to collaborate.

  ಈ ಅಪ್ಲಿಕೇಶನ್ ಕರ್ನಾಟಕದ ಗ್ರಾಮೀಣ ರೈತರಿಗಾಗಿ ಮಾಡಲಾಗಿದೆ.
  (This application is made for the rural farmers of Karnataka.)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  Made with ❤️ for the farmers of Karnataka | ಕರ್ನಾಟಕದ ರೈತರಿಗಾಗಿ ಪ್ರೀತಿಯಿಂದ ತಯಾರಿಸಲಾಗಿದೆ

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  HOW TO USE THIS FILE ON GITHUB:
  ─────────────────────────────────
  1. Rename this file from README.txt to README.md
  2. Place it in the ROOT folder of your GitHub repository
  3. GitHub will automatically display it on your repository homepage
  4. Replace "YOUR_USERNAME" with your actual GitHub username throughout
  5. Add actual screenshot images to a /screenshots/ folder and
     update the Screenshots section with image links like:
     ![Login Screen](screenshots/login.png)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
