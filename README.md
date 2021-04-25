This is a Rest service for querying my covid-19 NOSQL firestore database.
1) There are several components :
 - The bo are the business objects
 - The services take care of the business logic
 - The REST controller call the services to query its data
 - A Firebase config file to set the connection with my firestore database