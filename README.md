Waracle Cake Manager Micro Service
==================================

![Build Status](https://travis-ci.org/jsonking/cake-manager.svg?branch=master)
https://travis-ci.org/jsonking/cake-manager

# Changes
- Converted to spring-boot
- Added tests
- Added basic React based UI
- Detailed changes can be see via the commit history. 
   - https://github.com/jsonking/cake-manager/commits/master
   - or locally run command: `git log`

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

# User Interface

The code for the user interface is located in the `cake-manager-ui` folder. 
This is developed using the [React Framework](https://reactjs.org/).

Start the user interface for development (requires nodejs). Your browser will open at http://localhost:3000
```
cd cake-manager-ui
npm install
npm start
```

Start the server with cors security enabled:
```
./gradlew bootRun -Dspring.profiles.active=localtesting
```

Build the react application and bundle within the spring-boot application:
```
cd cake-manager-ui
npm run-script build
```
Note: The above command builds then copies the React application to the `src/main/resources/static` directory. The built application files are also checked in there so that the application can be started easily.

# Containerisation

## Docker
Example commands to build and run a [docker](https://www.docker.com/) image locally
```
./gradlew jibDockerBuild --image=waracle/cake-manager
docker run -p 8989:8282 -t waracle/cake-manager
```
The application will then be accessible at http://localhost:8989/

# todo's
- Improved UI error handling and validation
- Authentication via OAuth2
