# Company Management System
A robust and well-designed Java console application that simulates a company management environment. This project serves as a comprehensive demonstration of advanced **Object-Oriented Programming (OOP)** principles and **design patterns**, focusing on clean, maintainable, and scalable code architecture.


## Features
This application provides a full suite of company management tools:

👥 **Employee Management:** Hire and fire employees with different roles (e.g., Developer, Manager).

🧩 **Team Structuring:** Dynamically group employees into teams for better project organization.

✅ **Task Assignment:** Assign specific tasks to individuals or entire teams and track their progress.

📊 **Department Overview:** View a structured hierarchy of the entire company, including departments, teams, and employees.

💾 **Data Persistence:** Save and load the entire company state to/from a file for persistent data management.

## 🎯 Project Focus: Mastering OOP & SOLID
This project was meticulously crafted to be a practical workshop for advanced OOP concepts. It's not just about making it work; it's about making it right.

1. **SOLID Principles in Action**
Principle	Implementation Example
Single Responsibility	Each class has a clear, singular purpose (e.g., Company manages structure, Task models a task, FileService handles only I/O operations).
Open/Closed	The code is open for extension (adding new Employee types like Designer) but closed for modification (core logic doesn't change).
Liskov Substitution	Developer and Manager objects can be used interchangeably wherever an Employee is expected without breaking functionality.
Interface Segregation	Specific interfaces (e.g., ITaskAssignable) are defined so classes don't depend on methods they don't use.
Dependency Inversion	High-level modules (e.g., Company) depend on abstractions (e.g., the Employee interface/abstract class), not concrete low-level modules.
2. **Core OOP Pillars**
Encapsulation: All class fields are private, accessed only through public getters and setters, protecting the internal state of objects (e.g., an employee's salary cannot be changed arbitrarily without going through a setSalary method).

**Inheritance**: A base Employee abstract class contains common properties and methods, which are inherited by specialized classes like Developer and Manager, promoting code reuse.

**Polymorphism**: Methods like calculateBonus() can behave differently depending on the actual object type (a Manager might have a different bonus calculation than a Developer), often achieved through method overriding.

**Abstraction**: The Employee abstract class and interfaces like IWorkable define a contract, hiding complex implementation details and exposing only essential features.



## 👨‍💻 Developer
Ross Logger

GitHub: @Ross-logger

This project was created to solidify understanding of advanced Java and OOP concepts.
