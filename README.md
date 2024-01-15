
# Domain Driven Design Deit/Calorie tracker App

This is an example backend for a diet and calorie tracking application built(mostly) using domain driven design.


## Architecture followed

Following are the different DDD components that are used in this project, note that some of them are optional

![DDD components](../resources/ddd.png)

## Technologies Used
* Spring boot 3
* Apache Kafka for domain events
* MYSQL and MongoDB for data persistent
* Webflux for reactive flow
* Hibernate
* Tomcat and Netty Servers

## Structure

This project has two independant Spring boot applications, one a modular application the other a single module service

### Self Health Core Backend Modules

1. Food Plan Domain
This domain deals with the food catalog which the user can explore and search. 

Functionality:
* Add custom food with calorie intake
* Delete custom food entries
* Fetch custom user entered food plans based on userId
* Search for food plans from a list of curated food plans   and also user entered food plans

2. Meal Plan Domain
This domain deals with the actual meal the user consumed, it can be a combination od multiple food plans and also larger portions

Functionality:
* Save meal entry with total calorie intake for user
* Fire domain event for each meal entry(to be used by other domains)

3. User Domain
Persistes User metadata into the system to be used throught the system

Functionality:
* Add new users into the system

4. Content Domain
This domain just displays a list of posts/content to the user with some diet and meal advice

Functionality:
* Display food advice posts


### Insights Service

1. User Insights Domain
Probably the most rich domain model, this domain deals with aggregating and serving user metrics. 

Functionality:
* Aggregate and fetch metrics such as:
    * Average Calories Consumed per Day
    * Average Number of meals per day
    * Raw number of calories day wise for last week
    * Percentage of important nutrients(Fats, Carbs, Proteins)
* Listen to meal domain events and include metrics such as calorie intake into the current weeks or new weeks dashboard(implemented using strategy pattern)


# Run the application

### Navigate to the project directory
cd self-health-core-backend

### Build the project using Maven
mvn clean install

### Run the Spring Boot application
java -jar target/self-health-core-backend.jar
