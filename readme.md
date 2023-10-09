Sure, I can help you structure your README.md file. Here's a more structured version:

# Knapsack Problem Solver

This project provides a Java solution for the knapsack problem. It uses a dynamic programming approach to determine the optimal set of items to include in a package such that the total weight is less than or equal to the package limit and the total cost is maximized.

## Table of Contents
1. [Getting Started](#getting-started)
2. [Prerequisites](#prerequisites)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Testing](#testing)

## Getting Started

These instructions will guide you on how to get a copy of this project up and running on your local machine for development and testing purposes.

## Prerequisites

Ensure you have the following installed on your local machine:

- Java 8 or higher
- Maven

## Installation

Follow these steps to get a development environment running:

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Run `mvn clean install` to build the project.

## Usage

The main class of the application is `com.mobiquity.packer.Packer`. This class has a static method `pack` that takes the path to a file as an argument and returns a string representing the optimal packing solution for each test case in the file.

Please note that this is a standalone application and does not include a main method.

## Testing

You can test the functionality with the following command:

```bash
mvn test -Dconfig.properties=src/main/resources/config.properties -Dinput.file.path=src/test/resources/example_input -Doutput.file.path=src/test/resources/example_output
```

Feel free to replace `input_file`, `output_files`, and `config.properties` files with those you desire.

Also, remember to update the .jar file directory in Dockerfile depending on where it is located.