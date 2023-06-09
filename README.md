# Operations Manager
This is a Spring Boot application that implements a service for performing various mathematical operations. It uses Spring Boot version 2.7.9 and is compatible with Java 11.

## Features
- Scalable design allows easy addition of new types of operations.
- Input validation ensures stability and correctness of the application.
- Custom exception handling improves the clarity of error messages.
- Separate `application.properties` for different environments (dev, beta, production).
- A controller `OperationsController` is exposed to accept POST requests for performing operations.

## Testing
We employ two strategies for unit testing:

1. For performance-critical units like `AdditionOperation`, we use MockitoExtension for isolated testing without loading the entire application context.
2. For testing the overall application context, especially the custom exception handling and input validation, we use SpringBootTest.

## Validations
The DTO fields in the requests are validated using custom annotations: `ValidNumbers` and `ValidOperations`, backed by their respective validators `NumbersValidator` and `OperationsValidator`. These validations prevent passing of invalid arguments to services and components during execution.

## Future Enhancements
The application is designed with scalability in mind. We have implemented an OperationFactory that can be easily extended to support new types of operations. In the future, we could add more operations such as Decimal, Binary, HEX Addition, Subtraction, Multiplication, and Division.

UML Diagram

## Usage
A sample curl command to invoke the operation execution endpoint is:
Currently there is just one implementation of operations which is decimal_addition and it only accepts
large non-negative numbers
For production usage replace port with 8083 and /dev with /production 
```bash
curl --location 'localhost:8081/dev/operations/execute' \
--header 'Content-Type: application/json' \
--data '{
  "operation": "decimal_addition",
  "numbers": ["23350909","23456","123", "1234"]
}';

