# Software Engineering – Diagrams for OBE Question Set

These diagrams correspond to the eight questions in the Software Engineering question set.
GitHub renders Mermaid diagrams automatically when this Markdown file is viewed in a repository.

## Q1. Software Design Process
**Bloom's Level:** Remember + Understand | **BNQF Domain:** Cognitive | **CO:** CO1 | **Marks:** 4

```mermaid
flowchart TD
    A["Software Requirements"] --> B["Architectural Design"]
    B --> C["Detailed Design"]
    C --> D["Interface Design"]
    D --> E["Component Design"]
    E --> F["Software Design Model"]
```

## Q2. Design Process Outcome and Design Rationale
**Bloom's Level:** Understand | **BNQF Domain:** Cognitive | **CO:** CO1 | **Marks:** 4

```mermaid
flowchart TD
    A["Software Design Process"] --> B["Design Decisions"]
    B --> C["Design Model"]
    B --> D["Design Rationale"]
    C --> E["Software Structure"]
    D --> F["Why and How Decisions Were Made"]
    E --> G["Better Understanding"]
    F --> G
    G --> H["Long-term Maintainability"]
```

## Q3. Modularity, Coupling and Cohesion
**Bloom's Level:** Remember | **BNQF Domain:** Cognitive | **CO:** CO2 | **Marks:** 4

```mermaid
flowchart TD
    A["Large Software System"] --> B["Modularity"]
    B --> C["Module A"]
    B --> D["Module B"]
    B --> E["Module C"]
    C <-->|"Dependencies"| D
    D <-->|"Dependencies"| E
    C --> F["Coupling"]
    D --> G["Cohesion"]
    F --> H["Low Coupling"]
    G --> I["High Cohesion"]
    H --> J["Better Maintainability"]
    I --> J
```

## Q4. Abstraction, Encapsulation and Information Hiding
**Bloom's Level:** Understand | **BNQF Domain:** Cognitive | **CO:** CO2 | **Marks:** 4

```mermaid
flowchart TD
    A["Software Object"] --> B["Public Interface"]
    A --> C["Internal Implementation"]
    B --> D["Abstraction"]
    C --> E["Encapsulation"]
    C --> F["Information Hiding"]
    D --> G["Shows Essential Features"]
    E --> H["Groups Data and Methods"]
    F --> I["Protects Internal Details"]
```

## Q5. CBO, LCOM and WMC Metrics
**Bloom's Level:** Understand | **BNQF Domain:** Cognitive | **CO:** CO3 | **Marks:** 8

```mermaid
flowchart TD
    A["Object-Oriented Class"] --> B["Design Quality Metrics"]
    B --> C["CBO"]
    B --> D["LCOM"]
    B --> E["WMC"]
    C --> F["Coupling Between Object Classes"]
    D --> G["Lack of Cohesion in Methods"]
    E --> H["Weighted Methods per Class"]
    F --> I["Measures Class Dependencies"]
    G --> J["Measures Lack of Internal Cohesion"]
    H --> K["Measures Method Complexity"]
    I --> L["Design Quality Assessment"]
    J --> L
    K --> L
```

## Q6. Layered and Event-Based Architecture
**Bloom's Level:** Remember + Understand | **BNQF Domain:** Cognitive | **CO:** CO4 | **Marks:** 4

### A. Layered Architecture
```mermaid
flowchart TD
    A["Presentation Layer"] --> B["Business Logic Layer"]
    B --> C["Data Access Layer"]
    C --> D[("Database Layer")]
```

### B. Event-Based Architecture
```mermaid
flowchart TD
    A["Event Producer"] --> B["Event / Message"]
    B --> C["Event Channel"]
    C --> D["Event Consumer 1"]
    C --> E["Event Consumer 2"]
    C --> F["Event Consumer 3"]
```

## Q7. Model–View–Controller (MVC) Architecture
**Bloom's Level:** Understand | **BNQF Domain:** Cognitive | **CO:** CO4 | **Marks:** 4

```mermaid
flowchart TD
    U["User"] --> V["VIEW"]
    V -->|"User Input"| C["CONTROLLER"]
    C -->|"Updates / Requests"| M["MODEL"]
    M -->|"Data / Results"| C
    C -->|"Selects View"| V
    M -->|"Data Display"| V
```

## Q8. Strategy and Adapter Design Patterns
**Bloom's Level:** Understand | **BNQF Domain:** Cognitive | **CO:** CO5 | **Marks:** 8

### A. Strategy Design Pattern
```mermaid
classDiagram
    class Context {
        -Strategy strategy
        +setStrategy(Strategy)
        +executeStrategy()
    }
    class Strategy {
        <<interface>>
        +execute()
    }
    class StrategyA {
        +execute()
    }
    class StrategyB {
        +execute()
    }
    Context o--> Strategy
    Strategy <|.. StrategyA
    Strategy <|.. StrategyB
```

### B. Adapter Design Pattern
```mermaid
classDiagram
    class Client {
        +request()
    }
    class Target {
        <<interface>>
        +request()
    }
    class Adapter {
        -Adaptee adaptee
        +request()
    }
    class Adaptee {
        +specificRequest()
    }
    Client --> Target
    Target <|.. Adapter
    Adapter --> Adaptee
```

## Infographic
![Software Design Infographic](images/software-design-infographic.png)

## Question-to-Diagram Checklist

| Question | Diagram(s) | Marks |
|---|---|---:|
| Q1 | Software Design Process | 4 |
| Q2 | Design Outcome and Rationale | 4 |
| Q3 | Modularity, Coupling and Cohesion | 4 |
| Q4 | Abstraction, Encapsulation and Information Hiding | 4 |
| Q5 | CBO, LCOM and WMC | 8 |
| Q6 | Layered and Event-Based Architecture | 4 |
| Q7 | MVC Architecture | 4 |
| Q8 | Strategy and Adapter Patterns | 8 |
| **Total** | **8 Questions** | **40** |
