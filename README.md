# How to run

This app, is based on java 17.

I have created a docker file, and an image file.

You can pull the docker image with this command:
```
docker pull geolits/bookstore_rest_api

```

The commands to run the image is this:
```
docker run -it -p 8080:8080 geolits/bookstore_rest_api  (with this commnad we can see the terminal of the application)

or



```

# The database

As database I made use of h2 in memory database.

It starts when the application starts. 

All data will be deleted when the application stops.

You can connect at the web console from a browser: 

```
URL: localhost:8080/h2-console

Driver Class: org.h2.Driver

JDBC URL: jdbc:h2:mem:testdb

username: sa

Passwors: no password

```

# What has been implemented:

I have created a bookstore app.

The app has two entities: 1) Book 2) Author

I have made a ManyToMany relationship between them. A book could have many Authors, and an Author could have written many books.

You must at first instert some authors in the system. This can be happen through postman from this RestApi and this json Body:

> **Attension:** name & email must be unique for every Author

```
POST  http://localhost:8080/authors/insert

{
    "name": "Author 1",
    "email": "author1@gmail.com"
}

{
    "name": "Author 2",
    "email": "author2@gmail.com"
}

{
    "name": "Author 3",
    "email": "author3@gmail.com"
}

{
    "name": "Author 4",
    "email": "author4@gmail.com"
}

```

# Insert Books:

> **Attension:**  
>
>**bookType** can be one of eBook, paperback, hardcover.
> 
>**genre** can be one of  FICTION, NON_FICTION, HORROR, ROMANCE, SCIENCE_FICTION, 
BIOGRAPHY,
HISTORY,
MYSTERY,
FANTASY,
THRILLER.
> 
> **volume** is an integer
> 
> **Title & Volume must be unique in conjunction 


```
POST  http://localhost:8080/books/insert

{
  "title": "Book 1",
  "description": "first description",
  "publicationDate": "1951-07-16",
  "bookType": "eBook",
  "genre": "SCIENCE_FICTION",
  "price": 10.99,
  "quantity": 5,
  "volume":1,
  "authors":[
      { "id": 1 },
      { "id": 2 },
      { "id": 3 }
  ]
}

{
  "title": "Book 1",
  "description": "first description",
  "publicationDate": "1951-07-16",
  "bookType": "eBook",
  "genre": "SCIENCE_FICTION",
  "price": 10.99,
  "quantity": 5,
  "volume":2,
  "authors":[
      { "id": 1 },
      { "id": 2 },
      { "id": 3 }
  ]
}

{
  "title": "Book 1",
  "description": "first description",
  "publicationDate": "1951-07-16",
  "bookType": "eBook",
  "genre": "SCIENCE_FICTION",
  "price": 10.99,
  "quantity": 5,
  "volume":3,
  "authors":[
      { "id": 1 },
      { "id": 2 },
      { "id": 3 }
  ]
}

{
  "title": "Book 2",
  "description": "first description",
  "publicationDate": "1951-07-16",
  "bookType": "eBook",
  "genre": "",
  "price": 10.99,
  "quantity": 5,
  "volume":1,
  "authors":[
      { "id": 4 }
  ]
}

{
  "title": "Book 2",
  "description": "first description",
  "publicationDate": "1951-07-16",
  "bookType": "hardcover",
  "genre": "FICTION",
  "price": 10.99,
  "quantity": 5,
  "volume":1,
  "authors":[
      { "id": 4 }
  ]
}

{
  "title": "Book 2",
  "description": "first description",
  "publicationDate": "1951-07-16",
  "bookType": "hardcover",
  "genre": "FICTION",
  "price": 10.99,
  "quantity": 5,
  "volume":2,
  "authors":[
      { "id": 4 }
  ]
}

```

# Book Update:

> **Attension:**
>
>Because I gave at the ManyToMany at the book side the @ManyToMany(cascade = {CascadeType.MERGE})
>and the Authors name and email are unique it needs attention not to give at an authors name & email something that is already persistent in another authors in the database.
>
> the Author details will be updated here too.
```
PUT http://localhost:8080/books/{id}

example http://localhost:8080/books/6 , update the book with id = 6

Json Body:

{
  "title": "Book 2",
  "description": "first description",
  "publicationDate": "1951-07-16",
  "bookType": "paperback",
  "genre": "FICTION",
  "price": 10.99,
  "quantity": 5,
  "volume":1,
  "authors":[
        { 
          "id": 1,
          "name": "Author 1",
          "email": "author1@gmail.com"
        },
        { 
          "id": 2,
          "name": "Author 2,
          "email": "author2@gmail.com"
        },
        {
           "id": 3 ,
           "name": "Author 3",
          "email": "author3@gmail.com"
        }
  ]
}

```

# Book Delete:

```
DELETE http://localhost:8080/books/{id}

example: http://localhost:8080/books/1, delete the book with id 1.

```

# Filter Books

We can filter the books we have in our database with the following Rest API:

```
GET http://localhost:8080/books/filter

Json Body:

{
   "authorNamesList": [
     "Author 1", "Author 4"
   ],
   "minNumberOfVolumes": 3,
   "bookGenresList": [
        "FICTION","ROMANCE"
   ],
   "bookTypesList": [
      "paperback","hardcover"
   ]
}

This example will filter the books and will give us back the books which have:

1) Auhtor must be Author1 or Author4

&&

2) The count of the same book Title, with different Volume No. must be greater than 3

&&

3) Book genre must be FICTION or ROMANCE

&&

4) Book Type must be paperback or  hardcover.

All field could be ommitited or they could be null, so if we would like to take back all the books we can give the next Json Body:

{
   "authorNamesList": [
    
   ],
   "minNumberOfVolumes": null,
   "bookGenresList": [
        
   ],
   "bookTypesList": [
      
   ]
}
```
