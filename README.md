# ISTAD LMS API Documentation 🚀

Welcome to the official documentation for the ISTAD LMS API. This documentation provides detailed information on how to use and integrate with our platform to streamline and enhance school management processes.

## 📑 Table of Contents

1. [Introduction](#introduction) 🌟
2. [Security Management](#security-management)🔐
- [auth](#auth)
- [password](#password)
- [authority](#authority)
3. [Faculty Management](#faculty-management)👩‍🏫
- [faculty](#faculty)
- [degree](#degree)
- [studyProgram](#studyprogram)
- [yearOfStudy](#yearofstudy)
  - [subjects](#subjects)
- [subject](#subject)
- [shifts](#shifts)
4. [Admission Management](#admission-management)📝
- [admission](#admission)
- [studentAdmission](#studentAdmission) 👨
5. [Academic Management](#academic-management)🔄
- [generation](#generation)
- [class](#class)
  - [students](#students)
- [course](#course)
  - [instructor](#instructor)
- [lecture](#lecture)
- [score](#score)
- [attendance](#attendance)
6. [User Management](#user-management)👤
- [user](#user)
- [admin](#admin)
- [instructor](#instructor)
- [student](#student)
- [academic](#academic)
- [staff](#staff)
7. [Material Management](#material-management)📹
- [medias](#medias)
- [material](#material)
- [curriculum](#curriculum)
8. [Payment Management](#payment-management)💵
- [payment](#payment)
- [receipt](#receipt)
9. [Public Website](#public-website) 🎓
- [graduation](#graduation)

---

## Introduction <a name="introduction"></a>

ISTAD LMS API is a comprehensive platform designed to support school management tasks. It offers robust features for managing courses, faculty, students, and more, making it easier for educational institutions to streamline their operations and enhance the learning experience.

---

## For application.ylm profile <a name=""></a>


- **Instructor:** Profile use [test]
- **Student and frontend:** Profile use [dev]

---
## DownLoad postman document <a name=""></a>
**Link:** https://drive.google.com/file/d/1f_HXE7LdqyFopPTr-r8ZhKKoR1yhE7U5/view?usp=sharing
---
## ISTAD LMS API <a name="istad-lms-api"></a>

<details><summary>Show/Hide</summary>

## Security Management <a name="security-management"></a>

<details><summary>Show/Hide</summary>

## auth <a name="auth"></a>

<details><summary>Show/Hide</summary>

- **login** ➕
  - **Description:** Endpoint to login.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/auth/login`
  - **Request Body:**

```json
{

  "emailOrUsername": "admin@gmail.com",

  "password": "admin"

}


```
- **Response:** No examples available

- **register(not use)** ➕
  - **Description:** Endpoint to register(not use).
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/auth/register`
  - **Request Body:**

```json
{

   "alias": "pov soknem",

  "nameEn": "soknem",

  "nameKh": "soknem",

  "username": "soknem1234",

   "gender": "Male",

  "email": "soknem@gmail.com",

  "password": "password123",

  "profileImage": "http://example.com/profile.jpg",

  "phoneNumber": "9745632366",

  "cityOrProvince": "Phnom Penh",

  "khanOrDistrict": "Chamkar Mon",

  "sangkatOrCommune": "Tonle Bassac",

  "street": "123",

  "authorities": [

    {

      "authorityName": "user:write"

    }

  ]

}
```
- **Response:** No examples available

- **refresh** ➕
  - **Description:** Endpoint to refresh.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/auth/refresh`
  - **Request Body:**

```json
{

  "refreshToken": "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJJVFNBRC1MTVMiLCJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJleHAiOjE3MTg1NTE4NTQsImlhdCI6MTcxNzk0NzA1NH0.fuj9CvC0k6-oPjbqDAA5ypQAdhtRetA5oriQrTrBopgUTiETbNdHI08QvHu4lCOxYfthxnn-2ZhhyvjBjUdXlNi1DqNjLPaya-p-P4sEA8KkjwaE8avtUs4NY7MZolbik0tTgRlhhIex0Y9PMVc3OmQH9XUS8u37XqlDnoygYjM_e5rK13IckGCpva9mapUPTMq-_rE7lueiQu0NcuknOvnT-OcSFM2n6zYjGn7ajEApOjtPUE2smGAZjQytUOQpYg-NfMXM8CMBfXIUDvF0I00idw5DpdSoDnKQXA5vwp4ruIdiJD7pFmB3OPpIHzRU9fWBN63PnHlzjXoMOzEOiw"

}
```
- **Response:** No examples available

</details>

## password <a name="password"></a>

<details><summary>Show/Hide</summary>

- **getPassword** ➕
  - **Description:** Endpoint to getpassword.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/password/view`
  - **Request Body:**

```json
{

    "usernameOrEmail" : "admin"

}
```
- **Response:** No examples available

- **changePassword** ➕
  - **Description:** Endpoint to changepassword.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/password/change`
  - **Request Body:**

```json
{

  "emailOrUsername": "it.chhaya@gmail.com",

  "oldPassword": "securepassword123",

  "newPassword": "ChhayaISTAD@12",

  "confirmNewPassword": "ChhayaISTAD@12"

}


```
- **Response:** No examples available

- **resetPassword** ➕
  - **Description:** Endpoint to resetpassword.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/password/reset`
  - **Request Body:**

```json
{

    "usernameOrEmail" : "john_doe"

}
```
- **Response:** No examples available

</details>

## authority <a name="authority"></a>

<details><summary>Show/Hide</summary>

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/authorities?pageNum=0&pageSize=50`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **create** ➕
  - **Description:** Endpoint to create.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/authorities`
  - **Request Body:**

```json
{

    "authorityName": "student:read",

    "description": null

}
```
- **Response:** No examples available

</details>

</details>

## Faculty Management <a name="faculty-management"></a>

<details><summary>Show/Hide</summary>

## faculty <a name="faculty"></a>

<details><summary>Show/Hide</summary>

- **createNew** ➕
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/faculties`
  - **Request Body:**

```json
{

    "alias": "sciences-and-technologies",

    "name": "Sciences and Technologies",

    "description": "Sciences and technologies are interconnected fields that aim to understand and apply the natural and physical laws of the universe to develop innovative solutions and improve human life. The following information provides an overview of the various fields of sciences and technologies.",

    "logo": "",

    "address": "123 University Ave",

    "isDraft": false

}
```
- **Response:** No examples available

- **getByAlias** ➕
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/faculties/sciences-and-technologies`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/faculties?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **updateByAlias** ➕
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/faculties/business-management-i`
  - **Request Body:**

```json
{

    "name": "Business Management",

    "description": "This department now also includes courses on artificial intelligence and machine learning.",

    "address": "456 College St",

    "logo": ""

}
```
- **Response:** No examples available

- **deleteByAlias(not use)** ➕
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/faculties/mis`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **filter** ➕
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/faculties/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "OR",

  "specsDto": [

    {

      "column": "isDeleted",

      "value": "false",

      "operation": "EQUAL"

    }

  ]

}


```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/faculties/business-management/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **public** ➕
  - **Description:** Endpoint to public.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/faculties/business-management/public`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **draft** ➕
  - **Description:** Endpoint to draft.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/faculties/business-management/draft`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/faculties/business-management/disable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## degree <a name="degree"></a>

<details><summary>Show/Hide</summary>

- **createNew** ➕
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/degrees`
  - **Request Body:**

```json
{

    "alias":"bachelor",

    "level": "Bachelor Degree",

    "description": "This is master degree for student who finished bachelor",

    "isDraft":false

}


```
- **Response:** No examples available

- **getByAlias** ➕
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/degrees/bachelor`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/degrees?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **updateByAlias** ➕
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/degrees/master`
  - **Request Body:**

```json
{

    "alias":"master-i",

    "level": "Level 1",

    "description": "This is the first level for master."

}
```
- **Response:** No examples available

- **deleteByAlias(not use)** ➕
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/degrees/master`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **filter** ➕
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/degrees/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "OR",

  "specsDto": [

    {

      "column": "alias",

      "value": "M",

      "operation": "LIKE"

    }

  ]

}


```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/degrees/master-i/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/degrees/master-i/disable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **public** ➕
  - **Description:** Endpoint to public.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/degrees/master-i/public`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **draft** ➕
  - **Description:** Endpoint to draft.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/degrees/master-i/draft`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## studyProgram <a name="studyprogram"></a>

<details><summary>Show/Hide</summary>

- **createNew** ➕
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/study-programs`
  - **Request Body:**

```json
{

    "alias": "management-information-systems-master-i",

    "studyProgramName": "Master of Management Information Systems",

    "logo":"",

    "description": "The Master of Management Information Systems (MIS) is a degree program that focuses on the application of technology to solve business problems.",

    "facultyAlias": "business-management",

    "degreeAlias": "master-i",

    "isDraft":false

}
```
- **Response:** No examples available

- **updateByAlias** ➕
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/study-programs/management-information-systems-master-i`
  - **Request Body:**

```json
{

    "alias": "management-information-systems-master-iii",

    "studyProgramName": "Bachelor of MIS",

    "description": "Find out who's wearing what number in the Aston Villa squad this season, plus appearance and goal statistics.",

    "logo": ""

}
```
- **Response:** No examples available

- **getByAlias** ➕
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/study-programs/management-information-systems-master-ii`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/study-programs?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **deleteByAlias(not use)** ➕
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/study-programs/{uuid}`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **filter** ➕
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/study-programs/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "OR",

  "specsDto": [

    {

      "column": "isDeleted",

      "value": "false",

      "operation": "EQUAL"

    }

  ]

}


```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/study-programs/management-information-systems-master/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/study-programs/management-information-systems-master/disable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **public** ➕
  - **Description:** Endpoint to public.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/study-programs/management-information-systems-master/public`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **draft** ➕
  - **Description:** Endpoint to draft.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/study-programs/management-information-systems-master/draft`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## yearOfStudy <a name="yearofstudy"></a>

<details><summary>Show/Hide</summary>

## subjects <a name="subjects"></a>

<details><summary>Show/Hide</summary>

- **addSubjectsByAlias** ➕
  - **Description:** Endpoint to addsubjectsbyalias.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/year-of-studies/94a6a16c-7c76-47da-abba-352716070915/subjects`
  - **Request Body:**

```json
{

    "aliasOfSubjects": [

        "java-programming","reactJs"

    ]

}
```
- **Response:** No examples available

- **deletedSubjectFromYearOfStudy** ➕
  - **Description:** Endpoint to deletedsubjectfromyearofstudy.
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/year-of-studies/84f538cf-b65d-43e7-94ce-2842431f4395/subjects/java-programming`
  - **Request Body:**

```json

```
- **Response:** No examples available

</details>

- **createNew** ➕
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/year-of-studies`
  - **Request Body:**

```json
{

  "year": 1,

  "semester": 2,

  "studyProgramAlias": "management-information-systems-master",

  "isDraft":false

}
```
- **Response:** No examples available

- **updateByUuid** ➕
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/year-of-studies/94a6a16c-7c76-47da-abba-352716070915`
  - **Request Body:**

```json
{

  "year": 1,

  "semester": 1

}
```
- **Response:** No examples available

- **getByUuid** ➕
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/year-of-studies/94a6a16c-7c76-47da-abba-352716070915`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/year-of-studies?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **deleteByUuid(not use)** ➕
  - **Description:** Endpoint to deletebyuuid(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/year-of-studies/f9286264-e902-431b-a837-22b95e8afaf6`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **filter** ➕
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/year-of-studies/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "OR",

  "specsDto": [

    {

      "column": "year",

      "value": "1",

      "operation": "EQUAL"

    }

  ]

}


```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/year-of-studies/94a6a16c-7c76-47da-abba-352716070915/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/year-of-studies/94a6a16c-7c76-47da-abba-352716070915/disable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **public** ➕
  - **Description:** Endpoint to public.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/year-of-studies/94a6a16c-7c76-47da-abba-352716070915/public`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **draft** ➕
  - **Description:** Endpoint to draft.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/year-of-studies/94a6a16c-7c76-47da-abba-352716070915/draft`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## subject <a name="subject"></a>

<details><summary>Show/Hide</summary>

- **createNew** ➕
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/subjects`
  - **Request Body:**

```json
{

  "alias": "java-programming",

  "title": "java programing",

  "logo":"",

  "internship":1,

  "theory":2,

  "practice":1,

  "duration":80,

  "curriculum": {

    "modules": [

      {

        "title": "Basics of Computing",

        "content": "Introduction to basic computing concepts."

      },

      {

        "title": "Programming Fundamentals",

        "content": "Learning the basics of programming."

      }

    ]

  },

  "thumbnail": "http://example.com/thumbnail.jpg",

  "description": "This is a sample description.",

  "isDraft": true

}


```
- **Response:** No examples available

- **updateByAlias** ➕
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/subjects/reactJs`
  - **Request Body:**

```json
{

    "alias": "reactJs",

    "subjectName": " ReactJs framework",

     "description": "The Spring Framework is an application framework and inversion of control container for the Java platform.",

    "logo": "1f49fc23-3da5-4fe0-a50f-7140ff29cffa.png",

    "duration": 80

}
```
- **Response:** No examples available

- **getByAlias** ➕
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/subjects/reactJs`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/subjects?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **deleteByAlias(not use)** ➕
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/subbjects/java-basic`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **filter** ➕
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/subjects/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "OR",

  "specsDto": [

    {

      "column": "alias",

      "value": "react",

      "operation": "LIKE"

    }

  ]

}


```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/subjects/reactJs/disable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/subjects/java-basic/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **public** ➕
  - **Description:** Endpoint to public.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/subjects/java-basic/public`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **private** ➕
  - **Description:** Endpoint to private.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/subjects/java-basic/private`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## shifts <a name="shifts"></a>

<details><summary>Show/Hide</summary>

- **createNew** ➕
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/shifts`
  - **Request Body:**

```json
{

  "alias": "weekday-morning",

  "name": "Weekday morning",

  "startTime": "08:00:00",

  "endTime": "12:00:00",

  "weekday": true,

  "isDraft":false,

  "description": "This is the morning shift for weekday"

}


```
- **Response:** No examples available

- **updateByAlias** ➕
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/shifts/weekday-evening`
  - **Request Body:**

```json
{

  "alias": "weekday-afternoon",

  "name": "Weekday Afternoon",

  "startTime": "13:30:00",

  "endTime": "17:30:00",

  "weekday": true,

  "description": "This is the afternoon shift for weekday"

}
```
- **Response:** No examples available

- **getByAlias** ➕
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/shifts/weekday-morning`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/shifts?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **deleteByAlias(not use)** ➕
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/shifts/weekday-evening`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **filter** ➕
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/shifts/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "OR",

  "specsDto": [

    {

      "column": "startTime",

      "value": "18:00:00",

      "operation": "LESS_THAN"

    }

  ]

}


```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/shifts/weekday-morning/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/shifts/weekday-morning/disable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **public** ➕
  - **Description:** Endpoint to public.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/shifts/weekday-morning/public`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **draft** ➕
  - **Description:** Endpoint to draft.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/shifts/weekday-morning/draft`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

</details>

## Admission Management <a name="admission-management"></a>

<details><summary>Show/Hide</summary>

## admission <a name="admission"></a>

<details><summary>Show/Hide</summary>

- **createNew** ➕
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/admissions`
  - **Request Body:**

```json
{

  "status": 1,

  "remark": "this admission for first generation",

  "openDate": "2027-05-30",

   "endDate": "2028-05-30",

  "telegramLink": "https://t.me/admission_group"

}


```
- **Response:** No examples available

- **getByUuid** ➕
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/admissions/68eb704f-eed1-4e52-949a-ecc0f9b42931`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/admissions?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **updateByUuid** ➕
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/admissions/68eb704f-eed1-4e52-949a-ecc0f9b42931`
  - **Request Body:**

```json
{ "remark": "update",

  "endDate": "2028-02-02",

  "telegramLink":"no link yet"

}
```
- **Response:** No examples available

- **deleteByUuid(not use)** ➕
  - **Description:** Endpoint to deletebyuuid(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/admissions/beb2fcee-0e2f-4377-88dd-04221a56dcb4`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **filter** ➕
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/admissions/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "AND",

  "specsDto": [

    {

      "column": "status",

      "value": "2",

      "operation": "EQUAL"

    }

  ]

}


```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/admissions/68eb704f-eed1-4e52-949a-ecc0f9b42931/disable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/admissions/68eb704f-eed1-4e52-949a-ecc0f9b42931/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **updateStatusByUuid** ➕
  - **Description:** Endpoint to updatestatusbyuuid.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/admissions/68eb704f-eed1-4e52-949a-ecc0f9b42931/status`
  - **Request Body:**

```json
{

    "status":1

}
```
- **Response:** No examples available

</details>

## studentAmission <a name="studentamission"></a>

<details><summary>Show/Hide</summary>

- **createNew** ➕
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/student-admissions`
  - **Request Body:**

```json
{

  "nameEn": "pov soknem",

  "nameKh": "ពៅ​ សុខណែម",

  "email": "soknem@gmail.com",

  "highSchool": "snoul highSchool",

  "phoneNumber": "021456894",

  "dob": "2003-01-01",

  "pob": "karatie province",

  "bacIiGrade": "A",

  "gender": "Male",

  "avatar": "",

  "address": "123 Main Street",

  "guardianContact": "+1987654321",

  "guardianRelationShip": "Parent",

  "knownIstad": "By social media",

  "identity": "123ABC",

  "biography": ".",

  "shiftAlias": "weekday-morning",

  "studyProgramAlias": "management-information-systems-master",

  "degreeAlias": "master-i"

}




```
- **Response:** No examples available

- **getByUuid** ➕
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/student-admissions/736b0871-2c04-4dc9-a8d7-6d2d8bd9fee3`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/student-admissions?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **updateByUuid** ➕
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/student-admissions/2eef8ea3-158b-4ad6-890a-58cfd12133d1`
  - **Request Body:**

```json
{ "nameEn": "soknem",

  "email": "soknem@example.com",

  "dob": "1990-01-01",

  "gender": "Males",

  "avatar": "https://example.com/avatar.jpg"

}
```
- **Response:** No examples available

- **deleteByUuid(not use)** ➕
  - **Description:** Endpoint to deletebyuuid(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/student-admissions/beb2fcee-0e2f-4377-88dd-04221a56dcb4`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **filter** ➕
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/student-admissions/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "AND",

  "specsDto": [

    {

      "column": "nameEn",

      "value": "s",

      "operation": "LIKE"

    },

    {

      "column": "level",

      "value": "level2",

      "operation": "EQUAL",

      "joinTable": "degree"

    }

  ]

}


```
- **Response:** No examples available

</details>

</details>

## Academic Management <a name="academic-management"></a>

<details><summary>Show/Hide</summary>

## generation <a name="generation"></a>

<details><summary>Show/Hide</summary>

- **createNew** ➕
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/generations`
  - **Request Body:**

```json
{

  "alias": "gen1",

  "name": "Generation ",

  "description": "This is the first generation.",

  "startYear": 2025,

  "endYear": 2026,

  "isDraft":false

}


```
- **Response:** No examples available

- **getByAlias** ➕
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/generations/gen1`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/generations?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **updateByAlias** ➕
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/generations/gen1`
  - **Request Body:**

```json
{

  "alias": "gen1",

  "name": "Generation 1",

  "description": "This is the first generation.",

  "startYear": 2025,

  "endYear": 2027

}


```
- **Response:** No examples available

- **deleteByAlias(not use)** ➕
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/generations/gen1`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **filter** ➕
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/generations/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "OR",

  "specsDto": [

    {

      "column": "alias",

      "value": "g",

      "operation": "LIKE"

    }

  ]

}


```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/generations/gen1/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/generations/gen1/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **public** ➕
  - **Description:** Endpoint to public.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/generations/gen1/public`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **draft** ➕
  - **Description:** Endpoint to draft.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/generations/gen1/draft`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## class <a name="class"></a>

<details><summary>Show/Hide</summary>

## students <a name="students"></a>

<details><summary>Show/Hide</summary>

- **addStudentByUuid** ➕
  - **Description:** Endpoint to addstudentbyuuid.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/classes/dev-op/students`
  - **Request Body:**

```json
{

    "studentUuid":[

        "dd87eb8f-974a-4f9d-874a-1d2daad5f7a0"

    ]

}
```
- **Response:** No examples available

- **deleteStudentByUuid** ➕
  - **Description:** Endpoint to deletestudentbyuuid.
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/classes/{alias}/students/{uuid}`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

- **createNew** ➕
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/classes`
  - **Request Body:**

```json
{

  "alias": "f6",

  "className": "data",

  "description": "data analytics class",

  "year":1,

   "instructorUuid": null,

  "studyProgramAlias": "management-information-systems-master",

  "shiftAlias": "weekday-morning",

  "generationAlias": "gen1",

  "studentUuid": [

        

  ],

  "isDraft":false

}


```
- **Response:** No examples available

- **getByAlias** ➕
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/classes/dev-op2`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/classes?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **updateByAlias** ➕
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/classes/beginners.`
  - **Request Body:**

```json
{

    "alias":"beginner",

    "level": "Level 2",

    "description": "This is the first level for beginners."

}
```
- **Response:** No examples available

- **deleteByAlias(not use)** ➕
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/classes/e2`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **filter** ➕
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/classes/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "OR",

  "specsDto": [

    {

      "column": "alias",

      "value": "gen1",

      "operation": "EQUAL",

      "joinTable": "generation"

    }

  ]

}


```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/classes/alias/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/classes/alias/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **public** ➕
  - **Description:** Endpoint to public.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/classes/alias/public`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **draft** ➕
  - **Description:** Endpoint to draft.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/classes/alias/draft`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## course <a name="course"></a>

<details><summary>Show/Hide</summary>

## instructor <a name="instructor"></a>

<details><summary>Show/Hide</summary>

- **addInstructorByUuid** ➕
  - **Description:** Endpoint to addinstructorbyuuid.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/courses/{alias}/instructors/{uuid}`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **deleteInstructorByUuid** ➕
  - **Description:** Endpoint to deleteinstructorbyuuid.
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/courses/{alias}/instructors/{uuid}`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

- **createNew(not use)** ➕
  - **Description:** Endpoint to createnew(not use).
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/courses`
  - **Request Body:**

```json
{

    "alias":"java2",

    "title": "java2",

    "status": 1,

    "subjectAlias":"java-basic",

    "instructorUuid": "7a0339b5-62e2-4dd3-bc8d-c4de2402b2d0",

    "classAlias": "db1",

    "isDraft":false

}


```
- **Response:** No examples available

- **getByAlias** ➕
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/courses/java-advance-data`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/courses?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **updateByAlias** ➕
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/courses/java2`
  - **Request Body:**

```json
{

    "alias":"beginner",

    "level": "Level 2",

    "description": "This is the first level for beginners."

}
```
- **Response:** No examples available

- **deleteByAlias(not use)** ➕
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/course/{alias}`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **filter** ➕
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/courses/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "AND",

  "specsDto": [

    {

      "column": "alias",

      "value": "java",

      "operation": "LIKE"

    },

    {

      "column": "alias",

      "value": "d",

      "operation": "LIKE",

      "joinTable":"oneClass"

    },

    {

      "column": "alias",

      "value": "gen2",

      "operation": "LIKE",

      "joinTable":"oneClass.generation"

    }

  ]

}


```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/courses/{uuid}/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/courses/{uuid}/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## lecture <a name="lecture"></a>

<details><summary>Show/Hide</summary>

- **createNew** ➕
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/lectures`
  - **Request Body:**

```json
{

    "alias": "Introduction to Java",

    "startTime": "09:00 AM",

    "endTime": "11:00 AM",

    "description": "This lecture covers the basics of Java programming.",

    "lectureDate": "2024-05-27",

    "status": true,

    "courseAlias": "JAVA101",

    "isDraft":false

}


```
- **Response:** No examples available

- **getByAlias** ➕
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/lectures`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/faculties?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **updateByAlias** ➕
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/lectures/Introduction to Java`
  - **Request Body:**

```json
{

    "alias": "Introduction to Java",

    "startTime": "09:00 AM",

    "endTime": "11:00 AM",

    "description": "updated.",

    "lectureDate": "2024-05-27",

    "status": true,

    "courseAlias": "JAVA102"

}


```
- **Response:** No examples available

- **deleteByAlias(not use)** ➕
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/lectures/Introduction to Java`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **filter** ➕
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/lectures/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "OR",

  "specsDto": [

    {

      "column": "alias",

      "value": "c",

      "operation": "LIKE"

    }

  ]

}


```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/faculties/{uuid}/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/faculties/{uuid}/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## score <a name="score"></a>

<details><summary>Show/Hide</summary>

- **createNew** ➕
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/scores`
  - **Request Body:**

```json
{

    "semester": 1,

    "activityScore": 85.5,

    "attendanceScore": 90.0,

    "midtermExamScore": 78.0,

    "finalExamScore": 88.5,

    "miniProjectScore": 92.0,

    "assignmentScore": 80.0,

    "studentUuid": "c11bef99-b3a9-4e99-a15f-ac7cae8726ad",

    "courseAlias": "java1"

}




```
- **Response:** No examples available

- **getByAlias** ➕
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/scores/{uuid}`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/scores?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **updateByAlias** ➕
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/scores/{uuid}`
  - **Request Body:**

```json
{

    "activityScore": 85.5,

    "attendanceScore": 90.0,

    "midtermExamScore": 78.0,

    "finalExamScore": 88.5,

    "miniProjectScore": 92.0,

    "assignmentScore": 80.0

}
```
- **Response:** No examples available

- **deleteByAlias(not use)** ➕
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/scores/{uuid}`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **filter** ➕
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/scores/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "OR",

  "specsDto": [

    {

      "column": "semester",

      "value": "5",

      "operation": "IN"

    }

  ]

}


```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/scores/{uuid}/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/scores/{uuid}/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## attendance <a name="attendance"></a>

<details><summary>Show/Hide</summary>

- **createNew** ➕
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/attendances`
  - **Request Body:**

```json
{

  "status": 1,

  "note": "this student is present",

  "studentUuid": "123e4567-e89b-12d3-a456-426614174000",

  "lectureAlias": "lecture123"

}






```
- **Response:** No examples available

- **getByAlias** ➕
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/attendances/{uuid}`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/attendances?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **updateByAlias** ➕
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/attendances/{uuid}`
  - **Request Body:**

```json
{

  "status": 1,

  "note": "This is a note"

}


```
- **Response:** No examples available

- **deleteByAlias(not use)** ➕
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/attendances/{uuid}`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **filter** ➕
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/scores/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "OR",

  "specsDto": [

    {

      "column": "status",

      "value": "1",

      "operation": "EQUAL"

    }

  ]

}


```
- **Response:** No examples available

</details>

</details>

## User Management <a name="user-management"></a>

<details><summary>Show/Hide</summary>

## user <a name="user"></a>

<details><summary>Show/Hide</summary>

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/users?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getAllDetail** ➕
  - **Description:** Endpoint to getalldetail.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/users/details?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getByUuid** ➕
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/users/8e221fd6-14d5-4e65-8704-d1a79f5abac8`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getDetailByUuid** ➕
  - **Description:** Endpoint to getdetailbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/users/details/412201d1-27a1-481a-8573-28daa9a3f8ea`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getAllAdminUser** ➕
  - **Description:** Endpoint to getalladminuser.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/users/admins?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **updateByUuid** ➕
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/users/17d0c6b8-5671-479b-898d-e633795ce7ab`
  - **Request Body:**

```json
{

   "nameEn": "Jane Smith",

   "nameKh": "ជេន ស្ម៊ីត",

   "username": "janesmith",

   "gender": "Female",

   "dob": "1985-07-23",

   "email": "jane.smith@example.com",

   "password": "newSecurePassword456",

   "profileImage": "http://example.com/jane.jpg",

   "phoneNumber": "0987654321",

  "cityOrProvince": "Siem Reap",

  "khanOrDistrict": "Svay Dangkum",

  "sangkatOrCommune": "Sangkat Svay Dangkum",

  "street": "456 Avenue",

  "birthPlace": {

    "cityOrProvince": "Siem Reap",

    "khanOrDistrict": "Svay Dangkum",

    "sangkatOrCommune": "Sangkat Svay Dangkum",

    "villageOrPhum": "Phum Chong Kaosou",

    "street": "123 Street",

    "houseNumber": "789"

  },

  "authorities": [

    {

      "authorityName": "user:read"

    },

    {

      "authorityName": "user:write"

    }

  ]

}


```
- **Response:** No examples available

- **deleteByUuid** ➕
  - **Description:** Endpoint to deletebyuuid.
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/users/dec226e7-fd71-4d6b-827f-391d0be6bd08`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **createUser** ➕
  - **Description:** Endpoint to createuser.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/users`
  - **Request Body:**

```json
{

  "nameEn": "Chan Chhaya",

  "nameKh": "ចាន់ ឆៃយ៉ា",

  "username": "chhaya",

  "gender": "Male",

  "dob": "1990-01-01",

  "email": "it.chhaya@gmail.com",

  "password": "securepassword123",

  "profileImage": "http://example.com/images/johndoe.jpg",

  "phoneNumber": "1234567890",

  "authorities": [

    {

      "authorityName": "admin:control"

    },

    {

      "authorityName": "user:write"

    }

  ]

}


```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/users/dec226e7-fd71-4d6b-827f-391d0be6bd08/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/users/dec226e7-fd71-4d6b-827f-391d0be6bd08/disable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **block** ➕
  - **Description:** Endpoint to block.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/users/dec226e7-fd71-4d6b-827f-391d0be6bd08/block`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## admin <a name="admin"></a>

<details><summary>Show/Hide</summary>

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/admins?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getAllAdminDetail** ➕
  - **Description:** Endpoint to getalladmindetail.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/admins/detail?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getByUuid** ➕
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/admins/97ecfe35-b4cb-4aec-a037-e5ee93a895c0`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getAdminDetailByUuid** ➕
  - **Description:** Endpoint to getadmindetailbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/admins/detail/bd47c1ee-b6d1-46c7-bcda-0e8acaecdeab`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **create** ➕
  - **Description:** Endpoint to create.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/admins`
  - **Request Body:**

```json
{

    "user": {

        "nameEn": "Chan Chhaya",

        "nameKh": "ចាន់ ឆៃយ៉ា",

        "username": "chhaya",

        "gender": "Male",

        "dob": "1990-01-01",

        "email": "it.chhaya@gmail.com",

        "password": "iSTAD@10",

        "profileImage": "johndoe.jpg",

        "phoneNumber": "123452514",

        "authorities": [

            {

                "authorityName": "user:read"

            },

            {

                "authorityName": "user:write"

            }

        ]

    }

}


```
- **Response:** No examples available

- **updateByUuid** ➕
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/admins/6086e407-02e2-48e2-8005-ce646acbd617`
  - **Request Body:**

```json
{

  "highSchool": "string",

  "highSchoolGraduationDate": "2010-05-23",

  "degree": "string",

  "degreeGraduationDate": "2014-05-23",

  "major": "string",

  "studyAtUniversityOrInstitution": "string",

  "experienceAtWorkingPlace": "string",

  "experienceYear": 5,

  "user": {

     "nameEn": "string",

     "nameKh": "ជេម",

     "username": "string",

     "gender": "Male",

     "email": "john@example.com",

     "password": "password123",

     "profileImage": "johndoe.jpg",

     "phoneNumber": "123",

    "cityOrProvince": "Phnom Penh",

    "khanOrDistrict": "Chamkar Mon",

    "sangkatOrCommune": "Toul Tompoung",

    "street": "Street 123",

    "birthPlace": {

      "cityOrProvince": "string",

      "khanOrDistrict": "string",

      "sangkatOrCommune": "string",

      "villageOrPhum":"string",

      "street":"string",

      "houseNumber":"string"

    },

    "authorities": [

      {

        "authorityName": "user:read"

      },

      {

        "authorityName": "user:write"

      },

      {

        "authorityName": "user:update"

      }

    ]

  }

}


```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/admins/8e20e24b-6000-4c9a-bb68-e6c020bb718d/disable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/admins/8e20e24b-6000-4c9a-bb68-e6c020bb718d/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **block** ➕
  - **Description:** Endpoint to block.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/admins/97ecfe35-b4cb-4aec-a037-e5ee93a895c0/block`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **deleteByUuid** ➕
  - **Description:** Endpoint to deletebyuuid.
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/admins/8e20e24b-6000-4c9a-bb68-e6c020bb718d`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## instructor <a name="instructor"></a>

<details><summary>Show/Hide</summary>

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/instructors?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getAllDetail** ➕
  - **Description:** Endpoint to getalldetail.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/instructors/detail?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getByUuid** ➕
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/instructors/66d30761-cdc6-41f9-98fb-07a52b121331`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getDetailByUuid** ➕
  - **Description:** Endpoint to getdetailbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/instructors/detail/abde37f5-aa3d-4123-8121-2782fde7e706`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **create** ➕
  - **Description:** Endpoint to create.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/instructors`
  - **Request Body:**

```json
{

    "user": {

        "nameEn": "Long Piseth",

        "nameKh": "ឡុង ពិសិដ្ឋ",

        "username": "Seth new",

        "gender": "Male",

        "dob": "1990-01-01",

        "email": "seth.121new@gmail.com",

        "password": "iSTAD@STU",

        "profileImage": "johndoe.jpg",

        "phoneNumber": "123452514",

        "authorities": [

            {

                "authorityName": "user:read"

            },

            {

                "authorityName": "user:write"

            }

        ]

    }

}


```
- **Response:** No examples available

- **updateByUuid** ➕
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/instructors/187e91d9-ddc3-4f5c-9051-c9a02e5241d2`
  - **Request Body:**

```json
{

  "highSchool": "string",

  "highSchoolGraduationDate": "2010-05-23",

  "degree": "string",

  "degreeGraduationDate": "2014-05-23",

  "major": "string",

  "studyAtUniversityOrInstitution": "string",

  "experienceAtWorkingPlace": "string",

  "experienceYear": 3 ,

  "user": {

     "nameEn": "John",

     "nameKh": "ជេម",

     "username": "john",

     "gender": "Male",

     "dob": "1990-01-01",

     "email": "john@example1.com",

     "password": "password123",

     "profileImage": "http://example.com/images/johndoe.jpg",

     "phoneNumber": "12345",

    "cityOrProvince": "Phnom Penh",

    "khanOrDistrict": "Chamkar Mon",

    "sangkatOrCommune": "Toul Tompoung",

    "street": "Street 123",

    "birthPlace": {

      "cityOrProvince": "Phnom Penh",

      "khanOrDistrict": "Chamkar Mon",

      "sangkatOrCommune": "Toul Tompoung",

      "villageOrPhum":"string",

      "street":"Street 123",

      "houseNumber":"string"

    },

    "authorities": [

      {

        "authorityName": "user:read"

      },

      {

        "authorityName": "user:update"

      },

      {

        "authorityName": "user:write"

      },

      {

        "authorityName": "user:delete"

      }

    ]

  }

}


```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/instructors/82f56a18-0ffd-4e88-93c5-a3966e2bc3cf/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/instructors/82f56a18-0ffd-4e88-93c5-a3966e2bc3cf/disable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **block** ➕
  - **Description:** Endpoint to block.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/instructors/82f56a18-0ffd-4e88-93c5-a3966e2bc3cf/block`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **deleteByUuid** ➕
  - **Description:** Endpoint to deletebyuuid.
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/instructors/82f56a18-0ffd-4e88-93c5-a3966e2bc3cf`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## student <a name="student"></a>

<details><summary>Show/Hide</summary>

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/students?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getAllDetail** ➕
  - **Description:** Endpoint to getalldetail.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/students/detail?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getByUuid** ➕
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/students/80da63ce-84f7-42bd-a1f1-426c7ca3bd5c`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **geDetailByUuid** ➕
  - **Description:** Endpoint to gedetailbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/students/detail/f0b5dd7e-4397-4cd1-8785-dd0e683fa9e6`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **create** ➕
  - **Description:** Endpoint to create.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/students`
  - **Request Body:**

```json
{

  "user": {

    "nameEn": "tota",

    "nameKh": "តូតា",

    "username": "totakh",

    "gender": "Male",

    "dob": "2000-01-01",

    "email": "tota@mail.com",

    "password": "securepassword",

    "profileImage": "https://example.com/profile/johndoe.jpg",

    "phoneNumber": "1234567890",

    "authorities": [

    ]

  }

}


```
- **Response:** No examples available

- **updateByUuid** ➕
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/students/0764bf00-ff0b-4ef0-8253-14afc348ac90`
  - **Request Body:**

```json
{

  "user": {

     "nameEn": "John Doe",

     "nameKh": "ចន ដូ",

     "username": "john.doe",

     "gender": "Male",

     "dob": "2000-01-01",

     "email": "john@example.com",

     "password": "securepassword",

     "profileImage": "johndoe.jpg",

     "phoneNumber": "1234567890",

    "cityOrProvince": "Phnom Penh",

    "khanOrDistrict": "Chamkarmon",

    "sangkatOrCommune": "Tonle Bassac",

    "street": "Street 123",

    "birthPlace": {

      "cityOrProvince": "Phnom Penh",

      "khanOrDistrict": "Chamkarmon",

      "sangkatOrCommune": "Tonle Bassac",

      "villageOrPhum": "Village 1",

      "street": "Street 456",

      "houseNumber": "123A"

    }

  }

}


```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/students/af92b40a-88c8-4829-8039-c776ea0a31b9/disable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/students/af92b40a-88c8-4829-8039-c776ea0a31b9/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **block** ➕
  - **Description:** Endpoint to block.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/students/af92b40a-88c8-4829-8039-c776ea0a31b9/block`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **deleteByUuid** ➕
  - **Description:** Endpoint to deletebyuuid.
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/students/af92b40a-88c8-4829-8039-c776ea0a31b9`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## academic <a name="academic"></a>

<details><summary>Show/Hide</summary>

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/academics?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getAllDetail** ➕
  - **Description:** Endpoint to getalldetail.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/academics/detail?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getByUuid** ➕
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/academics/75ce2581-4698-4ae3-b2a7-35c76d6ab435`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getacademicDetailByUuid** ➕
  - **Description:** Endpoint to getacademicdetailbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `UNKNOWN`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **create** ➕
  - **Description:** Endpoint to create.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/academics`
  - **Request Body:**

```json
{

    "user": {

        "nameEn": "Long Piseth",

        "nameKh": "ឡុង ពិសិដ្ឋ",

        "username": "Seth new1",

        "gender": "Male",

        "dob": "1990-01-01",

        "email": "seth.121new1@gmail.com",

        "password": "iSTAD@STU",

        "profileImage": "johndoe.jpg",

        "phoneNumber": "123452514",

        "authorities": [

            {

                "authorityName": "user:read"

            },

            {

                "authorityName": "user:write"

            }

        ]

    }

}


```
- **Response:** No examples available

- **updateByUuid** ➕
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/academics/72177f77-9053-4e30-a3c7-80b704333c47`
  - **Request Body:**

```json
{

  "highSchool": "string",

  "highSchoolGraduationDate": "2010-05-23",

  "degree": "string",

  "degreeGraduationDate": "2014-05-23",

  "major": "string",

  "studyAtUniversityOrInstitution": "string",

  "experienceAtWorkingPlace": "string",

  "experienceYear": 3 ,

  "user": {

     "nameEn": "John",

     "nameKh": "ជេម",

     "username": "john11",

     "gender": "Male",

     "dob": "1990-01-01",

     "email": "john@example11.com",

     "password": "password123",

     "profileImage": "http://example.com/images/johndoe.jpg",

     "phoneNumber": "12345",

    "cityOrProvince": "Phnom Penh",

    "khanOrDistrict": "Chamkar Mon",

    "sangkatOrCommune": "Toul Tompoung",

    "street": "Street 123",

    "birthPlace": {

      "cityOrProvince": "Phnom Penh",

      "khanOrDistrict": "Chamkar Mon",

      "sangkatOrCommune": "Toul Tompoung",

      "villageOrPhum":"string",

      "street":"Street 123",

      "houseNumber":"string"

    },

    "authorities": [

      {

        "authorityName": "user:read"

      },

      {

        "authorityName": "user:update"

      },

      {

        "authorityName": "user:write"

      }

    ]

  }

}


```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/academics/76be6907-9f1d-48a0-9906-3b1b9aee37cd/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/academics/76be6907-9f1d-48a0-9906-3b1b9aee37cd/disable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **block** ➕
  - **Description:** Endpoint to block.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/academics/76be6907-9f1d-48a0-9906-3b1b9aee37cd/block`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **deleteByUuid** ➕
  - **Description:** Endpoint to deletebyuuid.
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/academics/76be6907-9f1d-48a0-9906-3b1b9aee37cd`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## staff <a name="staff"></a>

<details><summary>Show/Hide</summary>

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/staffs?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getAllDetail** ➕
  - **Description:** Endpoint to getalldetail.
  - **HTTP Method:** GET
  - **Endpoint:** `UNKNOWN`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getByUuid** ➕
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/staffs/76be6907-9f1d-48a0-9906-3b1b9aee37cd`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getDetailByUuid** ➕
  - **Description:** Endpoint to getdetailbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `UNKNOWN`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **create** ➕
  - **Description:** Endpoint to create.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/staffs`
  - **Request Body:**

```json
{

  "position": "Updated Staff Position",

  "user": {

    "nameEn": "John",

    "nameKh": "ជេម",

    "username": "john_doe",

    "gender": "Male",

    "dob": "1990-01-01",

    "email": "updated_email@example.com",

    "password": "updatedPassword123",

    "profileImage": "john_doe_updated.jpg",

    "phoneNumber": "987654321",

    "cityOrProvince": "Updated City",

    "khanOrDistrict": "Updated District",

    "sangkatOrCommune": "Updated Commune",

    "street": "Updated Street",

    "birthPlace": {

      "cityOrProvince": "Updated City",

      "khanOrDistrict": "Updated District",

      "sangkatOrCommune": "Updated Commune",

      "villageOrPhum": "Updated Village",

      "street": "Updated Street",

      "houseNumber": "123"

    },

    "authorities": [

      {

        "authorityName": "user:write"

      }

    ]

  }

}


```
- **Response:** No examples available

- **updateByUuid** ➕
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/staffs/05732c9c-e1d2-4382-bfdc-c3ebab215da0`
  - **Request Body:**

```json
{

  "position": "Updated",

  "user": {

     "nameEn": "John",

     "nameKh": "ជេម",

     "username": "john_doe2",

     "gender": "Male",

     "dob": "1990-01-01",

     "email": "updated_email2@example.com",

    "profileImage": "john_doe_updated.jpg",

    "phoneNumber": "987654321",

    "cityOrProvince": "Updated City",

    "khanOrDistrict": "Updated District",

    "sangkatOrCommune": "Updated Commune",

    "street": "Updated Street",

    "birthPlace": {

      "cityOrProvince": "Updated City",

      "khanOrDistrict": "Updated District",

      "sangkatOrCommune": "Updated Commune",

      "villageOrPhum": "Updated Village",

      "street": "Updated Street",

      "houseNumber": "123"

    },

    "authorities": [

      

      {

        "authorityName":"user:read"

      },

      {

        "authorityName":"user:write"

      },

      {

        "authorityName":"user:update"

      }

    ]

  }

}




```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/staffs/76be6907-9f1d-48a0-9906-3b1b9aee37cd/disable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/staffs/76be6907-9f1d-48a0-9906-3b1b9aee37cd/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **block** ➕
  - **Description:** Endpoint to block.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/staffs/99efc622-5a59-4731-8c3c-1a1fbdd4e3ba/block`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **deleteByUuid** ➕
  - **Description:** Endpoint to deletebyuuid.
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/staffs/76be6907-9f1d-48a0-9906-3b1b9aee37cd`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

</details>

## Material Mangement <a name="material-mangement"></a>

<details><summary>Show/Hide</summary>

## medias <a name="medias"></a>

<details><summary>Show/Hide</summary>

- **upload-single** ➕
  - **Description:** Endpoint to upload-single.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/medias/upload-single`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **upload-multiple** ➕
  - **Description:** Endpoint to upload-multiple.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/medias/upload-multiple`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getByName** ➕
  - **Description:** Endpoint to getbyname.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/medias/a75e2608-efc8-45e7-8d87-238c66f564b1.jpg`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **deleteByName(not use)** ➕
  - **Description:** Endpoint to deletebyname(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/medias/e9d6f354-677f-4021-8cdc-7192d999d4ca.png`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **downloadByName** ➕
  - **Description:** Endpoint to downloadbyname.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/medias/b4ca30ea-acfd-4c9b-8a09-79777c79da51.png/download`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## material <a name="material"></a>

<details><summary>Show/Hide</summary>

- **createNew** ➕
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/materials`
  - **Request Body:**

```json
{

  "alias": "web-basic-slide",

  "title": "introduction to java",

  "fileName": "f751e83c-377b-4056-9187-eaffa74cb51d.jpg",

  "contentType": "application/pdf",

  "extension": "pdf",

  "size": 123456,

  "description": "This is an example description of the material.",

  "subjectAlias": "web basic",

  "isDraft": false

}


```
- **Response:** No examples available

- **updateByAlias** ➕
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/materials/java-introduction-slide-1`
  - **Request Body:**

```json
{

    "alias": "spring-framework-basic",

    "subjectName": "Spring Framework Basic",

    "description": "The Spring Framework is an application framework and inversion of control container for the Java platform.",

    "subjectLogo": "spring-framework-basic.png",

    "credit": 1,

    "duration": 80

}
```
- **Response:** No examples available

- **getByAlias** ➕
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/materials/java-introduction-slide-1`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/materials?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **deleteByAlias(not use)** ➕
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/materials/java-introduction-slide-1`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **filter** ➕
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/materials/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "OR",

  "specsDto": [

    {

      "column": "alias",

      "value": "s",

      "operation": "LIKE"

    }

  ]

}


```
- **Response:** No examples available

- **disable** ➕
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/subjects/2eef8ea3-158b-4ad6-890a-58cfd12133d1/disable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **enable** ➕
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/subjects/2eef8ea3-158b-4ad6-890a-58cfd12133d1/enable`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

</details>

## Payment Management <a name="payment-management"></a>

<details><summary>Show/Hide</summary>

## paymeent <a name="paymeent"></a>

<details><summary>Show/Hide</summary>

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/payments?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getByUuid** ➕
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/payments/012ab18c-b6bc-4d7d-8bcc-8d1a0e67c437`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **filter** ➕
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/payments/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "OR",

  "specsDto": [

    {

      "column": "gender",

      "value": "Female",

      "operation": "EQUAL"

    }

  ]

}


```
- **Response:** No examples available

- **createPayment** ➕
  - **Description:** Endpoint to createpayment.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/payments`
  - **Request Body:**

```json
{

  "studentName": "soknem",

  "originalPayment": 1000.0,

  "discount": 10.0,

  "paidAmount": 450.0,

  "paidDate": "2024-06-05",

  "paymentMethod": "Credit Card",

  "remarks": "First installment payment"

}


```
- **Response:** No examples available

- **updateByUuid** ➕
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/payments/1a5cc4c2-8c76-4743-aca7-cd5876dbf411`
  - **Request Body:**

```json
{

  "paidAmount": 1500.00,

  "paymentDate": "2024-05-31",

  "discount": 100.00,

  "dueAmount": 200.00,

  "totalAmount": 1600.00,

  "year": 2024,

  "semester": 2,

  "remark": "update"

}


```
- **Response:** No examples available

- **deleteByUuid** ➕
  - **Description:** Endpoint to deletebyuuid.
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/payments/9d865cc6-3bfb-41a3-a986-aca05c64b455`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## receipt <a name="receipt"></a>

<details><summary>Show/Hide</summary>

- **getAll** ➕
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/receipts?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **create** ➕
  - **Description:** Endpoint to create.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/receipts`
  - **Request Body:**

```json
{

    "remarks": "new",

    "payments": [

        {

            "uuid": "1531c438-1f06-4153-8282-c5702b07968e"

        }

    ]

}


```
- **Response:** No examples available

- **getByUuid** ➕
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/receipts/107a919e-7a25-42ce-b18b-505b4d8d7183`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **updateByUuid** ➕
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/receipts/107a919e-7a25-42ce-b18b-505b4d8d7183`
  - **Request Body:**

```json
{

    "remarks": "new11",

    "payments": [

        {

            "uuid": "1531c438-1f06-4153-8282-c5702b07968e"

        }

    ]

}
```
- **Response:** No examples available

- **deleteByUuid** ➕
  - **Description:** Endpoint to deletebyuuid.
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/receipts/cf10f40b-2d4c-448a-b5f0-06fee5c1aacc`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

</details>

## Public Website <a name="public-website"></a>

<details><summary>Show/Hide</summary>

## graduation <a name="graduation"></a>

<details><summary>Show/Hide</summary>

- **getAllGraduation** ➕
  - **Description:** Endpoint to getallgraduation.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/graduations?pageNumber=1&pageSize=22`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getgraduationByUuid** ➕
  - **Description:** Endpoint to getgraduationbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/graduations/74f1b8ef-831a-41ac-ad04-7aadafd32400`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **updateByUuid** ➕
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/graduations/b6a94a56-8473-4744-bda8-48544bec2752`
  - **Request Body:**

```json
{

  "score": "85-100",

  "gpa": "4.0",

  "grade": "A"

}


```
- **Response:** No examples available

- **deleteByUuid** ➕
  - **Description:** Endpoint to deletebyuuid.
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/graduations/b6a94a56-8473-4744-bda8-48544bec2752`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

## curriculum <a name="curriculum"></a>

<details><summary>Show/Hide</summary>

- **allCurriculum** ➕
  - **Description:** Endpoint to allcurriculum.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/curriculums?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **getCurriculumByYearAndSemester** ➕
  - **Description:** Endpoint to getcurriculumbyyearandsemester.
  - **HTTP Method:** GET
  - **Endpoint:** `{{base_url}}/curriculums/2024/Fall`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

- **createCurriculum** ➕
  - **Description:** Endpoint to createcurriculum.
  - **HTTP Method:** POST
  - **Endpoint:** `{{base_url}}/curriculums`
  - **Request Body:**

```json
{

    "year": "2024",

    "semester": "Fall",

    "subjectsOfSemester": [

        {

            "subjectName": "Mathematics",

            "credit": 3.0,

            "hours": "3 hours/week"

        },

        {

            "subjectName": "Physics",

            "credit": 4.0,

            "hours": "4 hours/week"

        },

        {

            "subjectName": "Chemistry",

            "credit": 3.5,

            "hours": "3.5 hours/week"

        }

    ]

}


```
- **Response:** No examples available

- **addSubjectToCurriculum** ➕
  - **Description:** Endpoint to addsubjecttocurriculum.
  - **HTTP Method:** PATCH
  - **Endpoint:** `{{base_url}}/curriculums/Foundation/one`
  - **Request Body:**

```json
{

    "subjectsOfSemester": [

        {

            "subjectName": "Introduction to Computer Science",

            "credit": 3.0,

            "hours": "45"

        },

        {

            "subjectName": "Calculus I",

            "credit": 4.0,

            "hours": "60"

        },

        {

            "subjectName": "General Physics",

            "credit": 3.0,

            "hours": "45"

        }

    ]

}


```
- **Response:** No examples available

- **updateByYearAndSemester** ➕
  - **Description:** Endpoint to updatebyyearandsemester.
  - **HTTP Method:** PUT
  - **Endpoint:** `{{base_url}}/curriculums/Foundation/Semester one`
  - **Request Body:**

```json
{

    "year": "Second year",

    "semester": "Semester two",

    "subjectsOfSemester": [

        {

            "subjectName": "Mathematics",

            "credit": 3.0,

            "hours": "3 hours/week"

        }

    ]

}


```
- **Response:** No examples available

- **deleteByYearAndSemester** ➕
  - **Description:** Endpoint to deletebyyearandsemester.
  - **HTTP Method:** DELETE
  - **Endpoint:** `{{base_url}}/curriculums/Foundation/one`
  - **Request Body:**

```json
{"noBody": "noBody"}
```
- **Response:** No examples available

</details>

</details>

</details>
