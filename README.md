# EDE Project - Dylan Vanhoudt
## Beschrijving van het gekozen thema
Ik heb gekozen om een project te maken in het thema van medisch beheer. Het biedt een mogelijkheid voor bijvoorbeeld werknemers van een ziekenhuis om de gegevens van hun patiënten te beheren, net als hun afspraken en de bijhorende betalingen.
## Componentenlijst
- Patient Service: microservice voor het beheer van patiënten.
- Appointment Service: microservice voor het beheer van afspraken.
- Payment Service: microservice voor het beheer van betalingen.
- API Gateway: dient als een enkel toegangspunt om te communiceren met de microservices. Zijn primaire rol is het beheren en routeren van inkomende verzoeken naar de juiste microservice, waarbij hij fungeert als een gecentraliseerd toegangspunt.
## Deployment Diagram
![DeploymentDiagram](https://github.com/DylanVanhoudt/ProjectMicroservices/assets/91118385/42ebe2ee-899f-4c79-88d6-6a4a14e9bd91)
## Link naar hosted endpoint
https://api-gateway-dylanvanhoudt.cloud.okteto.net
## Werking van alle endpoints
- Create a patient
![image](https://github.com/DylanVanhoudt/ProjectMicroservices/assets/91118385/d2ed8422-86b9-494b-a6af-8688b94d8674)
- Get all patients
![image](https://github.com/DylanVanhoudt/ProjectMicroservices/assets/91118385/09921109-6898-4630-b9b8-55a3e1e2420b)
- Get patient by number
![image](https://github.com/DylanVanhoudt/ProjectMicroservices/assets/91118385/f1598041-c099-4daa-a488-72545d944581)
- Update a patient
![image](https://github.com/DylanVanhoudt/ProjectMicroservices/assets/91118385/79349ab6-f794-4774-99f5-70dcb0218226)
![image](https://github.com/DylanVanhoudt/ProjectMicroservices/assets/91118385/2267cac0-dfbe-428e-a4af-175affc06f6c)
- Delete a patient
![image](https://github.com/DylanVanhoudt/ProjectMicroservices/assets/91118385/6b5c12f3-778e-4fe2-8d20-09ab585f1e95)
![image](https://github.com/DylanVanhoudt/ProjectMicroservices/assets/91118385/55b80eb3-32f9-42f9-a6c9-9566f75dbf1f)
- Create an appointment
![image](https://github.com/DylanVanhoudt/ProjectMicroservices/assets/91118385/2e47352d-4b2c-42d0-aefe-a4747ec95c85)
- Get all appointments
![image](https://github.com/DylanVanhoudt/ProjectMicroservices/assets/91118385/d8d2ea30-3036-4a4d-81e6-d062c6eaaea2)
- Get appointments by patient number
![image](https://github.com/DylanVanhoudt/ProjectMicroservices/assets/91118385/e6c0d3b6-a1c3-4807-9f3b-5e3107afeade)
- Create a payment
![image](https://github.com/DylanVanhoudt/ProjectMicroservices/assets/91118385/d4beecb1-b126-484d-bfc1-9956b8b35219)
- Get all payments
![image](https://github.com/DylanVanhoudt/ProjectMicroservices/assets/91118385/d2e4c8f3-7bbc-4016-a385-19ddefd2189e)
- Get payments by appointment number
![image](https://github.com/DylanVanhoudt/ProjectMicroservices/assets/91118385/3ce4aa8b-f651-45d9-871c-72efb7a2d8f2)
- Update a payment
![image](https://github.com/DylanVanhoudt/ProjectMicroservices/assets/91118385/a68b53fd-b065-4ea6-b224-8951e010c069)
![image](https://github.com/DylanVanhoudt/ProjectMicroservices/assets/91118385/8f2af60b-06e5-42e0-9995-e0f1c02be3cd)
