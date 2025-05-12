## Explanation

There are two endpoints

## Endpoint to get a JWT

```
/api/auth/login
```

This endpoint will generate a JWT token if the credentials of the user are valid. The generated JWT will have an expiration time of one houre after its creation and it will be expired also if the user logout.

Below and example of how to use it

```
curl --location 'http://localhost:8080/api/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "william@guatemala502.com",
    "password": "MyPass1234"
}'
```

## Endpoint to send a SMS

```
/api/protected/send-sms
```

This endpoint will send a SMS. If messages are longer than 160 characters they will be divided into groups of 160 characters and it will be send individually.

The JWT is needed for this endpoint to be consumed.

Below how to use it

```
curl --location 'http://localhost:8080/api/protected/send-sms' \
--header 'Authorization: Bearer <JWT>' \
--header 'Content-Type: application/json' \
--data '{
    "message": "<some cool message here>",
    "countryCode": "+502",
    "phone": "11111111"
}'
```

## To run the project
```
mvn spring-boot:run
```

## H2 database

If it is wanted to take a look at the database, it can be done using this link

http://localhost:8080/h2-console/login

Leave the credentials as they appeared and press connect.


## Design & Analytical Questions

1. **Architecture Decisions**

What were your main considerations when structuring your code?

I organized the code in a way it can be understand easily by other developer, using names that are according to what actually the class or component does.

2. **API Design**

How did you decide what the API should look like? Did you consider versioning or error formats?

Basically I started with the front-end needs:

* What data will I receive from the UI?
* What data does the UI need?
* How the request/response will look like?
* How to secure the flow?

3. **State Management (If implemented)**

If you implemented frontend, how is state handled on the frontend? Why did you choose that approach?

I handled it with cookies, useful cause I used an authentication flow to secure the communication between the back-end and the front-end.

4. **Security (If implemented)**  

If you implemented login/auth, how did you handle token storage and validation?

The JWT is created in the back-end. The front-end will store this token in a HttpOnly cookie, so JavaScript can't access it. This token will be validated in the back-end every time a request is being done and also the front-end will check the JWT is present to render the pages.

5. **Scalability & Maintainability**

If this project grew to support teams and thousands of users, what changes would you make?

Add more validations in the front-end and back-end to ensure the flow is seamlessly.

Add support to more countries to send messages.

6. **Time Constraints**

What features did you intentionally skip due to time constraints?

a. Adding more country codes, currently only Guatemala and El Salvador.

b. Messages sent by user.

c. Hash the passwords when saving them.

d. Add more tests.

