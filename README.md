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

## Authentication <a name=""></a>


- **Please Login first:** 
  - **username:** admin
  - **password:** Admin@123
- **Access token expire:** 1 day
- **Refresh token expire:** 3 day
- **Auth type:** bearer token

---
## Authorities <a name=""></a>
```json
[
  "faculty:write",
  "faculty:update",
  "faculty:delete",
  "generation:read",
  "generation:write",
  "generation:update",
  "generation:delete",
  "shift:read",
  "shift:write",
  "shift:update",
  "shift:delete",
  "academic:read",
  "academic:write",
  "academic:update",
  "academic:delete",
  "assessment:read",
  "assessment:write",
  "assessment:update",
  "assessment:delete",
  "class:read",
  "class:write",
  "class:update",
  "class:delete",
  "course:read",
  "course:write",
  "course:update",
  "course:delete",
  "session:read",
  "session:write",
  "session:update",
  "session:delete",
  "material:read",
  "material:write",
  "material:update",
  "material:delete",
  "admission:read",
  "admission:write",
  "admission:update",
  "admission:delete",
  "payment:read",
  "payment:write",
  "payment:update",
  "payment:delete",
  "user:read",
  "user:write",
  "user:update",
  "user:delete",
  "admin:control"
]


```

---
## DownLoad postman document <a name=""></a>
- **Link:** https://drive.google.com/file/d/14ogd3xXHBqXQWbZesgamNOcTHLV1Kw6v/view?usp=sharing
---
## ISTAD LMS API <a name="istad-lms-api"></a>

<details><summary>Show/Hide</summary>

## Security Management <a name="security-management"></a>

<details><summary>Show/Hide</summary>

## auth <a name="auth"></a>

<details><summary>Show/Hide</summary>

- **login**
  - **Description:** Endpoint to login.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/auth/login`
  - **Request Body:**

```json
{

  "emailOrUsername": "johndoe11",

  "password": "PZ19G?%khX"
  

}


```
- **Response:** No examples available

- **register(not use)**
  - **Description:** Endpoint to register(not use).
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/auth/register`
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

- **refresh**
  - **Description:** Endpoint to refresh.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/auth/refresh`
  - **Request Body:**

```json
{

  "refreshToken": "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJJVFNBRC1MTVMiLCJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJleHAiOjE3MTgyNDYwMTUsImlhdCI6MTcxNzk4NjgxNX0.naoW81giheIX23Va9BnOg23rdfaO00TRkfDECVS9rL-tYOR4wvyh09j9BevWb2uQ7AgsHqsE-1Mft5V_Rh6_-ZhM_h9CxEO1WHEaFGoBlo1nk2SbZgaxk37y41FU3LFVASWueCSryyh8g0G5XuEDEePgQ88yzB3C-cHBMRbprTdS0wRgPnL0DA5SwoDeVt5NmrHIuYs6hKTZiq2ZqswDtiAwAkpEPn19et154zI6h6TdWGqlJ4-zxccQGBTQBNMnBhE4KkEK9add9ON0JKFEV5ZZYHRYJ6JbzC_y7e0BshbbJLVJhO-FS4tmQTghkI-4QubE7YbyM12bMk0o8FMOwA"

}
```
- **Response:** No examples available

</details>

## password <a name="password"></a>

<details><summary>Show/Hide</summary>

- **getPassword**
  - **Description:** Endpoint to getpassword.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/auth/passwords`
  - **Request Body:**

```json
{

    "usernameOrEmail" : "johndoe11"

}
```
- **Response:** No examples available

- **changePassword**
  - **Description:** Endpoint to changepassword.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/auth/passwords`
  - **Request Body:**

```json
{

    "newPassword": "NewPassword@123",

    "confirmPassword": "NewPassword@123"

}


```
- **Response:** No examples available

- **resetPassword**
  - **Description:** Endpoint to resetpassword.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/auth/passwords/reset`
  - **Request Body:**

```json
{

    "usernameOrEmail" : "johndoe11"

}
```
- **Response:** No examples available

</details>

</details>

## Faculty Management <a name="faculty-management"></a>

<details><summary>Show/Hide</summary>

## faculty <a name="faculty"></a>

<details><summary>Show/Hide</summary>

- **createNew**
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/faculties`
  - **Request Body:**

```json
{

    "alias": "sciences-and-technologies-2",

    "name": "Sciences and Technologies",

    "description": "Sciences and technologies are interconnected fields that aim to understand and apply the natural and physical laws of the universe to develop innovative solutions and improve human life. The following information provides an overview of the various fields of sciences and technologies.",

     "logo": "e1d4a8de-43e2-4e46-ab9d-e2cfd8eb2ea3.png",

    "address": "123 University Ave",

    "isDraft": "tr"

}
```
- **Response:** No examples available

- **getByAlias**
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/faculties/sciences-and-technologies`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/faculties?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **updateByAlias**
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/faculties/sciences-and-technologies`
  - **Request Body:**

```json
{

    "name": "Sciences and Technologies",

    "description": "This department now also includes courses on artificial intelligence and machine learning.",

    "address": "456 College St",

    "logo": "d882cc3e-81bf-4c17-b9cc-7bdf31c7c2d0.png"

}
```
- **Response:** No examples available

- **deleteByAlias(not use)**
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/faculties/business-management`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **filter**
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/faculties/filter?pageNumber=0&pageSize=25`
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

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/faculties/sciences-and-technologies/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **public**
  - **Description:** Endpoint to public.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/faculties/sciences-and-technologies/public`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **draft**
  - **Description:** Endpoint to draft.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/faculties/sciences-and-technologies/draft`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/faculties/sciences-and-technologies/disable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## degree <a name="degree"></a>

<details><summary>Show/Hide</summary>

- **createNew**
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/degrees`
  - **Request Body:**

```json
{

    "alias": "associate",

    "level": "Associate Degree",

    "description": "This is associate degree for student who finished bachelor",

    "isDraft": false

}
```
- **Response:** No examples available

- **getByAlias**
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/degrees/associate`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/degrees?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **updateByAlias**
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/degrees/associate`
  - **Request Body:**

```json
{

    "level": "Association",

    "description": "2 years of degree"

}
```
- **Response:** No examples available

- **deleteByAlias(not use)**
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/degrees/master`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **filter**
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/degrees/filter?pageNumber=0&pageSize=25`
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

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/degrees/associate/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/degrees/associate/disable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **public**
  - **Description:** Endpoint to public.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/degrees/associate/public`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **draft**
  - **Description:** Endpoint to draft.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/degrees/associate/draft`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## studyProgram <a name="studyprogram"></a>

<details><summary>Show/Hide</summary>

- **createNew**
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/study-programs`
  - **Request Body:**

```json
{

    "alias": "software-engineering-bachelor",

    "studyProgramName": "Bachelor of Software Engineering",
  

    "description": "The Bachelor of Management Information Systems (MIS) is a degree program that focuses on the application of technology to solve business problems.",

    "facultyAlias": "sciences-and-technologies",

    "degreeAlias": "associate",

    "isDraft": false

}
```
- **Response:** No examples available

- **updateByAlias**
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/study-programs/software-engineering-bachelor`
  - **Request Body:**

```json
{


    "studyProgramName": "Bachelor of Software Engineering",

    "logo": "9e520333-0a1d-4bcd-90aa-8e9582fb8c6f.jpeg",

    "description": "Bachelor of Software Engineering (SE) is a degree program that focuses on the application of technology to solve business problems."

}
```
- **Response:** No examples available

- **getByAlias**
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/study-programs/software-engineering-bachelor`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/study-programs?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **deleteByAlias(not use)**
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/study-programs/{uuid}`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **filter**
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/study-programs/filter?pageNumber=0&pageSize=25`
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

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/study-programs/management-information-systems-master/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/study-programs/management-information-systems-master/disable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **public**
  - **Description:** Endpoint to public.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/study-programs/management-information-systems-master/public`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **draft**
  - **Description:** Endpoint to draft.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/study-programs/management-information-systems-master/draft`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## yearOfStudy <a name="yearofstudy"></a>

<details><summary>Show/Hide</summary>

## subjects <a name="subjects"></a>

<details><summary>Show/Hide</summary>

- **addSubjectsByAlias**
  - **Description:** Endpoint to addsubjectsbyalias.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/year-of-studies/793b9f6c-f58a-4d10-9e95-e54ab4a5ab16/subjects`
  - **Request Body:**

```json
{

    "aliasOfSubjects": [

        "java-programming","html","github"

    ]

}
```
- **Response:** No examples available

- **deletedSubjectFromYearOfStudy**
  - **Description:** Endpoint to deletedsubjectfromyearofstudy.
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/year-of-studies/130b64e4-f2e4-4474-a7e5-d19d6a51ad6e/subjects/java-programming`
  - **Request Body:**

```json

```
- **Response:** No examples available

</details>

- **createNew**
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/year-of-studies`
  - **Request Body:**

```json
{

    "year": "",

    "semester": "",

    "studyProgramAlias": "software-engineering-bachelor",

    "isDraft": false

}
```
- **Response:** No examples available

- **updateByUuid**
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/year-of-studies/793b9f6c-f58a-4d10-9e95-e54ab4a5ab16`
  - **Request Body:**

```json
{

  "year": null,

  "semester":null 

}
```
- **Response:** No examples available

- **getByUuid**
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/year-of-studies/130b64e4-f2e4-4474-a7e5-d19d6a51ad6e`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/year-of-studies?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **deleteByUuid(not use)**
  - **Description:** Endpoint to deletebyuuid(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/year-of-studies/f9286264-e902-431b-a837-22b95e8afaf6`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **filter**
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/year-of-studies/filter?pageNumber=0&pageSize=25`
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

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/year-of-studies/94a6a16c-7c76-47da-abba-352716070915/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/year-of-studies/94a6a16c-7c76-47da-abba-352716070915/disable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **public**
  - **Description:** Endpoint to public.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/year-of-studies/94a6a16c-7c76-47da-abba-352716070915/public`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **draft**
  - **Description:** Endpoint to draft.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/year-of-studies/94a6a16c-7c76-47da-abba-352716070915/draft`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## subject <a name="subject"></a>

<details><summary>Show/Hide</summary>

- **createNew**
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/subjects`
  - **Request Body:**

```json
{

  "alias": "github",

  "title": "git control version",

  "logo":"1623cc23-948d-4f2b-8c57-65e0b7bfcc4b.png",

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

- **updateByAlias**
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/subjects/java-programming`
  - **Request Body:**

```json
{

    "alias": "reactJs",

    "subjectName": " ReactJs framework",

     "description": "The Spring Framework is an application framework and inversion of control container for the Java platform.",

    "logo": "1f49fc23-3da5-4fe0-a50f-7140ff29cffa.png"
  

}
```
- **Response:** No examples available

- **getByAlias**
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/subjects/reactJs`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/subjects?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **deleteByAlias(not use)**
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/subbjects/java-basic`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **filter**
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/subjects/filter?pageNumber=0&pageSize=25`
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

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/subjects/reactJs/disable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/subjects/java-basic/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **public**
  - **Description:** Endpoint to public.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/subjects/java-basic/public`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **private**
  - **Description:** Endpoint to private.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/subjects/java-basic/private`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## shifts <a name="shifts"></a>

<details><summary>Show/Hide</summary>

- **createNew**
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/shifts`
  - **Request Body:**

```json
{

  "alias": "weekday-morning-1",

  "name": "Weekday morning",

  "startTime": "08:00:00",

  "endTime": "90:00:00",

  "weekday": true,

  "isDraft":false,

  "description": "This is the morning shift for weekday"

}


```
- **Response:** No examples available

- **updateByAlias**
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/shifts/weekday-evening`
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

- **getByAlias**
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/shifts/weekday-morning`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/shifts?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **deleteByAlias(not use)**
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/shifts/weekday-evening`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **filter**
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/shifts/filter?pageNumber=0&pageSize=25`
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

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/shifts/weekday-morning/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/shifts/weekday-morning/disable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **public**
  - **Description:** Endpoint to public.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/shifts/weekday-morning/public`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **draft**
  - **Description:** Endpoint to draft.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/shifts/weekday-morning/draft`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

</details>

## Admission Management <a name="admission-management"></a>

<details><summary>Show/Hide</summary>

## admission <a name="admission"></a>

<details><summary>Show/Hide</summary>

- **createNew**
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/admissions`
  - **Request Body:**

```json
{

  "status": 1,

  "remark": "this admission for first generation",

  "openDate": "2028-05-30",

   "endDate": "2029-05-30",

  "telegramLink": "https://t.me/admission_group"

}


```
- **Response:** No examples available

- **getByUuid**
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/admissions/f284241c-70cf-4469-bef3-3cb5e47cac04`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/admissions?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **updateByUuid**
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/admissions/f284241c-70cf-4469-bef3-3cb5e47cac04`
  - **Request Body:**

```json
{

    "remark": "update",

    "endDate": "2028-02-02",

    "telegramLink": "no link yet"

}
```
- **Response:** No examples available

- **deleteByUuid(not use)**
  - **Description:** Endpoint to deletebyuuid(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/admissions/beb2fcee-0e2f-4377-88dd-04221a56dcb4`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **filter**
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/admissions/filter?pageNumber=0&pageSize=25`
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

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/admissions/f284241c-70cf-4469-bef3-3cb5e47cac04/disable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/admissions/f284241c-70cf-4469-bef3-3cb5e47cac04/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **updateStatusByUuid**
  - **Description:** Endpoint to updatestatusbyuuid.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/admissions/f284241c-70cf-4469-bef3-3cb5e47cac04/status`
  - **Request Body:**

```json
{

    "status": 1

}
```
- **Response:** No examples available

</details>

## studentAmission <a name="studentamission"></a>

<details><summary>Show/Hide</summary>

- **createNew**
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/student-admissions`
  - **Request Body:**

```json
{

  "nameEn": "the flash",

  "nameKh": "ដឺ ហ្លាស្ស",

  "email": "theflash@gmail.com",

  "highSchool": "snoul takeo highSchool",

  "phoneNumber": "021456894",

  "dob": "2003-01-01",

  "pob": "kratie province",

  "bacIiGrade": "A",

  "gender": "Male",

  "avatar": "bb0a9242-89fe-44e7-884e-aa392909af11.jpg",

  "address": "123 Main Street",

  "guardianContact": "+1987654321",

  "guardianRelationShip": "Parent",

  "knownIstad": "By social media",

  "identity": "",

  "biography": "ដោយសារខ្ញុំចង់ចឹង teacher , ពេល disabled ចឹងខ្ញុំអោយវា draft  ដែរ, ព្រោះយើងអត់អាច public  degree ដែល disabled",

  "shiftAlias": "weekday-morning",

  "studyProgramAlias": "software-engineering-bachelor",

  "degreeAlias": "associate"

}




```
- **Response:** No examples available

- **getByUuid**
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/student-admissions/bc1f9d0d-0f2a-43ba-9602-bef6674e8b99`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/student-admissions?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **updateByUuid**
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/student-admissions/4aaab4dc-cd67-48f0-96e2-76a1416dc5db`
  - **Request Body:**

```json
{ "nameEn": "hidra",

  "email": "hidra@example.com",

  "dob": "1990-01-01",

  "gender": "Males",

  "avatar": ""

}
```
- **Response:** No examples available

- **deleteByUuid(not use)**
  - **Description:** Endpoint to deletebyuuid(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/student-admissions/beb2fcee-0e2f-4377-88dd-04221a56dcb4`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **filter**
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/student-admissions/filter?pageNumber=0&pageSize=25`
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

      "column": "alias",

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

- **createNew**
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/generations`
  - **Request Body:**

```json
{

    "alias": "generation-2",

    "name": "Generation 2",

    "description": "This is the second generation.",

    "startYear": 2026,

    "endYear": 2027,

    "isDraft": false

}
```
- **Response:** No examples available

- **getByAlias**
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/generations/generation-1`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/generations?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **updateByAlias**
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/generations/generation-1`
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

- **deleteByAlias(not use)**
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/generations/gen1`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **filter**
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/generations/filter?pageNumber=0&pageSize=25`
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

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/generations/generation-1/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/generations/generation-1/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **public**
  - **Description:** Endpoint to public.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/generations/generation-1/public`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **draft**
  - **Description:** Endpoint to draft.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/generations/generation-1/draft`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## class <a name="class"></a>

<details><summary>Show/Hide</summary>

## students <a name="students"></a>

<details><summary>Show/Hide</summary>

- **addStudentByUuid**
  - **Description:** Endpoint to addstudentbyuuid.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/classes/1546f137-30e5-4f8f-8328-e0d26bd3647f/students`
  - **Request Body:**

```json
{

    "studentAdmissionUuid":[

        "b11d7b76-bdb1-4fd5-ae3f-1e8bdd86de3f"

    ]

}
```
- **Response:** No examples available

- **deleteStudentByUuid**
  - **Description:** Endpoint to deletestudentbyuuid.
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/classes/0919d156-6bdb-49ba-8e35-b665bec783b9/students/847ead5b-bc03-484c-9c37-b8987248ef3f`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

- **createNew**
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/classes`
  - **Request Body:**

```json
{

    "classCode": "G1-Y1-S1",

    "description": "Generation 1 (Year 1 Semester 1)",

    "year": 1,

    "instructorUuid": null,

    "studyProgramAlias": "software-engineering-bachelor",

    "shiftAlias": "weekday-morning",

    "generationAlias": "generation-2",

    "studentUuid": [],

    "isDraft": false

}
```
- **Response:** No examples available

- **getByUuid**
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/classes/g1-y1-s1`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/classes?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **updateByUuid**
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/classes/g1-y1-s1`
  - **Request Body:**

```json
{

    "alias":"beginner",

    "level": "Level 2",

    "description": "This is the first level for beginners."

}
```
- **Response:** No examples available

- **deleteByUuid(not use)**
  - **Description:** Endpoint to deletebyuuid(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/classes/e2`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **filter**
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/classes/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "OR",

  "specsDto": [

    {

      "column": "alias",

      "value": "g",

      "operation": "LIKE",

      "joinTable": "shift"

    }

  ]

}


```
- **Response:** No examples available

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/classes/alias/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/classes/alias/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **public**
  - **Description:** Endpoint to public.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/classes/alias/public`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **draft**
  - **Description:** Endpoint to draft.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/classes/alias/draft`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## course <a name="course"></a>

<details><summary>Show/Hide</summary>

## instructor <a name="instructor"></a>

<details><summary>Show/Hide</summary>

- **addInstructorByUuid**
  - **Description:** Endpoint to addinstructorbyuuid.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/courses/{alias}/instructors/{uuid}`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **deleteInstructorByUuid**
  - **Description:** Endpoint to deleteinstructorbyuuid.
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/courses/{alias}/instructors/{uuid}`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

- **createNew(not use)**
  - **Description:** Endpoint to createnew(not use).
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/courses`
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

- **getByUuid**
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/courses/java-advance-data`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/courses?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **updateByUuid**
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/courses/java2`
  - **Request Body:**

```json
{

    "alias":"beginner",

    "level": "Level 2",

    "description": "This is the first level for beginners."

}
```
- **Response:** No examples available

- **deleteByUuid(not use)**
  - **Description:** Endpoint to deletebyuuid(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/course/{uuid}`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **filter**
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/courses/filter?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{

  "globalOperator": "AND",

  "specsDto": [

    {

      "column": "isDeleted",

      "value": "false",

      "operation": "EQUAL"

    },

    {

      "column": "className",

      "value": "g",

      "operation": "LIKE",

      "joinTable":"oneClass"

    }
    

  ]

}


```
- **Response:** No examples available

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/courses/{uuid}/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/courses/{uuid}/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## lecture <a name="lecture"></a>

<details><summary>Show/Hide</summary>

- **createNew**
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/lectures`
  - **Request Body:**

```json
{

    "alias": "Introduction to Java",

    "startTime": "10:00 AM",

    "endTime": "12:00 PM",

    "description": "This lecture covers the basics of Java programming.",

    "lectureDate": "2024-05-27",

    "status": true,

    "courseAlias": "java-programming-g1-y1-s1",

    "isDraft": false

}
```
- **Response:** No examples available

- **getByAlias**
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/lectures/Introduction to Java`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/lectures?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **updateByAlias**
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/lectures/Introduction to Java`
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

- **deleteByAlias(not use)**
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/lectures/Introduction to Java`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **filter**
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/lectures/filter?pageNumber=0&pageSize=25`
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

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/faculties/{uuid}/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/faculties/{uuid}/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## score <a name="score"></a>

<details><summary>Show/Hide</summary>

- **createNew**
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/scores`
  - **Request Body:**

```json
{

    "activityScore": 85.5,

    "attendanceScore": 90.0,

    "midtermExamScore": 78.0,

    "finalExamScore": 88.5,

    "miniProjectScore": 92.0,

    "assignmentScore": 80.0,

    "studentUuid": "fdbee7ea-9811-490b-8cf0-79f2815482c3",

    "courseAlias": "java-programming-g1-y1-s1"

}




```
- **Response:** No examples available

- **getByAlias**
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/scores/32d4402e-d2f6-44ec-a340-678515dfa602`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/scores?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **updateByAlias**
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/scores/{uuid}`
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

- **deleteByAlias(not use)**
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/scores/{uuid}`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **filter**
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/scores/filter?pageNumber=0&pageSize=25`
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

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/scores/32d4402e-d2f6-44ec-a340-678515dfa602/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/scores/32d4402e-d2f6-44ec-a340-678515dfa602/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## attendance <a name="attendance"></a>

<details><summary>Show/Hide</summary>

- **createNew**
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/attendances`
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

- **getByAlias**
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/attendances/{uuid}`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/attendances?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **updateByAlias**
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/attendances/{uuid}`
  - **Request Body:**

```json
{

  "status": 1,

  "note": "This is a note"

}


```
- **Response:** No examples available

- **deleteByAlias(not use)**
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/attendances/{uuid}`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **filter**
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/scores/filter?pageNumber=0&pageSize=25`
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

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/users?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getAllDetail**
  - **Description:** Endpoint to getalldetail.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/users/details?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getByUuid**
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/users/8e221fd6-14d5-4e65-8704-d1a79f5abac8`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getDetailByUuid**
  - **Description:** Endpoint to getdetailbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/users/details/412201d1-27a1-481a-8573-28daa9a3f8ea`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getAllAdminUser**
  - **Description:** Endpoint to getalladminuser.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/users/admins?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **updateByUuid**
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/users/15e91d10-1f3c-4394-afb5-d2aed2922210`
  - **Request Body:**

```json
{

  "nameEn": "John",

  "nameKh": "ចន់",

  "username": "john123",

  "gender": "Male",

  "dob": "1990-05-15",

  "email": "john@example.com",

  "profileImage": "https://example.com/profile.jpg",

  "phoneNumber": "+1234567890",

  "currentAddress": "House 123, Street 310, Phum 4, Boeung Keng Kang 1, Chamkarmon, Phnom Penh, Cambodia",

  "birthPlace": {

    "cityOrProvince": "Phnom Penh",

    "khanOrDistrict": "Chamkarmon",

    "sangkatOrCommune": "Boeung Keng Kang 1",

    "villageOrPhum": "Phum 4",

    "street": "Street 310",

    "houseNumber": "House 123"

  }

}


```
- **Response:** No examples available

- **createUser**
  - **Description:** Endpoint to createuser.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/users`
  - **Request Body:**

```json
{

    "nameEn": "John Doe",

    "nameKh": "ជន ដូ",

    "username": "johndoe",

    "gender": "Male",

    "dob": "1990-05-15",

    "email": "johndoe@gmail.com",

    "profileImage": "",

    "phoneNumber": "1234567890",

    "authorityNames": [

        "user:update", 

        "admin:control"

    ]

}


```
- **Response:** No examples available

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/users/dec226e7-fd71-4d6b-827f-391d0be6bd08/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/users/dec226e7-fd71-4d6b-827f-391d0be6bd08/disable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **block**
  - **Description:** Endpoint to block.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/users/dec226e7-fd71-4d6b-827f-391d0be6bd08/block`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **deleteByUuid(not use)**
  - **Description:** Endpoint to deletebyuuid(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/users/dec226e7-fd71-4d6b-827f-391d0be6bd08`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## admin <a name="admin"></a>

<details><summary>Show/Hide</summary>

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/admins?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getAllAdminDetail**
  - **Description:** Endpoint to getalladmindetail.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/admins/detail?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getByUuid**
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/admins/c03ab5e8-ab75-4d1e-bf12-37feac4151e6`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getAdminDetailByUuid**
  - **Description:** Endpoint to getadmindetailbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/admins/detail/c03ab5e8-ab75-4d1e-bf12-37feac4151e6`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **create**
  - **Description:** Endpoint to create.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/admins`
  - **Request Body:**

```json
{

  "nameEn": "Jonh Son",

  "nameKh": "ជន សាន់",

  "username": "jonhson",

  "gender": "Male",

  "dob": "1985-06-15",

  "email": "jonhson@gmail.com",

  "password": "Password123!",

  "profileImage": "https://example.com/images/profile/johndoe.jpg",

  "phoneNumber": "1234567890",

  "authorityNames": [

    "faculty:write",

    "faculty:update",

    "faculty:delete",

    "generation:read",

    "generation:write",

    "generation:update",

    "generation:delete",

    "shift:read",

    "shift:write",

    "shift:update",

    "shift:delete",

    "academic:read",

    "academic:write",

    "academic:update",

    "academic:delete",

    "assessment:read",

    "assessment:write",

    "assessment:update",

    "assessment:delete",

    "class:read",

    "class:write",

    "class:update",

    "class:delete",

    "course:read",

    "course:write",

    "course:update",

    "course:delete",

    "session:read",

    "session:write",

    "session:update",

    "session:delete",

    "material:read",

    "material:write",

    "material:update",

    "material:delete",

    "admission:read",

    "admission:write",

    "admission:update",

    "admission:delete",

    "payment:read",

    "payment:write",

    "payment:update",

    "payment:delete",

    "user:read",

    "user:write",

    "user:update",

    "user:delete",

    "admin:control"

  ]

}


```
- **Response:** No examples available

- **updateByUuid**
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/admins/409442bb-068b-4fc3-8a7f-78fb5eaeb125`
  - **Request Body:**

```json
{

  "highSchool": "International School of Phnom Penh",

  "highSchoolGraduationDate": "2010-06-30",

  "degree": "Bachelor of Science",

  "degreeGraduationDate": "2014-06-30",

  "major": "Computer Science",

  "studyAtUniversityOrInstitution": "University of Phnom Penh",

  "experienceAtWorkingPlace": "Software Engineer",

  "experienceYear": 8,

  "nameEn": "John Doe",

  "nameKh": "សេចក្តីពិភពលោក",

  "username": "johndoe123",

  "gender": "Male",

  "dob": "1990-05-15",

  "email": "john.doe@example.com",

  "profileImage": "https://example.com/profile.jpg",

  "phoneNumber": "+1234567890",

  "currentAddress": "House 123, Street 310, Phum 4, Boeung Keng Kang 1, Chamkarmon, Phnom Penh, Cambodia",

  "birthPlace": {

    "cityOrProvince": "Phnom Penh",

    "khanOrDistrict": "Chamkarmon",

    "sangkatOrCommune": "Boeung Keng Kang 1",

    "villageOrPhum": "Phum 4",

    "street": "Street 310",

    "houseNumber": "House 123"

  }

}


```
- **Response:** No examples available

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/admins/19598b74-6be4-4833-b420-6c60a0c8038b/disable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/admins/19598b74-6be4-4833-b420-6c60a0c8038b/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **block**
  - **Description:** Endpoint to block.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/admins/19598b74-6be4-4833-b420-6c60a0c8038b/block`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## academic <a name="academic"></a>

<details><summary>Show/Hide</summary>

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/academics?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getAllDetail**
  - **Description:** Endpoint to getalldetail.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/academics/detail?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getByUuid**
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/academics/75ce2581-4698-4ae3-b2a7-35c76d6ab435`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getacademicDetailByUuid**
  - **Description:** Endpoint to getacademicdetailbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/academics/detail/e4c96f54-4141-4d1c-b73a-ba8411f37b35`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **create**
  - **Description:** Endpoint to create.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/academics`
  - **Request Body:**

```json
{

  "nameEn": "Panha",

  "nameKh": "បញ្ញា",

  "username": "panha",

  "gender": "Male",

  "dob": "1985-06-15",

  "email": "panha12@gmail.com",

  "password": "Password123!",

  "profileImage": "https://example.com/images/profile/johndoe.jpg",

  "phoneNumber": "1234567890",

  "authorityNames": [

    "faculty:write",

    "faculty:update"
    

  ]

}


```
- **Response:** No examples available

- **updateByUuid**
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/academics/e4c96f54-4141-4d1c-b73a-ba8411f37b35`
  - **Request Body:**

```json
{

  "highSchool": "ABC High School",

  "highSchoolGraduationDate": "2003-05-25",

  "degree": "Bachelor of Science",

  "degreeGraduationDate": "2007-06-15",

  "major": "Computer Science",

  "studyAtUniversityOrInstitution": "XYZ University",

  "experienceAtWorkingPlace": "Tech Solutions Inc.",

  "experienceYear": 10,

  "cityOrProvince": "Phnom Penh",

  "khanOrDistrict": "Chamkar Mon",

  "sangkatOrCommune": "Toul Tom Poung",

  "street": "Street 123",

  "birthPlace": {

    "cityOrProvince": "Phnom Penh",

    "khanOrDistrict": "Daun Penh",

    "sangkatOrCommune": "Wat Phnom",

    "villageOrPhum": "Phum 1",

    "street": "Street 58",

    "houseNumber": "No. 123"

  }

}


```
- **Response:** No examples available

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/academics/76be6907-9f1d-48a0-9906-3b1b9aee37cd/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/academics/76be6907-9f1d-48a0-9906-3b1b9aee37cd/disable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **block**
  - **Description:** Endpoint to block.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/academics/76be6907-9f1d-48a0-9906-3b1b9aee37cd/block`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## instructor <a name="instructor"></a>

<details><summary>Show/Hide</summary>

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/instructors?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getAllDetail**
  - **Description:** Endpoint to getalldetail.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/instructors/detail?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getByUuid**
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/instructors/7a4e8775-6dab-4c3b-b517-8b25b66adfb1`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getDetailByUuid**
  - **Description:** Endpoint to getdetailbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/instructors/detail/7a4e8775-6dab-4c3b-b517-8b25b66adfb1`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **create**
  - **Description:** Endpoint to create.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/instructors`
  - **Request Body:**

```json
{

    "nameEn": "SokNem",

    "nameKh": "សុខនែម",

    "username": "soknem",

    "gender": "Male",

    "dob": "1990-05-15",

    "email": "soknem@gmail.com",

    "password": "Password@123",

    "profileImage": "http://example.com/profile/johndoe.jpg",

    "phoneNumber": "1234567890",

    "authorityNames": [

        "user:update", 

        "admin:control"

    ]

}


```
- **Response:** No examples available

- **updateByUuid**
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/instructors/0a1bbafb-4e0f-4394-8770-5fb7fad76466`
  - **Request Body:**

```json
{

  "highSchool": "ABC High School",

  "highSchoolGraduationDate": "2003-05-25",

  "degree": "Bachelor of Science",

  "degreeGraduationDate": "2007-06-15",

  "major": "Computer Science",

  "studyAtUniversityOrInstitution": "XYZ University",

  "experienceAtWorkingPlace": "Tech Solutions Inc.",

  "experienceYear": 10,

  "cityOrProvince": "Phnom Penh",

  "khanOrDistrict": "Chamkar Mon",

  "sangkatOrCommune": "Toul Tom Poung",

  "street": "Street 123",

  "birthPlace": {

    "cityOrProvince": "Phnom Penh",

    "khanOrDistrict": "Daun Penh",

    "sangkatOrCommune": "Wat Phnom",

    "villageOrPhum": "Phum 1",

    "street": "Street 58",

    "houseNumber": "No. 123"

  }

}


```
- **Response:** No examples available

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/instructors/7a4e8775-6dab-4c3b-b517-8b25b66adfb1/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/instructors/7a4e8775-6dab-4c3b-b517-8b25b66adfb1/disable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **block**
  - **Description:** Endpoint to block.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/instructors/7a4e8775-6dab-4c3b-b517-8b25b66adfb1/block`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## staff <a name="staff"></a>

<details><summary>Show/Hide</summary>

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/staffs?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getAllDetail**
  - **Description:** Endpoint to getalldetail.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/staffs/detail?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getByUuid**
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/staffs/76be6907-9f1d-48a0-9906-3b1b9aee37cd`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getDetailByUuid**
  - **Description:** Endpoint to getdetailbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/staffs/detail/245286d0-a365-47a9-b42f-936daaaa9b7f`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **create**
  - **Description:** Endpoint to create.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/staffs`
  - **Request Body:**

```json
{

  "position": "Senior Lecturer",

  "nameEn": "John Doe",

  "nameKh": "ជូន ដូ",

  "username": "johndoe123",

  "gender": "Male",

  "dob": "1985-06-15",

  "email": "johndoe@example.com",

  "password": "Password123!",

  "profileImage": "https://example.com/images/profile/johndoe.jpg",

  "phoneNumber": "1234567890",

  "authorityNames": [

    "faculty:write",

    "faculty:update",

    "faculty:delete"
    

  ]

}


```
- **Response:** No examples available

- **updateByUuid**
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/staffs/245286d0-a365-47a9-b42f-936daaaa9b7f`
  - **Request Body:**

```json
{

  "position": "Administrative Assistant",

  "nameEn": "Jane Doe",

  "nameKh": "ជេន ដូ",

  "username": "janedoe123",

  "gender": "Female",

  "dob": "1990-04-20",

  "email": "janedoe@example.com",

  "profileImage": "https://example.com/images/profile/janedoe.jpg",

  "phoneNumber": "0987654321",

  "cityOrProvince": "Phnom Penh",

  "khanOrDistrict": "Chamkar Mon",

  "sangkatOrCommune": "Toul Tom Poung",

  "street": "Street 456",

  "birthPlace": {

    "cityOrProvince": "Phnom Penh",

    "khanOrDistrict": "Daun Penh",

    "sangkatOrCommune": "Wat Phnom",

    "villageOrPhum": "Phum 2",

    "street": "Street 58",

    "houseNumber": "No. 456"

  }

}


```
- **Response:** No examples available

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/staffs/76be6907-9f1d-48a0-9906-3b1b9aee37cd/disable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/staffs/76be6907-9f1d-48a0-9906-3b1b9aee37cd/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **block**
  - **Description:** Endpoint to block.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/staffs/99efc622-5a59-4731-8c3c-1a1fbdd4e3ba/block`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## student <a name="student"></a>

<details><summary>Show/Hide</summary>

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/students?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getAllDetail**
  - **Description:** Endpoint to getalldetail.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/students/detail?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getByUuid**
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/students/80da63ce-84f7-42bd-a1f1-426c7ca3bd5c`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **geDetailByUuid**
  - **Description:** Endpoint to gedetailbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/students/detail/f0b5dd7e-4397-4cd1-8785-dd0e683fa9e6`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **create**
  - **Description:** Endpoint to create.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/students`
  - **Request Body:**

```json
{

  "position": "Senior Lecturer",

  "nameEn": "John Doe",

  "nameKh": "ជូន ដូ",

  "username": "johndoe123",

  "gender": "Male",

  "dob": "1985-06-15",

  "email": "johndoe@example.com",

  "password": "Password123!",

  "profileImage": "https://example.com/images/profile/johndoe.jpg",

  "phoneNumber": "1234567890"

}


```
- **Response:** No examples available

- **updateByUuid**
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/students/0049ff77-f220-4cc5-a4df-e664280313fc`
  - **Request Body:**

```json
{



}


```
- **Response:** No examples available

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/students/64fceae5-b097-4eb2-a3ed-b58a90b81600/disable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/students/d52e56e6-73c2-4c01-8962-741ad491095d/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **block**
  - **Description:** Endpoint to block.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/students/af92b40a-88c8-4829-8039-c776ea0a31b9/block`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **New Request**
  - **Description:** Endpoint to new request.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/students/profile`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

</details>

## Material Mangement <a name="material-mangement"></a>

<details><summary>Show/Hide</summary>

## medias <a name="medias"></a>

<details><summary>Show/Hide</summary>

- **upload-single**
  - **Description:** Endpoint to upload-single.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/medias/upload-single`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **upload-multiple**
  - **Description:** Endpoint to upload-multiple.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/medias/upload-multiple`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getByName**
  - **Description:** Endpoint to getbyname.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/medias/38c5c6c4-f7f1-41e2-b4c4-a4b5222215d7.jpeg`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **deleteByName(not use)**
  - **Description:** Endpoint to deletebyname(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/medias/e9d6f354-677f-4021-8cdc-7192d999d4ca.png`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **downloadByName**
  - **Description:** Endpoint to downloadbyname.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/medias/download/38c5c6c4-f7f1-41e2-b4c4-a4b5222215d7.jpeg`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **view**
  - **Description:** Endpoint to view.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/medias/view/d7f6e815-0d3b-46b4-b70b-e52c8c26a801.png`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## material <a name="material"></a>

<details><summary>Show/Hide</summary>

- **createNew**
  - **Description:** Endpoint to createnew.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/materials`
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

  "subjectAlias": "java-programming",

  "isDraft": false

}


```
- **Response:** No examples available

- **updateByAlias**
  - **Description:** Endpoint to updatebyalias.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/materials/web-basic-slide`
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

- **getByAlias**
  - **Description:** Endpoint to getbyalias.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/materials/web-basic-slide`
  - **Request Body:**

```json

```
- **Response:** No examples available

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/materials?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **deleteByAlias(not use)**
  - **Description:** Endpoint to deletebyalias(not use).
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/materials/java-introduction-slide-1`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **filter**
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/materials/filter?pageNumber=0&pageSize=25`
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

- **disable**
  - **Description:** Endpoint to disable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/materials/web-basic-slide/disable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **enable**
  - **Description:** Endpoint to enable.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/subjects/2eef8ea3-158b-4ad6-890a-58cfd12133d1/enable`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

</details>

## Payment Management <a name="payment-management"></a>

<details><summary>Show/Hide</summary>

## paymeent <a name="paymeent"></a>

<details><summary>Show/Hide</summary>

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/payments?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getByUuid**
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/payments/3bb68852-bd8c-4909-9ffc-1118a79b0034`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **filter**
  - **Description:** Endpoint to filter.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/payments/filter?pageNumber=0&pageSize=25`
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

- **createPayment**
  - **Description:** Endpoint to createpayment.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/payments`
  - **Request Body:**

```json
{

  "studentName": "pov_soknem",

  "originalPayment": 1000.0,

  "discount": 10.0,

  "paidAmount": 750.0,

  "paidDate": "2024-06-05",

  "paymentMethod": "Credit Card",

  "remarks": "First installment payment"

}


```
- **Response:** No examples available

- **updateByUuid**
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/payments/1a5cc4c2-8c76-4743-aca7-cd5876dbf411`
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

- **deleteByUuid**
  - **Description:** Endpoint to deletebyuuid.
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/payments/9d865cc6-3bfb-41a3-a986-aca05c64b455`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## receipt <a name="receipt"></a>

<details><summary>Show/Hide</summary>

- **getAll**
  - **Description:** Endpoint to getall.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/receipts?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **create**
  - **Description:** Endpoint to create.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/receipts`
  - **Request Body:**

```json
{

    "remarks": "new payment",

    "payments": [

        {

            "uuid": "3bb68852-bd8c-4909-9ffc-1118a79b0034"

        }

    ]

}


```
- **Response:** No examples available

- **getByUuid**
  - **Description:** Endpoint to getbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/receipts/da6cc91e-487d-4a54-8d72-431d3e51f229`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **updateByUuid**
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/receipts/107a919e-7a25-42ce-b18b-505b4d8d7183`
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

- **deleteByUuid**
  - **Description:** Endpoint to deletebyuuid.
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/receipts/cf10f40b-2d4c-448a-b5f0-06fee5c1aacc`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

</details>

## Public Website <a name="public-website"></a>

<details><summary>Show/Hide</summary>

## graduation <a name="graduation"></a>

<details><summary>Show/Hide</summary>

- **getAllGraduation**
  - **Description:** Endpoint to getallgraduation.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/graduations?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getgraduationByUuid**
  - **Description:** Endpoint to getgraduationbyuuid.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/graduations/74f1b8ef-831a-41ac-ad04-7aadafd32400`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **updateByUuid**
  - **Description:** Endpoint to updatebyuuid.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/graduations/b6a94a56-8473-4744-bda8-48544bec2752`
  - **Request Body:**

```json
{

  "score": "85-100",

  "gpa": "4.0",

  "grade": "A"

}


```
- **Response:** No examples available

- **deleteByUuid**
  - **Description:** Endpoint to deletebyuuid.
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/graduations/b6a94a56-8473-4744-bda8-48544bec2752`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

## curriculum <a name="curriculum"></a>

<details><summary>Show/Hide</summary>

- **allCurriculum**
  - **Description:** Endpoint to allcurriculum.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/curriculums?pageNumber=0&pageSize=25`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **getCurriculumByYearAndSemester**
  - **Description:** Endpoint to getcurriculumbyyearandsemester.
  - **HTTP Method:** GET
  - **Endpoint:** `/api/v1/curriculums/2024/semester one`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

- **createCurriculum**
  - **Description:** Endpoint to createcurriculum.
  - **HTTP Method:** POST
  - **Endpoint:** `/api/v1/curriculums`
  - **Request Body:**

```json
{

    "year": "2024",

    "semester": "semester one",

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

- **addSubjectToCurriculum**
  - **Description:** Endpoint to addsubjecttocurriculum.
  - **HTTP Method:** PATCH
  - **Endpoint:** `/api/v1/curriculums/Foundation/one`
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

- **updateByYearAndSemester**
  - **Description:** Endpoint to updatebyyearandsemester.
  - **HTTP Method:** PUT
  - **Endpoint:** `/api/v1/curriculums/Foundation/Semester one`
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

- **deleteByYearAndSemester**
  - **Description:** Endpoint to deletebyyearandsemester.
  - **HTTP Method:** DELETE
  - **Endpoint:** `/api/v1/curriculums/Foundation/one`
  - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** No examples available

</details>

</details>

</details>
