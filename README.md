# URL Shortener Project

## Overview
This SpringBoot-based URL shortener project allows users to shorten long URLs and retrieve them efficiently. The project incorporates caching and rate limiting features to enhance performance and prevent abuse.

## Features
- **URL Shortening**: Transform long URLs into short, manageable links.
- **Caching**: Utilize caching mechanisms to improve retrieval times for frequently accessed links.
- **Rate Limiting**: Implement rate limiting to control the frequency of requests from individual users, protecting against misuse.

## Technologies Used
- **Spring Boot**: The backbone of the application, providing a robust framework for Java applications.
- **Spring Cache**: For caching response data.
- **Redis**: Optional caching layer using Redis for faster access to frequently used data.
- **Spring Security**: To manage security and implement rate limiting.

## Getting Started
### Prerequisites
- Java 11 or higher
- Maven
- An IDE such as IntelliJ IDEA or Eclipse

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Saiteja-Basam/url-shortener.git
   ```
2. Navigate to the project directory:
   ```bash
   cd url-shortener
   ```
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

### Running the Application
You can run the application using the following command:
```bash
mvn spring-boot:run
```

## API Endpoints
- **POST /shorten**: Create a new short URL.
- **GET /{shortUrl}**: Redirect to the original long URL.

## Conclusion
This project is a practical implementation of a URL shortener service using Spring Boot, featuring caching for performance and rate limiting for security. Feel free to use and contribute to the repository!