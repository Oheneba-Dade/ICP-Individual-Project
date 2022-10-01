# ICP-Individual-Project
This project contains three classes, namely Airport, Node, and Route.
The Airport Class represents a given airport, containing an airport ID, airport name, city, country, IATA code and ICAO code.
The Node Class is a specific class created to aid in the breadth-first search.
The Route Class represents a given route with information such as airline code, airline ID, destination airport code and stops.

To run this application, run the main method located in the TestRoute Class.
The bfs method takes in a start city and country and a destination city and country, and returns the flights to get there.

To test this, an input file containing a start city and country and a destination city and country is provided (sandane-bangkok.txt)
After running the main method, an output file is created, and the flights from Sandane to Bangkok is written to it.