# Product Overview

Mini Social is a RESTful social-media backend API built with Spring Boot and MongoDB. It allows users to register accounts, create posts, and add comments to posts.

## Core Entities

- **User** — account with name, email, and password; owns a list of posts
- **Post** — authored content with a title, body, date, and embedded comments
- **Comment** — text reply embedded inside a post document

## Key Capabilities

- User registration and self-service account management (update, delete)
- Authenticated post creation, editing, and deletion (authors only)
- Comment creation and deletion on posts
- Paginated listing of users and posts; post filtering by date
- HTTP Basic authentication; passwords hashed with BCrypt

## Access Rules

- `POST /users` is the only public endpoint (registration)
- All other endpoints require authentication
- Users may only modify or delete their own resources; cross-user operations return 403
