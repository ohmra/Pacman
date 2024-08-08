# Pacman
Pacman game, 3rd year college project

# How to execute
## Using the .jar file
Download and unzip the project, and double click on the .jar file OR execute the following command in a command prompt:
```console
$ java -jar pacman.jar
```

## Using the source files
- Download and unzip the project, open a terminal in the extracted folder and execute the following commands :
```console
$ javac -sourcepath src/ -d bin src/controller/Main.java
$ java -cp bin controller/Main
```

# Features
- 4 Pacgums
- - 2 purples PacGums
  - 1 green PacGum that changes the map
  - 1 orange PacGums that make Super Pacman
- The ghost move randomly
- There is a restart button and a GameSpeed menu
