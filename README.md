
## This project:
This work proposes an empirical investigation to define the best JVM arguments in six specific scenarios, aiming to optimize both performance and resource savings. The application was tested in an isolated environment with limited hardware, using tools such as JMeter and cAdvisor to collect and analyze performance metrics, based on the premise that precise adjustments can significantly improve both the stability and response of the application.

How to test:

## Prerequisites
- Docker installed on your machine.
- JMeter installed on your machine.
- Access to Java Mission Control (JMC) or familiarity with using Docker stats for monitoring.

## Step 1: Set Up the Docker Environment

### 1. Clone the Repository
Clone the repository containing the Dockerfiles and the Docker Compose configuration.
```bash
git clone https://github.com/DarlanNoetzold/JVMParamsCompare
```
### 2. Navigate to the Project Directory
Change directory to the cloned repository and Compile the project. 

mvn clean install

OBS.: Run the maven command just if you have the rabbitmq and the postgres up. If not, just use the .jar already compiled in the /target folder of this repository.

### 3. Build the Docker Images
Use the Docker Compose file to build the Docker images for each application scenario.

docker-compose build
## Step 2: Running the Application
### 1. Start the Services
Use Docker Compose to start the services defined in your docker-compose.yml.

docker-compose up -d

### 2. Verify the Containers are Running
Check the status of your Docker containers.

docker ps

## Step 3: Executing JMeter Tests
### 1. Prepare JMeter Test Plan
Ensure your JMeter test plans (jmeter_tests.jmx) are correctly configured to target your Docker-hosted application. Adjust the host and port settings if necessary.

### 2. Run JMeter Test Plan
Execute the JMeter test plan from the command line or through the JMeter GUI.

jmeter -n -t jmeter_tests.jmx -l results.jtl

-n runs JMeter in non-GUI mode, -t specifies the test file, and -l specifies the results file.

## Step 4: Monitoring Performance
### 1. Using Java Mission Control
Connect JMC to your running application to monitor JVM performance metrics.
Launch JMC and connect to the JVM using the JMX port (make sure JMX is configured in your JVM arguments).

### 2. Using Docker Stats
Alternatively, use Docker stats to monitor the resource usage of your Docker containers.

docker stats

## Step 5: Collect and Analyze Results
### 1. Analyze JMeter Results
Review the output file (results.jtl) using JMeter's GUI or any other suitable tool to analyze HTTP request responses and performance metrics.

### 2. Review Docker/JMC Metrics
Collect data from Docker stats or JMC during the JMeter load tests to understand the CPU, memory usage, and other vital metrics.
