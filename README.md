# 🧠 BrainSpark

![GitHub Repo stars](https://img.shields.io/github/stars/Thogaruchesti-hemanth/BrainSpark?style=social)
![GitHub forks](https://img.shields.io/github/forks/Thogaruchesti-hemanth/BrainSpark?style=social)
![GitHub issues](https://img.shields.io/github/issues/Thogaruchesti-hemanth/BrainSpark)
![MIT License](https://img.shields.io/badge/license-MIT-blue)

> 🎓 **BrainSpark** – A clean, animated mobile quiz app built with Java and Android Studio. Perfect for students, learners, interview prep, and developers who want to explore Android UI + logic in one place.



## 📱 Features

- ✅ Multiple choice questions (MCQs) with 4 options
- 📊 Real-time scoring system
- 🧩 Progress tracking via `ProgressBar`
- ✨ Smooth transitions and animations
- 📑 BottomSheetDialog for quiz results
- 🔁 Quiz restart functionality
- 🎨 Modern Material UI
- 💡 Beginner-friendly, clean codebase


## 📸 Screenshots

> *(Add screenshots in the `/screenshots/` folder and link below)*

| Quiz Screen | Result Screen |
|-------------|---------------|
| ![Question](screenshots/question.png) | ![Result](screenshots/result.png) |



## 🛠️ Tech Stack

| Tech | Usage |
|------|-------|
| **Java** | Core logic & Android app |
| **Android SDK** | UI rendering & framework |
| **Material Components** | Buttons, dialogs, and design |
| **BottomSheetDialog** | Result display |
| **LinearLayoutCompat** | Dynamically added options |
| **View Animation** | Fade-in transition for question changes |


## 📂 Folder Structure

```
BrainSpark/
├── app/
│ └── src/
│ └── main/
│ ├── java/com/example/brainspark/
│ │ ├── activities/MainActivity.java
│ │ ├── data/QuestionProvider.java
│ │ └── models/Question.java
│ ├── res/
│ │ ├── layout/
│ │ │ ├── activity_main.xml
│ │ │ ├── option_layout.xml
│ │ │ └── quiz_result_bottom_sheet.xml
│ │ ├── values/
│ │ │ ├── colors.xml
│ │ │ ├── strings.xml
│ │ │ └── dimens.xml
├── screenshots/
│ ├── question.png
│ └── result.png
├── .gitignore
├── LICENSE
└── README.md
```


## 🚀 Getting Started

### ✅ Prerequisites

- Android Studio (Hedgehog or Flamingo)
- Java JDK 8 or higher
- Android SDK 21+

## 🛠️ Setup

1. **Clone the Repository**

```bash
git clone https://github.com/your-username/BrainSpark.git
```

2. **Open in Android Studio**
  - File → Open → Select the `BrainSpark/` folder
  
3. **Run the App**
  - Choose a device or emulator and press ▶️ Run

## 🧠 How It Works
- Loads question list from QuestionProvider
- Displays one question at a time with 4 radio button options
- User selects an option → Next button gets enabled
- After all questions, score is shown via BottomSheetDialog
- User can restart the quiz from the result screen

-----
## ✨ Roadmap / TODO
- Want to contribute? Here's what we plan to add:
- Countdown timer per question ⏱️
- Quiz categories (Java, Kotlin, Math)
- Local score storage using SharedPreferences
- Firebase Integration (auth + leaderboard)
- Dark mode toggle 🌙
- Animations when changing questions
- Sound effects & confetti 🎉

-------
## 🧑‍💻 Contributing
We welcome contributions from the community!
1. Fork this repo
2. Create your feature branch: git checkout -b feature/your-feature
3. Commit changes: git commit -m 'Add your feature'
4. Push to branch: git push origin feature/your-feature
5. Open a pull request ✔️
Make sure to follow our contribution guidelines (coming soon).


## 📝 License
This project is licensed under the MIT License.
See the LICENSE file for details.

## 👨‍💻 Author
Sai Hemanth
📍 Android & Java Developer
🔗 GitHub · LinkedIn

## 📢 Spread the Word
If you found this project helpful or cool, please ⭐ star the repo and share it with others.
Let’s make BrainSpark a go-to quiz template for Java learners and Android devs!
