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
