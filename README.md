# Online Exam System Backend

This is a Spring Boot backend application for an online exam system with PostgreSQL database.

## Features

- Teacher management (CRUD operations)
- Student management (CRUD operations)
- Course management (CRUD operations)
- Question management with multiple choice options
- Exam management
- RESTful API endpoints
- PostgreSQL database integration
- Input validation
- Global exception handling
- CORS configuration

## Technology Stack

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- PostgreSQL
- Maven
- Bean Validation

## Database Schema

The application uses the provided PostgreSQL schema with the following main entities:
- Teachers
- Students
- Courses
- Questions
- Question Options
- Exams
- Student-Exam assignments
- Student Answers

## API Endpoints

### Teachers
- `GET /api/teachers` - Get all teachers
- `GET /api/teachers/active` - Get active teachers
- `GET /api/teachers/{id}` - Get teacher by ID
- `GET /api/teachers/email/{email}` - Get teacher by email
- `POST /api/teachers` - Create new teacher
- `PUT /api/teachers/{id}` - Update teacher
- `DELETE /api/teachers/{id}` - Delete teacher
- `GET /api/teachers/search?name={name}` - Search teachers by name

### Students
- `GET /api/students` - Get all students
- `GET /api/students/active` - Get active students
- `GET /api/students/{email}` - Get student by email
- `POST /api/students` - Create new student
- `PUT /api/students/{email}` - Update student
- `DELETE /api/students/{email}` - Delete student
- `GET /api/students/search?name={name}` - Search students by name

### Courses
- `GET /api/courses` - Get all courses
- `GET /api/courses/active` - Get active courses
- `GET /api/courses/{id}` - Get course by ID
- `GET /api/courses/teacher/{teacherId}` - Get courses by teacher
- `POST /api/courses` - Create new course
- `PUT /api/courses/{id}` - Update course
- `DELETE /api/courses/{id}` - Delete course
- `GET /api/courses/search?name={name}` - Search courses by name

### Questions
- `GET /api/questions` - Get all questions
- `GET /api/questions/active` - Get active questions
- `GET /api/questions/{id}` - Get question by ID
- `GET /api/questions/course/{courseId}` - Get questions by course
- `POST /api/questions` - Create new question with options
- `PUT /api/questions/{id}` - Update question
- `DELETE /api/questions/{id}` - Delete question
- `GET /api/questions/search?text={text}` - Search questions by text

### Exams
- `GET /api/exams` - Get all exams
- `GET /api/exams/active` - Get active exams
- `GET /api/exams/{id}` - Get exam by ID
- `GET /api/exams/date/{date}` - Get exams by date
- `GET /api/exams/date-range?startDate={start}&endDate={end}` - Get exams by date range
- `POST /api/exams` - Create new exam
- `PUT /api/exams/{id}` - Update exam
- `DELETE /api/exams/{id}` - Delete exam
- `GET /api/exams/search?name={name}` - Search exams by name

## Running the Application

1. Make sure PostgreSQL is running and the database is created with the provided schema
2. Update the database connection details in `application.yml` if needed
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. The API will be available at `http://localhost:8080`

## Configuration

The application is configured to connect to your PostgreSQL database using the provided connection string. The configuration is in `src/main/resources/application.yml`.

## Project Structure

```
src/main/java/com/examensystem/
├── OnlineExamSystemApplication.java
├── config/
│   ├── DatabaseConfig.java
│   └── WebConfig.java
├── controller/
│   ├── TeacherController.java
│   ├── StudentController.java
│   ├── CourseController.java
│   ├── QuestionController.java
│   └── ExamController.java
├── dto/
│   ├── TeacherDTO.java
│   ├── StudentDTO.java
│   ├── CourseDTO.java
│   ├── QuestionDTO.java
│   ├── QuestionOptionDTO.java
│   └── ExamDTO.java
├── exception/
│   └── GlobalExceptionHandler.java
├── model/
│   ├── Teacher.java
│   ├── Student.java
│   ├── Course.java
│   ├── Question.java
│   ├── QuestionOption.java
│   ├── Exam.java
│   ├── QuestionExam.java
│   ├── StudentExam.java
│   ├── StudentAnswer.java
│   └── [ID classes for composite keys]
├── repository/
│   ├── TeacherRepository.java
│   ├── StudentRepository.java
│   ├── CourseRepository.java
│   ├── QuestionRepository.java
│   ├── QuestionOptionRepository.java
│   ├── ExamRepository.java
│   └── StudentExamRepository.java
└── service/
    ├── TeacherService.java
    ├── StudentService.java
    ├── CourseService.java
    ├── QuestionService.java
    └── ExamService.java
```