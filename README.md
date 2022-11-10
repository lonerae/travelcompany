#TravelCompany Eshop

##About

Ticket purchasing and reporting application for an eshop, written in Java. 

Data (customers, itineraries, tickets) can be
    -read from and saved to CSV files, using the `IoService` methods or
    -imported using the DataImport class of `util` package.

After execution, the application will print
    -all the imported data
    -the total number and total cost of all tickets purchased
    -the number of itineraries offered to customers, per destination and per departure
    -the name(s) of the customer(s) with most tickets purchased and the number of those tickets
    -the name(s) of the customer(s) with the largest cost of purchase and the amount of that cost and
    -the name(s) of the customer(s) who have not purchased any tickets yet
in that order and as JSON.

##Requirements:
    JDK 17
    [GSON library](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.10)

