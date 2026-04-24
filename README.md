### The original README file has been renamed to PROBLEMSTATEMENT.md
### This file contains my interpretation, design decisions and implementation details for the solution.

# Problem Statement Interpretation

By carefully reading the problem statement my analysis was the following:
1. We already have an existing employee management system, but we have to shift to a newer system recently purchased by the employer.
2. However, the transition cannot be done immediately as the existing system is TIGHTLY COUPLED with other services.
3. My job is basically to build a secure bridge which exposes the employee data to Employees-R-US before we are ready for complete migration.

# Design Decisions

1. As the problem statement explicitly states SECURE, I have implemented API-KEY-AUTHENTICATION to validate user requests. The keys are passed via request header and authenticated via Spring Security Filter Chain. In my opinion this was the safest option for this case as for implementing HMAC both the systems must agree upon the same principles, and we don't know what the new system supports. Also for machine-to-machine communication API keys are more appropriate than OAuth bearer tokens.
 
2. Then I have created a class named EmployeeModel which is supposed to handle employee data which we receive from the existing system. 
    Member Variables: 
    - firstName            (String)
    - lastName             (String)
    - fullName             (String)
    - salary               (Integer)
    - age                  (Integer)
    - jobTitle             (String)
    - email                (String) 
    - contractHireDate     (Instant) 
    - contractTerminationDate (Instant)
     
3. For storing the data, as stated in the provided controller skeleton code not to worry about any persistent data layer, I have used HashMaps( Key=UUID & Value=Employee ) to store new and existing employee mock data.

4. I have also added logging to keep track of errors if any are encountered during the execution.

5. Wrote custom exceptions for handling various scenarios.

6. EmployeeRequestDTO for handling incoming requests. I have used Java records for DTOs due to
   their immutability and validated incoming request fields.

   Fields:
    - firstName            (String)  - Required
    - lastName             (String)  - Required
    - fullName             (String)
    - salary               (Integer) - Required, must be positive
    - age                  (Integer) - Required, minimum 18
    - jobTitle             (String)  - Required
    - email                (String)  - Required, must be valid email format
    - contractHireDate     (Instant) - Required
    - contractTerminationDate (Instant)
7. HTTP endpoints implemented : GET : /fetch-all 
                                GET : /fetch-by-uuid/{uuid} 
                                POST : /create-new
8. The API-KEY is stored in a .env file. 
9. To run the application just change the name of the .env.example to .env and make sure that the request contains the Key = X-API-KEY and Value = {The value of the key} in the header.

# Resources used as reference for this project

1. Documents provided with the problem statement
2. API-Key Authentication : Medium Blog - https://medium.com/@abhishekranjandev/a-comprehensive-guide-to-api-authentication-securing-spring-boot-apis-with-api-key-and-secret-e20b069b367e
                            YT Video - https://www.youtube.com/watch?v=QtK0VNUkfzY
3. Exception Handling Docs : Medium - https://medium.com/@sharmapraveen91/handle-exceptions-in-spring-boot-a-guide-to-clean-code-principles-e8a9d56cafe8
                             YT(Selenium Express) : https://www.youtube.com/watch?v=ZeKP8mxbE2I&t=3843s
