# servicemanual-java

This is test application to create REST api backend for factory devices maintenance task using Java spring boot.

Project uses MVC architecture where model and controller is located in backend and frontend acts as a view.
This application uses Java collections and streams to work with data and Mongo database for storing data.
Application is configured to use local Mongo database. See mongodb.com for instructions how to start your local database.

Before starting the application you need Mongo database running on your local computer.
Project uses database named backendtest. When started application searches for saved data and if not found it generates new data from CSV file and also with random data.



Class packages:
model 
    FactoryDevice holds Factory device information year, name and type.
    MaintenanceTask holds Factory device's maintenance task information. Which factory device it is related to, time when maintenance task is registered, severity and status of the task.

controller
    FactoryDeviceController for REST api to work with FactoryDevice data
    MaintenanceTaskController for REST api to worki with MaintenanceTasks.
    FactoryDeviceNotFoundException, FactoryDeviceNotFoundAdvice and MaintenanceTaskNotFoundException classes are for exception handling.
    MongoController is only for testing aggregations with mongodb Atlas cloud.

repository
    FactoryDeviceRepository is used to store and read data from Mongo database.
    MaintenanceTaskRepository is used to store and read data from Mongo database.

util
    CSVReader is helping class to read CSV file
    MaintenanceTaskGenerator is helping class to fill MaintenanceTask repository with some randomly generated data.


Application starting point is in ServiceManualApplication class.
It uses local Mongo database to read backendtest database and FactoryDevice collection. Mongo database connection uri is located at application.properties file. If factorydevices collection is empty, then CSVReader class is used to read data from seeddata.csv file to fill factorydevices collection. Mongo database generates id data for each saved document. After saving collection it is then read back with that id data.
Secondly maintenancetask collection is filled with random data with the help of MaintenanceTaskGenerator class. This generator takes as a parameter, how many tasks to generate and a list of factorydevice id to connect tasks with.


REST API
GET
/maintenancetasks
Get all maintenance tasks as JSON.
Tasks are sorted first with severity and secondly with registration time.
Data with datatypes:
factoryDeviceId: String -id of the factory device which this maintenance task concerns.
severity: Enum { CRITICAL, IMPORTANT, UNIMPORTANT } -Severity of the task.
status: Enum { OPEN, CLOSED } -Is the task open or closed.
description: String -Description about the maintenance task.
timestamp: Long -Timestamp when this maintenance task was registered.
id: String -id of the maintenance task.

Example when three maintenance tasks are registered.
[{"factoryDeviceId":"638f028a35c5ce5d76cdc310","severity":"CRITICAL","description":"Maintenance task 3","status":"CLOSED","timestamp":1670316682770,"id":"638f028b35c5ce5d76cdc40c"},
{"factoryDeviceId":"638f028a35c5ce5d76cdc16c","severity":"CRITICAL","description":"Maintenance task 5","status":"OPEN","timestamp":1670316682777,"id":"638f028b35c5ce5d76cdc40e"},
{"factoryDeviceId":"638f028a35c5ce5d76cdc121","severity":"CRITICAL","description":"Maintenance task 6","status":"CLOSED","timestamp":1670316682781,"id":"638f028b35c5ce5d76cdc40f"}]

GET
/maintenancetasks/{id}
Get on maintenance task with id
Example
get: /maintenancetasks/638f028b35c5ce5d76cdc42e

{"factoryDeviceId":"638f028a35c5ce5d76cdc320","severity":"IMPORTANT","description":"Maintenance task 37","status":"CLOSED","timestamp":1670316682887,"id":"638f028b35c5ce5d76cdc42e"}

POST
/maintenancetasks
Post new maintenance task
JSON data in body of the request
factoryDeviceId -Which device maintenance task is related to
severity -Severity of the task, CRITICAL, IMPORTANT and UNIMPORTANT
status -status of the task OPEN or CLOSE
Example
{
    "factoryDeviceId": "638c5c4a1685cc63e5fcede5",
    "severity": "UNIMPORTANT",
    "description": "Testing",
    "status": "OPEN"
}

PUT

DELETE



