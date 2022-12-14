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


###Using Token
```shell
curl -X GET 'http://localhost:8080/hello' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjcxMDMwNzM0LCJpYXQiOjE2NzA5OTQ3MzR9.z-eTgKdunUeaU8OR5v1877q_ayDOH2ycMql_sEAwZHE'; echo  
Hello from home!
zeezee@Zee-MacBook-Pro-3 springboot-jwt-app % curl -X GET 'http://localhost:8080/hello' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjcxMDMwNzM0LCJpYXQiOjE2NzA5OTQ3MzR9.z-eTgKdunUeaU8OR5v1877q_ayDOH2ycMql_sEAwZHE' -v; echo
Note: Unnecessary use of -X or --request, GET is already inferred.
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /hello HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.79.1
> Accept: */*
> Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjcxMDMwNzM0LCJpYXQiOjE2NzA5OTQ3MzR9.z-eTgKdunUeaU8OR5v1877q_ayDOH2ycMql_sEAwZHE
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
< X-Content-Type-Options: nosniff
< X-XSS-Protection: 0
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Frame-Options: DENY
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 16
< Date: Wed, 14 Dec 2022 05:14:04 GMT
< 
* Connection #0 to host localhost left intact
Hello from home!
```
