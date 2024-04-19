# Cinema Project 🎥
cinema project is a bakeend monolith made for movie reservations both as the administrator uploading the movies with basic characteristics and as a user entering the app to register their reservation.

## Administrator module 🕴️

In the endpoint Movie controller administrator module you can load the movie with the items:

- Name of the movie.
- Category.
- Release date.
- Description of the movie.

##Client module 👩‍   👨‍👩‍👧‍👦

The client module is designed to make a registration which allows generating an authentication through a token, after entering the user will be able to make a reservation through BookingController.

##### Registration (Authcontroller 🔒 )
In this security service we can find a filter that sends a token generated with public and private keys, this token is sent to the header in order to authenticate the other endpoints

In addition to this we find the open corks in order to be able to connect it with a front.

##### Booking ( Bookingcontroller 🈸)

In this service we can find a registration form in order to know how many people are needed for the reservation with a defined price.

Form  :
- Name of the movie.
- show time.
- the number of seats.
- the room where it should be located.

It is a student project in order to learn the best practices of a monolith for web services with http requests.