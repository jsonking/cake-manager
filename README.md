Waracle Cake Manager Micro Service
==================================

# Changes
- Converted to spring-boot
- added tests
- added thymeleaf (very basic UI)
- Detailed changes can be see via the commit history. Command: `git log`

# Build
Requires Java 8

`./gradlew clean build`

# Run
`./gradlew bootRun`

Web server will run at http://localhost:8282/

A single cake has been baked into the system for you to enjoy!

# Endpoints

- GET / (web page)
- GET /cake/{id} - Returns the details of a single cake
- GET /cakes - Returns details of all cakes in json format
- POST /cakes - Create a new cake

# Example - create a new cake

`curl -i -X POST -H "Content-Type:application/json" -d '{"name":"Cheesecake","description":"Cheesecake is a sweet dessert consisting of one or more layers","imageURL":"https://www.seriouseats.com/recipes/images/2017/06/20170526-no-bake-cheesecake-vicky-wasik-18-1500x1125.jpg"}' http://localhost:8282/cakes
`

# TODO
- Separate the UI (e.g. using ReactJs)
- Improved UI error handling and validation
- Authentication via OAuth2
- Containerisation
