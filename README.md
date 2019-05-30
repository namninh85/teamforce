# Loyalty backend

A barebones Java app, which can easily be deployed to Heroku.



[![Deploy to Heroku](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

## Running Locally

Make sure you have Java and Maven installed.  Also, install the [Heroku CLI](https://cli.heroku.com/).

```sh
$ git clone https://gitlab.com/loyalty-reward/loyalty-backend.git
$ cd loyalty-backend
$ mvn install
$ heroku local:start
```

Your app should now be running on [localhost:8080](http://localhost:8080/).

If you're going to use a database, ensure you have a local `.application.properties` file that reads something like this:

```
spring.datasource.url = jdbc:postgresql://localhost:5432/loyalty
spring.datasource.username = postgres
spring.datasource.password = 123456

```

## Deploying to Heroku

```sh
$ heroku create
$ git push heroku master
$ heroku open
```

## Documentation

For more information about using Java on Heroku, see these Dev Center articles:

- [Java on Heroku](https://devcenter.heroku.com/categories/java)