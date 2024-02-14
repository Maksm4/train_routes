    OVERVIEW
This project is my attempt to create train infrastructure in java. It randomly creates trainsets with randomly chosen types of wagons and number of them.
Then it routes all of the trians between available stations from the chosen starting station and end station, and displays all the important information 
about the trains path and it's load sorted in descending order in realtion to path traveled to the final destination. After arriving the trian chooses 
new end station, and starts from the last end station. Apart from the AppState.txt file in which this information is displayed additinal information is 
displayed on the console, for example at which station a specific train has arrived, if the train has reached it's final destination or how many passengers 
are currently on the train.


    ADDITIONAL INFORMATION
The src/Carts folder has all the types and categories of carts available. BasicFreighCar and HeavyFreightCar extends the Abstract FreightCar, and all of the 
actual types of carts must be of one of those 2 types. All of the BasicFreightCars must implement the CarInterface which means that they have to have defined
the hasElectricalGrid method that returns the boolean value or can inherit the method with the default value of true from the Abstract BasicFreightCar class.
src/Route folder has the classes that define the logic and the actual comining all elements into working program. All the creation of stations carts and trains is happening
in the Presentation class. The custom Exceptions are also included: RailroadHazard and TrainSetException.
  
     APPSTATE.TXT FILE INFORMATION
The format of the AppState.txt file is as follows:

 TRAIN ID | TOTAL WEIGHT | DISTANCE TRAVELLED TO NEXT STATION | DISTANCE TRAVELLED TO DESTINATION |
| CURRENT SPEED | ROUTE LENGTH | RAILROAD CARS ATTACHED [ INFORMATION ABOUT ALL OF THE CARTS ]

and there are some norms that cannot be exceeded, for examnple if speed exceeds 150 km/h the railroad hazzard will be raised and the train will slow down.
The similar logic is implementred when it comes to the weight, after exceeding some weight the trainset wont accept more carts.
All of this entries are sorted by distance travelled to final destination and the state of this file is updated every 5 seconds if the program is running.



    FEATURES IN THE FUTURE
- create GUI to show the graph of the stations and connections between them as well as the trains that go between them
- update the routing algorithm as well as predefined stations with stated the adjacent stations instead of the randm connections created between them each time the application is run
