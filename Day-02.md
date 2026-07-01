# ImpactFund - Day 3

## Goal
Complete the User Registration feature and establish a solid authentication foundation.

---

# Completed

## User Registration API

Implemented the complete registration flow:

Client
↓
AuthController
↓
UserService
↓
UserRepository
↓
MySQL

Successfully tested using Postman.

---

## Security

- Added Spring Security.
- Created `SecurityConfig`.
- Public endpoints:
    - `/api/v1/auth/**`
- All remaining endpoints require authentication.

Password is encrypted using `BCryptPasswordEncoder`.

Never store passwords in plain text.

---

## Validation

Used Bean Validation annotations in request DTO.

Examples:

- `@NotBlank`
- `@Email`
- `@Pattern`
- `@Past`
- `@Size`

Validation happens before the service layer.

---

## UUID

Using UUID as primary key.

Reason:

- Difficult to guess
- Better security
- Easier future migration to Public IDs
- Distributed-system friendly

Public IDs will be introduced later if required.

---

## BaseEntity

Every entity extends `BaseEntity`.

Common fields:

- id
- createdAt
- updatedAt
- createdBy (future)
- updatedBy (future)

Avoids duplication.

---

## JPA Auditing

Enabled:

```java
@EnableJpaAuditing
```

Learnt:

- `@EnableJpaAuditing` enables the auditing infrastructure.
- `AuditingEntityListener` listens to entity lifecycle events.
- `@CreatedDate`
- `@LastModifiedDate`

Automatically populate timestamps.

---

## Lombok

Learnt why:

```java
@Builder.Default
```

is required.

Without it:

Builder ignores field initializers.

Correct:

```java
@Builder.Default
private boolean emailVerified = false;
```

---

## DTOs

Learnt Java Records.

Example:

```java
public record RegisterUserRequest(...)
```

Advantages:

- Immutable
- Less boilerplate
- Thread-safe
- Perfect for request/response DTOs

---

## Repository

Learnt:

`JpaRepository`

No need for `@Repository`.

Spring Data automatically creates the implementation.

---

## Password Encryption

Used:

```java
BCryptPasswordEncoder
```

Passwords are never stored in plain text.

---

## Testing

Successfully tested registration using Postman.

POST

```
/api/v1/auth/register
```

Received:

```
201 Created
```

Database entry created successfully.

---

# Problems Solved Today

### 1. Jackson Configuration

Discovered Spring Boot 4 uses Jackson 3 (`tools.jackson.*`).

Decision:

Postpone global Jackson customization until later.

---

### 2. Security 401

Initially receiving:

```
401 Unauthorized
```

Root cause investigation included:

- SecurityConfig
- Request mapping
- Dependencies
- Postman Authorization

Eventually found missing:

```java
@EnableJpaAuditing
```

Application worked correctly afterwards.

---

# Architecture Decisions

✔ Package-by-feature architecture

✔ UUID as primary key

✔ Shared BaseEntity

✔ BCrypt password hashing

✔ Separate DTOs

✔ Controller → Service → Repository

✔ Spring Security from Day 1

---

# Interview Concepts Learnt

- Why DTOs?
- Why Records?
- Why UUID?
- Why BCrypt?
- Why BaseEntity?
- Why Builder.Default?
- What is JPA Auditing?
- What is EntityListener?
- Why Layered Architecture?

---

# Git Commit

feat(auth): implement user registration with validation, BCrypt encryption and JPA auditing