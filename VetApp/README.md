# Veterinary Management System

The Veterinary Management System project aims to create an API that allows a veterinary clinic to manage its operations effectively. This application is intended to be used by veterinary staff to manage veterinarians, their availability, customers, animals, vaccinations, and appointments.

## Project Purpose

The purpose of this project is to streamline and automate the operations of a veterinary clinic. By using this API, veterinary staff can handle the registration and management of doctors, their available working days, customers, animals belonging to customers, vaccinations applied to animals, and appointments for the animals.

## Technologies Used

- **Java 22**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Maven**
- **Lombok**
- **ModelMapper**
- **Spring Web**

## Project Features

### Animal and Customer Management

- Register, update, view, and delete animals.
- Register, update, view, and delete customers.
- Create endpoints to filter animals by name and customers by name.
- Provide an endpoint to list all animals belonging to a specific customer by filtering them based on the customer.

### Vaccine Management

- Register, update, view, and delete vaccinations applied to animals.
- Prevent new vaccinations from being added if an existing vaccine of the same type (same name and code) has not yet reached its protection end date.
- Create an endpoint to list all vaccination records for a specific animal based on the animal's ID.
- Provide an endpoint to list animals with approaching vaccination protection end dates based on user-specified start and end dates.

### Appointment Management

- Create, update, view, and delete appointments for animal vaccinations and examinations.
- Ensure that appointments include both date and time.
- Appointments are only being created if the veterinarian is available on the given date and time. Each doctor is available for appointments only on the hour and each examination is assumed to take one hour.
- Validate that the doctor is available on the given date and time, and that there is no conflicting appointment. If either condition is not met, program returning an error message such as "The doctor is not available on this date!" or "There is already an appointment at this time!" with using custom exceptions.
- Endpoints to filter appointments by date range and doctor, as well as by date range and animal.

### Veterinarian Management

- Register, update, view, and delete veterinarian doctors.

### Doctor Availability Management

- Register, update, view, and delete the availability of doctors.
- Doctors' available working days recorded, without including time information.

## Important Considerations

- **Layered Architecture**: The project followed a layered architecture.
- **IoC and DI**: Used constructor injection for IoC and DI.
- **Validation**: Ensure that duplicate records are not created. If a record already exists, throws a custom exception with a message like "The record already exists."
- **Exceptions**: Used custom exceptions to handle scenarios like record not found during update or delete operations.
- **Fetch and Cascade**: Used appropriate fetch and cascade annotations.
- **HTTP Status Codes**: Used correct and meaningful HTTP status codes.

## Getting Started

To get started with the project, clone the repository and import it into your IDE. Configure the database settings in the `application.properties` file and run the application.



# API Documentation

## Animal Controller

| HTTP Method | Endpoint         | Description                                      |
|-------------|------------------|--------------------------------------------------|
| POST        | /v1/animals      | Save a new animal                                |
| GET         | /v1/animals/id/{id} | Get an animal by its ID                          |
| GET         | /v1/animals      | Get all animals                                  |
| PUT         | /v1/animals      | Update an existing animal                        |
| DELETE      | /v1/animals/{id} | Delete an animal by its ID                       |
| GET         | /v1/animals/{name} | Get animals by name                              |

## Appointment Controller

| HTTP Method | Endpoint                     | Description                                      |
|-------------|------------------------------|--------------------------------------------------|
| POST        | /v1/appointments             | Save a new appointment                           |
| GET         | /v1/appointments/{id}        | Get an appointment by its ID                     |
| GET         | /v1/appointments             | Get all appointments                             |
| PUT         | /v1/appointments             | Update an existing appointment                   |
| DELETE      | /v1/appointments/{id}        | Delete an appointment by its ID                  |
| GET         | /v1/appointments/search-by-animal-and-date | Find appointments by animal and date |
| GET         | /v1/appointments/search-by-doctor-and-date | Find appointments by doctor and date |

## Available Date Controller

| HTTP Method | Endpoint         | Description                                      |
|-------------|------------------|--------------------------------------------------|
| POST        | /v1/availabledates | Save a new available date                        |
| GET         | /v1/availabledates/{id} | Get an available date by its ID                  |
| GET         | /v1/availabledates | Get all available dates                          |
| PUT         | /v1/availabledates | Update an existing available date                |
| DELETE      | /v1/availabledates/{id} | Delete an available date by its ID               |

## Customer Controller

| HTTP Method | Endpoint         | Description                                      |
|-------------|------------------|--------------------------------------------------|
| POST        | /v1/customers    | Save a new customer                              |
| GET         | /v1/customers/id/{id} | Get a customer by its ID                         |
| GET         | /v1/customers    | Get all customers                                |
| PUT         | /v1/customers    | Update an existing customer                      |
| DELETE      | /v1/customers/{id} | Delete a customer by its ID                      |
| GET         | /v1/customers/{name} | Get customers by name                            |

## Doctor Controller

| HTTP Method | Endpoint         | Description                                      |
|-------------|------------------|--------------------------------------------------|
| POST        | /v1/doctors      | Save a new doctor                                |
| GET         | /v1/doctors/{id} | Get a doctor by its ID                           |
| GET         | /v1/doctors      | Get all doctors                                  |
| PUT         | /v1/doctors      | Update an existing doctor                        |
| DELETE      | /v1/doctors/{id} | Delete a doctor by its ID                        |

## Vaccine Controller

| HTTP Method | Endpoint               | Description                                      |
|-------------|------------------------|--------------------------------------------------|
| POST        | /v1/vaccines           | Save a new vaccine                               |
| GET         | /v1/vaccines/id/{id}   | Get a vaccine by its ID                          |
| GET         | /v1/vaccines           | Get all vaccines                                 |
| PUT         | /v1/vaccines           | Update an existing vaccine                       |
| DELETE      | /v1/vaccines/{id}      | Delete a vaccine by its ID                       |
| GET         | /v1/vaccines/{name}    | Get vaccines by name                             |
| GET         | /v1/vaccines/animalid/{id} | Get vaccines by animal ID                        |
| GET         | /v1/vaccines/near-vaccines | Get vaccines with protection dates near a range  |
