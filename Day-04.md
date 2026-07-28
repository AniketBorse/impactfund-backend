# Day 4 - JWT Authentication (Part 1)

## Topics Covered

### Why JWT?

Traditional Session Authentication:
- Server stores session in memory.
- Client stores Session ID.
- Doesn't scale well for distributed systems.

JWT Authentication:
- Server doesn't store session.
- Client stores JWT.
- JWT is sent with every request.
- Stateless authentication.

---

## JWT Structure

JWT consists of three parts:

Header.Payload.Signature

Example:

xxxxx.yyyyy.zzzzz

### Header

Contains metadata.

{
"alg": "HS256",
"typ": "JWT"
}

### Payload

Contains claims.

Example:

{
"sub": "tony@gmail.com",
"iat": ...,
"exp": ...
}

Never store:
- Password
- Bank Details
- Aadhaar
- PAN

### Signature

Generated using:

Header + Payload + Secret Key

Used to verify token integrity.

---

## JWT Flow

Login

↓

Verify Email & Password

↓

Generate JWT

↓

Frontend Stores JWT

↓

Every Request

Authorization: Bearer <token>

↓

JWT Filter

↓

Validate Signature

↓

Check Expiry

↓

Extract Username

↓

Authenticate User

↓

Controller

---

## Important Concepts

JWT is NOT encrypted.

JWT is:
- Base64 Encoded
- Digitally Signed

Anyone can read Header and Payload.

Nobody can modify Payload because Signature becomes invalid.

---

## JwtProperties

Created using:

@ConfigurationProperties(prefix = "jwt")

Contains:

- secret
- accessTokenExpiration
- refreshTokenExpiration

Registered using:

@EnableConfigurationProperties(JwtProperties.class)

---

## JwtService

Methods implemented:

- getSigningKey()
- generateToken()
- extractAllClaims()
- extractClaim()
- extractUsername()
- extractExpiration()
- isTokenExpired()
- isTokenValid()

---

## Important APIs Learned

Keys.hmacShaKeyFor()

Creates SecretKey from String.

Jwts.builder()

Creates JWT.

compact()

Converts Builder to JWT String.

Jwts.parser()

Reads JWT.

parseSignedClaims()

Validates Signature and parses JWT.

Claims

Represents Payload.

---

## Functional Programming

Generic Method:

extractClaim()

Uses:

Function<Claims, T>

Examples:

Claims::getSubject

Claims::getExpiration

---

## Key Learning

JWT is simply a digitally signed identity card.

Server trusts the Signature,
not the client.

If Payload changes,

↓

Signature changes

↓

Token becomes invalid

↓

401 Unauthorized