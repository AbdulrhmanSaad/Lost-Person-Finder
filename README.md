# Lost Person Finder 👤📱

An Android-based mobile application that helps locate and identify missing persons using facial recognition, powered by machine learning and cloud storage services.

## 📱 Description

**Lost Person Finder** is a mobile app developed in Kotlin for Android devices. It allows users to upload and search for photos of missing individuals. The app connects to a backend API that handles facial recognition using deep learning models and image processing, helping in the identification of people through secure cloud-based technologies.

The backend for this app is hosted separately:
👉 [People_Fined Backend Repository](https://github.com/AhmedYossry552/People_Fined)

## 🧠 Key Features

- 📲 Android mobile application developed in Kotlin
- 📸 Upload and search for person photos
- 🧠 Facial recognition using machine learning
- ☁️ Secure image storage with Azure Blob Storage
- 🔐 User login & account management
- 🔄 Real-time communication with a Flask REST API

## 🏗️ Tech Stack

### Frontend (This Repo)
- Kotlin
- Android SDK
- Retrofit (API communication)
- Glide (Image loading)
- Jetpack components (Navigation, LiveData, ViewModel)

### Backend (in [People_Fined](https://github.com/AhmedYossry552/People_Fined))
- Python + Flask
- TensorFlow / OpenCV / dlib
- MySQL database
- Azure Blob Storage

## 🚀 Getting Started

### 1. Clone the Repositories

```bash
# Clone the Android mobile app
git clone https://github.com/AbdulrhmanSaad/Lost-Person-Finder.git

# Clone the backend Flask API
git clone https://github.com/AhmedYossry552/People_Fined.git
