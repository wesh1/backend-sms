## Explanation

There will be 2 endpoints: one for generating a JWT, this will be use for security purposes, it is required when sending a message.

The other endpoint will be the one for sending messages. If messages are longer than 160 characters they will be divided into groups of 160 characters and it will be send individually.

Both servises will be consumed by the front-end.

## To run the project
```
mvn spring-boot:run
```
## Endpoint to get a JWT
/api/auth/login
```
curl --location 'http://localhost:8080/api/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "william@guatemala502.com",
    "password": "MyPass1234"
}'
```
## Endpoint to send SMS
/api/protected/send-sms
```
curl --location 'http://localhost:8080/api/protected/send-sms' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3aWxsaWFtQGd1YXRlbWFsYTUwMi5jb20iLCJpYXQiOjE3NDcwMjM4MjksImV4cCI6MTc0NzAyNzQyOX0.upO8RcNJ2AQqwyrHjdv_d90DTKAMOWeE-aV3cM0Vnbo' \
--header 'Content-Type: application/json' \
--data '{
    "message": "The Mayan culture, a Mesoamerican civilization, flourished for centuries in areas now encompassing Guatemala, Mexico, Belize, Honduras, and El Salvador. Known for their sophisticated writing system, advanced mathematics, and complex calendar, the Maya left behind a legacy of impressive architecture, art, and religious beliefs. They also developed innovative agricultural practices, including slash-and-burn methods and terracing.",
    "countryCode": "+502",
    "phone": "30303030"
}'
```
