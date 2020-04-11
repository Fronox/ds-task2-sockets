# Description of classes
* Client.java - client class which implements
    - data initialization
    - data transfer to a server
    - result of sort receiving
    
* Server.java - server class which implements 

    - data receiving from the client
    - data sorting using InsertionSort class and Java-reflection
    - sending back result of sort

* InsertionSort.java - sort class which implements
    
    - data storing
    - data sorting

* Config.java - config class which

    - stores program constants (i.e. hostname and port for socket communication)
    - implements current time obtaining in human-readable format

# Run instructions 
* Go to `out/artifacts` directory

    `cd out/artifacts`

* Run server

    `java -jar Server.jar`
    
* Run client

    `java -jar Client.jar`
    
* Follow output instructions

**NB: Server must start before a client!**