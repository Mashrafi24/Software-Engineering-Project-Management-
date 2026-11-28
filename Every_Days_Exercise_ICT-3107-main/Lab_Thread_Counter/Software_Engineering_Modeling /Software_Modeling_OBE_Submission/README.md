# Software Design — Solved OBE Question Set


## Student Information

| Field | Details |
|---|---|
| **Name** | Md. Mashrafi Rahman |
| **Student ID** | IT-24034 |
| **University** | Mawlana Bhashani Science and Technology University (MBSTU) |
| **Department** | Information and Communication Technology (ICT) |
| **Course Title** | Software Engineering Project Management |
| **Course Code** | ICT_3107 |
| **Topic** | Software Design |



**Full Marks:** 40 · **Time:** 1 Hour  
**BNQF Domain:** Cognitive  
**Bloom levels:** K = Knowledge/Remember · U = Understanding · A = Application

> **Study note:** The paper instructs students to answer all Section A questions, any four from Section B, and any two from Section C. For practice, solutions are provided for every question, including the optional questions.

---
## Here Question :

!<img width="542" height="773" alt="image" src="https://github.com/user-attachments/assets/9f683783-8b51-4be2-bc8a-ef113c1a4865" />

<img width="577" height="608" alt="image-1" src="https://github.com/user-attachments/assets/6c11567c-94c4-4717-aaae-87fc14856ac5" />


## Section A — Short Answers
**Answer all. (8 × 1 = 8 marks)**

### Q1. Define software design. (K · CO1)

Software design is the process of transforming software requirements into a plan or blueprint that describes the system's structure, components, interfaces and data so that it can be implemented.

### Q2. Name the two steps of the software design process. (K · CO1)

1. **Architectural design:** Identifies the main components or subsystems and their relationships.
2. **Detailed design:** Describes the internal structure and behaviour of each component.

### Q3. What does low coupling mean? (K · CO2)

Low coupling means that modules have few dependencies on one another. A change in one module therefore has less effect on other modules.

### Q4. Define cohesion. (K · CO2)

Cohesion is the degree to which the elements within a module are related and work together to perform one focused task. High cohesion is desirable.

### Q5. What does the LCOM2 metric measure? (K · CO3)

LCOM2 (Lack of Cohesion in Methods) measures how poorly the methods of a class are connected through shared instance variables. A higher value generally indicates lower cohesion.

### Q6. List any two architectural styles. (K · CO4)

- Layered architecture
- Model–View–Controller (MVC) architecture

### Q7. Under which GoF category does the Adapter pattern fall? (K · CO5)

The Adapter pattern is a **Structural** design pattern.

### Q8. State in one sentence what the Domain Model pattern (Fowler) represents. (K · CO5)

The Domain Model pattern represents the business domain as a set of objects that contain both domain data and the business rules or behaviour associated with that data.

---

## Section B — Short Questions
**Answer any 4 of 5. (4 × 4 = 16 marks)**

### Q9. Distinguish between architectural (high-level) design and detailed design. (U · CO1)

| Architectural design | Detailed design |
|---|---|
| Describes the overall system structure. | Describes the internal design of each component. |
| Identifies major components/subsystems. | Specifies classes, methods, algorithms and data structures. |
| Defines how components communicate. | Defines how each component performs its responsibility. |
| Focuses on the big picture. | Focuses on implementation-level details. |

**Example:** For an online shopping system, architectural design identifies modules such as Customer, Product, Order and Payment. Detailed design specifies the methods and data fields inside the `Order` class and how an order is calculated.

### Q10. Explain, with a suitable example, how high cohesion and low coupling improve maintainability. (U · CO2)

**High cohesion** means a module has one clear responsibility. **Low coupling** means it depends as little as possible on other modules.

Example: In an online shop, a `PaymentService` should handle payment processing only. It should not also manage product inventory or generate invoices. It can communicate with those modules through small, well-defined interfaces.

Benefits:
1. A change in payment processing is less likely to affect inventory or invoicing.
2. Each module is easier to understand and test.
3. Bugs can be isolated more easily.
4. New features can be added with fewer changes to existing code.

Therefore, high cohesion and low coupling make software easier to maintain and extend.

### Q11. What does WMC measure? Explain the two ways it can be calculated. (U · CO3)

**WMC (Weighted Methods per Class)** measures the complexity of a class by considering the complexity of its methods.

Two common calculation approaches are:

1. **Equal weight:** Count the methods in the class, treating each method as having weight 1.
   \[
   WMC = \text{Number of methods}
   \]
2. **Complexity weight:** Add the complexity value of each method, such as its cyclomatic complexity.
   \[
   WMC = \sum_{i=1}^{n} c_i
   \]

A higher WMC may indicate that a class is complex, harder to understand, test and maintain.

### Q12. Describe the Pipes and Filters architectural style. Give one benefit and one liability. (U · CO4)

In the **Pipes and Filters** style, processing is divided into independent components called *filters*. Each filter receives input, processes it and sends its output through a *pipe* to the next filter.

```text
Input → [Filter 1] → Pipe → [Filter 2] → Pipe → [Filter 3] → Output
```

**Example:** A data-processing system may read a file, clean the data, sort it and then generate a report. Each stage is a separate filter.

- **Benefit:** Filters can be reused, replaced or rearranged independently.
- **Liability:** Data conversion and communication between filters may add overhead; the style can also be awkward when processing requires shared state or complex interaction.

### Q13. Differentiate between the Strategy and Template Method patterns in terms of intent and structure. (U · CO5)

| Strategy | Template Method |
|---|---|
| Defines a family of interchangeable algorithms and makes them selectable. | Defines the skeleton of an algorithm in a superclass and lets subclasses redefine selected steps. |
| Usually uses composition: a context holds a strategy object. | Uses inheritance: subclasses implement or override steps. |
| The algorithm can be changed at runtime by replacing the strategy. | The overall algorithm structure is fixed by the superclass. |
| Example: choosing CardPayment or MobilePayment. | Example: a report-generation superclass defining `read()`, `process()` and `print()` steps. |

**Main difference:** Strategy varies the whole algorithm through composition, while Template Method varies selected steps of a fixed algorithm through inheritance.

---

## Section C — Long / Problem-Based Questions
**Answer any 2 of 3. (2 × 8 = 16 marks)**

### Q14. Calculate and interpret CBO and LCOM2. Suggest one refactoring. (CO3 · Bloom: A)

**Given:**

- `OrderController` uses `OrderModel`, `OrderView` and `Logger`.
- `Validator` also uses `OrderController`.
- `OrderModel` has methods and attributes:

| Method | Attributes used |
|---|---|
| m1 | a, b |
| m2 | b |
| m3 | c |

#### (a) Calculate CBO for OrderController, counting coupling in either direction. (2)

Coupling Between Objects (CBO) counts the number of distinct other classes coupled to the target class. Since the question says to count coupling in **either direction**, count both classes it uses and classes that use it.

- Outgoing dependencies: `OrderModel`, `OrderView`, `Logger` = 3
- Incoming dependency: `Validator` = 1

\[
\boxed{CBO(OrderController)=3+1=4}
\]

#### (b) Calculate LCOM2 for OrderModel, showing the values of P and Q. (4)

For this question:

- **P** = number of pairs of methods that do not share an instance attribute.
- **Q** = number of pairs of methods that share at least one instance attribute.
- \(LCOM2 = \max(P-Q,0)\).

There are three method pairs:

| Method pair | Shared attribute? | Classification |
|---|---|---|
| m1, m2 | Yes — b | Q |
| m1, m3 | No | P |
| m2, m3 | No | P |

Therefore:

- \(P=2\)
- \(Q=1\)

\[
LCOM2=\max(2-1,0)=\boxed{1}
\]

#### (c) Interpret the result and suggest one refactoring. (2)

An LCOM2 value of 1 indicates some lack of cohesion: `m3` does not share an attribute with either `m1` or `m2`. The class may contain more than one responsibility.

**Suggested refactoring:** If `m3` represents a separate responsibility, move it and the related attribute `c` into a separate class. This can improve cohesion and make both classes easier to maintain.

### Q15. Explain Layered and MVC architectural styles, with the responsibility of each component or layer. Compare them. (CO4 · Bloom: U)

#### (a) Layered architecture

Layered architecture organizes a system into horizontal layers. Each layer has a specific responsibility and normally uses the services of the layer below it.

```text
+------------------------------+
| Presentation / UI Layer      |
| User interface and display   |
+------------------------------+
              ↓
+------------------------------+
| Business Logic Layer         |
| Rules and application logic  |
+------------------------------+
              ↓
+------------------------------+
| Data Access Layer            |
| Database operations          |
+------------------------------+
              ↓
+------------------------------+
| Database                     |
| Persistent data              |
+------------------------------+
```

**Responsibilities:**
1. **Presentation layer:** Accepts user input and displays output.
2. **Business logic layer:** Applies business rules and processes requests.
3. **Data access layer:** Reads and writes data in the database.
4. **Database:** Stores persistent information.

#### (b) Model–View–Controller (MVC)

MVC separates an application into three cooperating components.

```text
             User
              |
              v
        +-----------+
        | Controller|
        +-----------+
          |       |
   updates|       |selects
          v       v
     +---------+  +------+
     |  Model  |  | View |
     +---------+  +------+
          |          |
          +----------+
          Model data
          displayed
          in the View
```

**Responsibilities:**
1. **Model:** Holds application data and domain logic.
2. **View:** Presents information to the user.
3. **Controller:** Handles user input, coordinates actions and updates the model or selects a view.

#### (c) Comparison

| Property | Layered architecture | MVC |
|---|---|---|
| Main organization | Divides the system into technical layers. | Separates model, presentation and input control. |
| Main purpose | Separation of responsibilities across system tiers. | Separation of user interface concerns. |
| Typical use | Enterprise systems and applications with data/business layers. | Web and desktop user-interface applications. |
| Relationship | Layers usually call services in adjacent/lower layers. | Controller coordinates Model and View. |

**Conclusion:** Layered architecture structures the overall application into technical tiers, while MVC structures the interaction and presentation part of an application. They can also be used together.

### Q16. For the Facade pattern, describe its intent, motivation, structure, benefits and liabilities. (CO5 · Bloom: U)

#### (a) Intent (1)

The **Facade** is a Structural GoF design pattern that provides a simple, unified interface to a complex subsystem.

#### (b) Motivation, with a short example (2)

A client may need to interact with several classes to complete one task. This makes the client code complex and tightly coupled to the subsystem.

**Example:** To start a computer, a client may need to start the CPU, check memory and load the operating system. A `ComputerFacade` can provide one simple method, `startComputer()`, that coordinates these operations.

#### (c) Structure (participants and labelled diagram) (2)

**Participants:**
- **Client:** Uses the simple facade interface.
- **Facade:** Provides the unified interface and coordinates subsystem operations.
- **Subsystem classes:** Perform the actual work.

```text
             +----------------+
             |     Client     |
             +----------------+
                     |
                     | calls
                     v
             +----------------+
             |     Facade     |
             | startComputer()|
             +----------------+
                /     |     \
               /      |      \
              v       v       v
        +--------+ +--------+ +--------+
        |  CPU   | | Memory | |  OS    |
        +--------+ +--------+ +--------+
          Subsystem classes
```

#### (d) Benefits and liabilities, including the “God class” risk (3)

**Benefits:**
1. Simplifies the interface to a complex subsystem.
2. Reduces coupling between clients and subsystem classes.
3. Makes common operations easier to perform.
4. Can help organize subsystem access behind one entry point.

**Liabilities:**
1. The facade may become a **God class** if too many unrelated responsibilities are added to it.
2. It may hide useful subsystem functionality from clients that need direct access.
3. It does not remove the subsystem's complexity; it only provides a simpler interface to it.

**Conclusion:** Facade improves usability and reduces client-side complexity, but the facade itself should remain focused and should not become responsible for the entire system.

---

## Quick Revision — Key Formulas

| Metric | Meaning | Formula / interpretation |
|---|---|---|
| CBO | Coupling Between Objects | Number of distinct coupled classes |
| WMC | Weighted Methods per Class | \(\sum c_i\), or method count when each weight is 1 |
| LCOM2 | Lack of Cohesion in Methods | \(\max(P-Q,0)\), where P = non-sharing pairs and Q = sharing pairs |

## Bloom's Taxonomy and BNQF Mapping

| Section | Main Bloom level | BNQF domain | Assessment focus |
|---|---|---|---|
| A | Knowledge / Remember (K) | Cognitive | Recall definitions, names and basic facts |
| B | Understanding (U) | Cognitive | Explain, compare and describe concepts |
| C | Application (A) and Understanding (U) | Cognitive | Calculate metrics, interpret results, apply and explain design concepts |

## Important exam reminders

- For CBO in Q14, count the incoming `Validator` dependency because the question explicitly says to count coupling in either direction.
- For LCOM2 in Q14, show the three method pairs before calculating P and Q.
- In Q15, label the layers and MVC components clearly in the diagrams.
- In Q16, label Client, Facade and subsystem classes; mention the God class risk explicitly.


source: IT-24006 
