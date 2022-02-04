# Spring Boot - Mongo DB polymorphic document demo

This project is a demo of how to implement polymorphic demo in mongo DB.

## Create user in mongo DB

Connect to your mongodb instance in your container:
___docker exec -it yourMongoDBContainerName bash___

Then, connect as admin:

___mongo -u root___ and then enter ___password___ for the password prompted.

Then use your database:

___use polymorphic_demo___

Then, create your user:

___db.createUser({user: "root", pwd: "password", roles : [{role: "readWrite", db: "polymorphic_demo"}]});___