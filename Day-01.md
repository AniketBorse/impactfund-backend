# 📘 ImpactFund - Backend Development Notes

# Day 1 - Audit Infrastructure & User Entity Design

> **Goal:** Build a strong architectural foundation before implementing business features.

---

# 1. BaseEntity

## Purpose

Avoid code duplication by keeping common fields in a reusable parent class.

Current fields:

* UUID id
* LocalDateTime createdAt
* LocalDateTime updatedAt

Every entity in the project will extend `BaseEntity`.

---

# 2. Why UUID Instead of Auto Increment?

We chose:

```java
@GeneratedValue(strategy = GenerationType.UUID)
```

### Reasons

* Prevents ID guessing
* Better API security
* Suitable for distributed systems
* Easier future migration
* Better for microservices

### Future Plan

```
UUID
   ↓
Public ID
   ↓
Expose to Client
```

Initially we'll expose UUID. Later we'll introduce Public IDs.

---

# 3. JPA Auditing

Enabled using:

```java
@EnableJpaAuditing
```

This starts Spring's auditing infrastructure.

Entities use:

```java
@EntityListeners(AuditingEntityListener.class)
```

This tells Hibernate:

> Whenever an entity is inserted or updated, process auditing annotations.

---

## @CreatedDate

Purpose:

* Automatically set creation timestamp.
* Populated only during INSERT.

```java
@CreatedDate
@Column(nullable = false, updatable = false)
private LocalDateTime createdAt;
```

---

## @LastModifiedDate

Purpose:

* Automatically update timestamp whenever entity changes.

```java
@LastModifiedDate
@Column(nullable = false)
private LocalDateTime updatedAt;
```

---

# 4. Why nullable = false?

Database should enforce important business rules.

Benefits:

* Prevents invalid data
* Protects against application bugs
* Ensures data integrity

Fail Fast Principle:

If auditing fails, the database immediately rejects invalid records.

---

# 5. Why updatable = false?

Creation timestamp should never change.

Hibernate excludes this column from UPDATE statements.

---

# 6. Why LocalDateTime?

Instead of:

```java
Date
```

We use:

```java
LocalDateTime
```

Reasons:

* Modern Java Time API
* Immutable
* Cleaner API
* Better readability

Future Topics:

* Instant
* UTC
* Time Zones

---

# 7. Lombok Decision

Avoid:

```java
@Data
```

Reason:

Generates:

* equals()
* hashCode()
* toString()

These can cause problems with JPA entities.

Preferred:

```java
@Getter
@Setter
```

---

# User Module Design

User is **not only an authentication entity**.

It is the core business entity of ImpactFund.

```
User
│
├── Loans
├── Investments
├── Notifications
├── Repayments
├── Wallet (Future)
└── KYC (Future)
```

---

# Roles Design

Instead of separate tables:

```
Investor

Borrower

Admin
```

We use:

```
User
    │
UserRole
    │
Role
```

Future:

```
BorrowerProfile

InvestorProfile
```

---

# User Fields

Current design:

* firstName
* lastName
* email
* phoneNumber
* password
* dateOfBirth
* status
* emailVerified
* phoneVerified

---

# Field Design Checklist

Before adding any field always ask:

1. Why does business need it?
2. Why this Java datatype?
3. Why these annotations?
4. What database constraint does this create?
5. Can this field evolve in the future?

---

# firstName

```java
@Column(nullable = false, length = 100)
private String firstName;
```

Reason:

* Mandatory
* Not unique
* Updatable

Validation belongs in DTOs, not Entities.

---

# lastName

Same design as firstName.

Business Decision:

Since ImpactFund performs KYC and financial verification, last name is mandatory.

---

# Email

Used for:

* Login
* Password Reset
* Notifications
* Email Verification

Database Constraint:

```java
unique = true
```

Application Validation:

```java
existsByEmail()
```

Both are required.

Reason:

Race Conditions.

---

## Email Normalization

Before saving:

```java
email = email.trim().toLowerCase();
```

Business logic belongs in the Service layer.

---

# Phone Number

Phone numbers are identifiers, not numeric values.

❌ Wrong

```java
long phoneNumber;
```

✅ Correct

```java
String phoneNumber;
```

Reasons:

* Preserves leading zeros
* Supports '+'
* Supports international numbers

---

## Storage Format

Use E.164 format.

Example:

```
+919876543210
```

---

## Frontend UX Decision

Frontend:

```
Country Dropdown

↓

Phone Number Input
```

Example:

Country:

🇮🇳 India (+91)

Phone:

9876543210

Backend stores:

```
+919876543210
```

This provides:

* Better User Experience
* Standardized Database Format

---

# Country Discussion

Country will **not** be part of User registration.

Future structure:

```
User
    │
UserProfile
    │
├── Country
├── State
├── City
├── Address
├── Postal Code
└── Preferred Currency
```

Reason:

Registration should remain simple.

Detailed profile information belongs to UserProfile/KYC.

---

# Password

Passwords are **hashed**, not encrypted.

Algorithm:

```
BCrypt
```

Never store:

* Plain Password
* SHA-256 directly

Spring Security uses:

```java
BCryptPasswordEncoder
```

Database stores:

* BCrypt Hash (60 characters)

```java
@Column(length = 60)
private String password;
```

---

## Password Security Rules

Never expose passwords in:

* API Responses
* Logs
* Exceptions
* Emails

---

# DTO Principle

```
Client
    │
RegisterUserRequest (Validation)
    │
Controller
    │
Service (Business Logic)
    │
Entity (Persistence)
    │
Database
```

Entities represent database state.

DTOs represent API contracts.

---

# Engineering Principles Learned

* Business First, Code Later
* Validation belongs in DTOs
* Business Logic belongs in Services
* Database enforces integrity
* Avoid duplicate data
* Design for scalability
* Think globally (internationalization)
* Optimize user experience without compromising architecture

---

# Interview Topics Covered

* UUID vs Auto Increment
* @CreatedDate
* @LastModifiedDate
* @EnableJpaAuditing
* EntityListeners
* nullable = false
* updatable = false
* Lombok (@Data vs @Getter/@Setter)
* DTO vs Entity Validation
* Race Conditions
* UNIQUE Constraints
* String vs long for phone numbers
* BCrypt vs Encryption
* DTO Pattern
* Service Layer Responsibilities

---

# Progress

```
Project Setup                     ✅
GitHub Repository                 ✅
BaseEntity                        ✅
JPA Auditing                      ✅
User Entity Design                60%
Security Foundation               ✅
```

---

# Tomorrow (Day 2)

Topics:

* dateOfBirth
* LocalDate vs LocalDateTime
* Why age should never be stored
* UserStatus enum
* @Enumerated(EnumType.STRING)
* emailVerified
* phoneVerified
* Complete User Entity
* Repository Layer

---

> **Personal Learning**
>
> Today reinforced an important lesson:
>
> *"Good software is not built by writing code quickly. It is built by making good design decisions before writing code."*
