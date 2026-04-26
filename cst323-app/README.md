# CST-323 Cloud Test Application

A simple inventory management web app built with Spring Boot 3, Thymeleaf, MySQL, and Bootstrap 5.
Designed to be easily deployed to Microsoft Azure, Heroku, AWS Elastic Beanstalk, and Google Cloud.

## Tech Stack

- **Framework:** Spring Boot 3.2 (Java 17)
- **Template Engine:** Thymeleaf
- **Database:** MySQL 8
- **Persistence:** Spring Data JPA / Hibernate
- **UI:** Bootstrap 5.3
- **Build:** Apache Maven
- **Logging:** SLF4J / Logback

## Pages

| URL | Description |
|-----|-------------|
| `/` | Dashboard - stats and product overview |
| `/products` | Product list (Read) |
| `/products/new` | Add product form (Create) |
| `/products/edit/{id}` | Edit product form (Update) |
| `/products/delete/{id}` | Delete product (Delete) |
| `/categories` | Category list |
| `/categories/new` | Add category form |
| `/categories/edit/{id}` | Edit category form |

## Local Setup

1. Install Java 17, Maven, MySQL 8
2. Create the database by running `src/main/resources/schema.sql`
3. Update `src/main/resources/application.properties` with your DB credentials
4. Run: `mvn spring-boot:run`
5. Open: `http://localhost:8080`

## Git Setup (for first time)

```bash
git init
git add .
git commit -m "Initial commit - CST-323 Cloud Test Application"
git remote add origin https://github.com/YOUR_USERNAME/cst323-cloud-app.git
git push -u origin main
```


```Mermaid
erDiagram
    CATEGORIES {
        bigint   id           PK
        varchar  name
        varchar  description
        datetime created_at
    }
    PRODUCTS {
        bigint   id           PK
        bigint   category_id  FK
        varchar  name
        varchar  description
        int      quantity
        decimal  price
        datetime created_at
    }
    CATEGORIES ||--o{ PRODUCTS : "has"

```