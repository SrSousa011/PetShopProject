# Spring Boot + JWT + H2 Database + Swagger-UI 2

<hr>

Spring Boot, JWT (Json Web Token) and Swagger UI


## How to start ?

```
$ mvn spring-boot:run
```



## Swagger-UI
* After starting the application Click on [Swagger-home](http://localhost:8080/swagger-ui.html)

![Swagger-Home](/screenshots/swagger.png "Swagger UI Home")


## Authenticate and Get Token
![Swagger-Home](/screenshots/get-token.png "Authenticate And Get Token")


## User Data

```
   username | password
   lucas    | dummy
   lcs      | dummy
```

## Request With Token
![Swagger-Home](/screenshots/success-response.png "Swagger UI Home")







## Postman
* After starting the application Open on (http://localhost:8080/authenticate)

## User Data

```
   username | password
   lucas    | dummy
   lcs      | dummy
```

## Authenticate and Get Token


![Postman-Home](/screenshots/postman-get-token.png "Get the Token Authentication ")


## Add Token
![Postman-Home](/screenshots/postman-add-token.png "Add the Token Authentication ")



## Request With Token
![Postman-Home](/screenshots/postman-success-response.png "Postman")



Prossiga com as seguintes relações:

User:

@OneToMany com Client (User possui vários Client)


Client:

@OneToMany com Address (User possui vários Address)
@OneToMany com Contact (User possui vários Contact)
@OneToMany com Pet (Client possui vários Pet)
@OneToMany com Service (Client possui vários Service)
@ManyToOne com User (Client pertence a um User)


Pet:

@ManyToOne com Race (Pet pertence a uma Race)
@ManyToOne com Client (Pet pertence a um Client)

Address:

@ManyToOne com User (Address pertence a um User)
Contact:

@ManyToOne com User (Contact pertence a um User)

Service:

@ManyToOne com Client (Service pertence a um Client)
