## Problem
The company ACME offers their employees the flexibility to work the hours they want. But due to some external circumstances they need to know what employees have been at the office within the same time frame
The goal of this exercise is to output a table containing pairs of employees and how often they have coincided in the office.
Input: the name of an employee and the schedule they worked, indicating the time and hours. This should be a .txt file with at least five sets of data. You can include the data from our examples below:

**Example 1:**
INPUT
RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00- 21:00
ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00
ANDRES=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00

OUTPUT:
ASTRID-RENE: 2
ASTRID-ANDRES: 3
RENE-ANDRES: 2

**Example 2:**
INPUT:
RENE=MO10:15-12:00,TU10:00-12:00,TH13:00-13:15,SA14:00-18:00,SU20:00-21:00
ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00

OUTPUT:
RENE-ASTRID: 3

## Solution
The first step is to identify how the data is arranged in the txt file, to later create a structure and save them to be able to handle them.

We can also get an idea of how the expected output of the program is structured.
![Diagrama en blanco](https://user-images.githubusercontent.com/52268702/153798193-ccb95092-9331-4565-bfdc-9b0604be8da0.svg)

### Architecture

To save the extracted information, the following was identified:

The txt file represents a set of employees, so we can generate a class called **Company**, which contains all the employees.

Each line of the txt file represents an employee. For whatever it is create the **Employee** class.
An employee has a name, and a list of schedules, which represent the hours they work, due to this a **Schedule** class is created, because it will hold more information inside.

Each schedule is made up of 2 characters at the beginning that refer to the days of the week Example: SU = Sunday, followed by that there are 2 hours, a start time and an end time, which we must store in attributes of the time class.

See the image for more information.
![uml](https://user-images.githubusercontent.com/52268702/153800215-cb4f5fb5-a65e-42e8-a380-648d1eed9a37.svg)

The **Company**, **Employees**, **Schedules** classes are created, with the corresponding attributes, as can be seen in the image, we also generate some methods to implement an algorithm.

### Algorithm
There are several employees, of which we must compare their schedules with each other, but we must not repeat their iteration since it would give us double results, which are wrong.

The way to iterate will be like the following.

![iterations](https://user-images.githubusercontent.com/52268702/153801189-845937ef-7ee8-4637-8b13-129b8fe39b7c.svg)

They have 2 schedules, **Schedule1** and **Schedule2**


The **end time of schedule 1** must be greater than the **start time of schedule 2** and the **start time of schedule 1** must be less than the **end time of schedule 2**

![matches between two](https://user-images.githubusercontent.com/52268702/153802314-c0198dab-e668-4c76-aaf4-8077c01a0ca8.svg)

#### Algorithm Demonstration

![iter1](https://user-images.githubusercontent.com/52268702/153802460-5cedea17-f99f-41a5-a6c8-0f06ed4454d1.svg)

![iter2](https://user-images.githubusercontent.com/52268702/153802441-4cc3695c-d83b-4f29-abee-d9abee1f54c4.svg)
![iter3](https://user-images.githubusercontent.com/52268702/153802479-5db8efff-c73e-45ef-b447-878a5cd9b357.svg)

When the coincidences of schedules can be identified, a counter will be created, which will serve to keep track of the coincidences.

Once the count of equal hours between two employees is finished, their names will be obtained and saved in a string, concatenating the number of matches.

## Instructions to Compile

The solution is generated in the **Java** programming language, in a project with the **Maven** package manager.

For its implementation, the maven file must be compiled, to install the Junit dependencies.

The solution is executed in the **console**, when executing the **Main.java Class** in the folder **src/test/java/com/ioet/exercise/schedule/test**
There is a Main class which is executed, and in which some methods are implemented, to execute you must have a file of employees with their schedules, and you must copy the path to change it in the Main class.

At the end of the execution of the main class, a file is generated in the path **C:/Coincidences.txt**, which can be modified to preference.

It is recommended to open the project with an **IDE** like **Eclipse** or **Netbeans**, that will facilitate the execution and manipulation of methods.

**Each method of the project is commented which will give more information.**
