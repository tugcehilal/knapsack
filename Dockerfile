# Use an official Java runtime as a parent image
FROM maven:3.6.3-openjdk-11

# Set the working directory in the container to /app
WORKDIR /app

# Add the current directory contents into the container at /app
ADD . /app

# Package any included application within Docker image
RUN mvn clean package

# Make port 80 available to the world outside this container
EXPOSE 80

# Run the application when the container launches
CMD ["java", "-jar", "target/knapsack-1.0-SNAPSHOT.jar"]
