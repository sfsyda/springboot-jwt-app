# springboot-jwt-app


###Generating Token

**Request:**
```shell
curl -X POST 'http://localhost:8080/generate-token' \
-H 'Content-Type: application/json' \
-d '{
    "username": "user",
    "password": "password"
}';echo
```
**Response:**
```shell
{"token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjcxMDI3NjM2LCJpYXQiOjE2NzA5OTE2MzZ9.kXc-x5qFOgtc98Z1olbIpFFkawfHgayVipBdZsMZHy0"}
```


