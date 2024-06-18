# Smart Contact Manager

Smart Contact Manager is a web application built using Spring Boot and React.js that allows users to manage their contacts efficiently. Users can register via email verification or log in using OAuth (Google and GitHub). The application supports adding, editing, deleting, and searching contacts, with the ability to upload images to Cloudinary.

## Features

- **User Registration and Authentication**:
  - Email verification for new user registration.
  - OAuth login via Google and GitHub.
  - Direct email login.
  
- **Contact Management**:
  - Add new contacts with details such as name, email, phone number, and profile image.
  - Edit existing contacts.
  - Delete contacts.
  - Upload and manage contact images using Cloudinary.
  
- **Search Functionality**:
  - Search contacts by name, email, or phone number.
  
- **User Interface**:
  - Display contacts in a modal format for easy viewing.
  - Responsive design for optimal viewing on various devices.

## Technology Stack

### Backend
- **Spring Boot**: For building the RESTful API and handling business logic.
- **Spring Security**: For authentication and authorization.
- **OAuth2**: For integrating Google and GitHub login.
- **JPA/Hibernate**: For ORM and database interactions.
- **MySQL**: For the relational database.

### Frontend
- **thymleaf**: For building the user interface.
- **Tailwind CSS**: For styling and responsive design.

### Cloud Services
- **Cloudinary**: For handling image uploads and storage.

## Installation

### Prerequisites
- Node.js
- npm
- Java JDK
- Maven
- MySQL

### Backend Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/smart-contact-manager.git
   cd smart-contact-manager

2. Configure MySQL database:
    ```bash
    create database smart_contact_manager;

3. Update application.yml with your MySQL credentials:
    ```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/smart_contact_manager
    spring.datasource.username=your_mysql_username
    spring.datasource.password=your_mysql_password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    spring.security.oauth2.client.registration.google.clientname=xxxxxxxxxx  #and rest other properties
    #cloudinary cloudanme, apikey and apisecret

4. Run the Spring Boot application:
    ```bash
    mvn spring-boot:run

### Usage
- Open your browser and navigate to http://localhost:3000.
- Register a new account via email verification or log in using Google/GitHub.
- Start managing your contacts by adding, editing, deleting, and searching contacts.
- Upload contact images, which will be stored in Cloudinary.

### Contributing
- Fork the repository.
- Create a new branch (git checkout -b feature-branch).
- Make your changes and commit them (git commit -am 'Add new feature').
- Push to the branch (git push origin feature-branch).
- Create a new Pull Request.

### Contact
- For any inquiries or feedback, please reach out at your-email@example.com.
