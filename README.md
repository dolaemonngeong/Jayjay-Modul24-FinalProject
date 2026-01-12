## Deskripsi
Project ini merupakan automation testing framework untuk pengujian API dan Web UI.
Pengujian Web UI dilakukan pada website https://www.saucedemo.com/ dengan fokus pada fitur Login, Logout, Filter, Cart dan Checkout menggunakan Selenium dan Cucumber.
Pengujian API dilakukan pada https://gorest.co.in/public/v2/ untuk menguji endpoint Get All Users, Get One User, Create User, Update User, Delete User, dan Get Tags menggunakan RestAssured dan Cucumber.
Framework ini menerapkan pendekatan BDD dengan Cucumber serta menggunakan Page Object Model untuk meningkatkan keterbacaan dan maintainability kode.

---

## Teknologi yang Digunakan
- Bahasa Pemrograman Java
- Selenium WebDriver 
- Cucumber
- Gradle
- JUnit 

---

## Struktur Proyek
```text
src/
└── test/
    ├── java/
    │   └── org/davinatw/
    │       ├── apiTest/
    │       │   ├── ApiCucumberTest.java
    │       │   ├── apiHooks.java
    │       │   └── apiStepDef.java
    │       │
    │       ├── WebUITest/
    │       │   ├── page/
    │       │   │   ├── LoginPage.java
    │       │   │   ├── HomePage.java
    │       │   │   ├── CartPage.java
    │       │   │   ├── CheckoutFirstPage.java
    │       │   │   ├── CheckoutSecondPage.java
    │       │   │   └── CheckoutCompletePage.java
    │       │   │
    │       │   └── stepdef/
    │       │       ├── BaseTest.java
    │       │       ├── CucumberHooks.java
    │       │       ├── LoginStepDef.java
    │       │       ├── HomeStepDef.java
    │       │       ├── CartStepDef.java
    │       │       ├── CheckoutStepDef.java
    │       │       └── LogOutStepDef.java
    │       │
    │       └── CucumberTest.java
    │
    └── resources/
        ├── API/
        │   └── jsonSchema/
        │       ├── getAllUsers.json
        │       └── userDetail.json
        │
        ├── api.feature
        │
        └── Web/
            ├── login.feature
            ├── cart.feature
            ├── checkout.feature
            ├── filter.feature
            └── logout.feature


```

---

## Deskripsi Direktori Proyek

#### apiTest
Berisi komponen pengujian API menggunakan RestAssured dan Cucumber.

- ApiCucumberTest.java berfungsi sebagai test runner untuk pengujian API  
- apiHooks.java mengelola konfigurasi awal API seperti base URI  
- apiStepDef.java berisi implementasi step definition untuk skenario pengujian API  

#### WebUITest/page
Berisi class Page Object Model (POM) untuk pengujian Web UI menggunakan Selenium.  
Setiap class merepresentasikan halaman tertentu pada aplikasi.

#### WebUITest/stepdef
Berisi Step Definition dan Hooks Cucumber untuk pengujian Web UI.

- BaseTest.java mengelola konfigurasi WebDriver yang digunakan bersama  
- CucumberHooks.java menangani proses setup dan teardown browser  
- Class step definition mengimplementasikan langkah-langkah Gherkin untuk fitur Web UI  

#### resources/API/jsonSchema
Berisi file JSON Schema yang digunakan untuk memvalidasi struktur response API.

#### resources/Web
Berisi file feature Gherkin untuk skenario pengujian Web UI.

#### api.feature
Berisi skenario Gherkin untuk pengujian otomatis API.

#### CucumberTest.java
Berfungsi sebagai test runner utama Cucumber untuk menjalankan pengujian Web UI dan API.

---

## Cara Menjalankan Test
- Menggunakan terminal
./gradlew clean test

- Menggunakan IntelliJ IDEA
1. Buka class CucumberTest
2. Klik tombol Run

---

## Hasil Report Test
### API

### Web
<img width="1710" height="1107" alt="Screenshot 2026-01-06 at 13 25 43" src="https://github.com/user-attachments/assets/e0ddaa09-7225-48cb-82e3-c735b037b3d1" />

---


## Author
Davina Teresa Wijaya
