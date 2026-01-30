# Unit Test Results

## Test Framework

- **JUnit 5** (Jupiter) with JUnit Platform Console Launcher
- **Java 17** (--release 17)

## Baseline Run (Before Modern Java Refactor)

| Metric | Value |
|--------|--------|
| **Containers (test classes)** | 16 |
| **Tests found** | 92 |
| **Tests passed** | 92 |
| **Tests failed** | 0 |
| **Tests skipped** | 0 |
| **Status** | **All pass** |

### Containers Covered

| Container | Test Class | Tests |
|-----------|------------|-------|
| Stack (StackAsLinkedList) | StackTest | 9 |
| Queue (QueueAsLinkedList) | QueueTest | 8 |
| MyContainer | MyContainerTest | 5 |
| BinaryHeap | BinaryHeapTest | 9 |
| PriorityQueueAsLinkedList | PriorityQueueAsLinkedListTest | 3 |
| MyLinkedList | MyLinkedListTest | 10 |
| DoublyLinkedList | DoublyLinkedListTest | 8 |
| BinaryTree | BinaryTreeTest | 12 |
| BinarySearchTree | BinarySearchTreeTest | 8 |
| ExpressionTree | ExpressionTreeTest | 4 |
| GraphAsLists | GraphAsListsTest | 7 |
| GraphAsMatrix | GraphAsMatrixTest | 6 |
| Container + Visitor | ContainerVisitorTest | 3 |

### How to Run Tests

**Using JUnit Console (no Maven):**
```bash
# Compile main
javac -d target/classes --release 17 src/main/java/ics202/*.java \
  src/main/java/ics202/lab01/*.java src/main/java/ics202/lab04/*.java \
  src/main/java/ics202/lab08/*.java

# Compile tests (requires lib/junit-platform-console-standalone-1.10.2.jar)
javac -d target/test-classes -cp "target/classes:lib/junit-platform-console-standalone-1.10.2.jar" \
  --release 17 src/test/java/ics202/*.java

# Run tests
java -jar lib/junit-platform-console-standalone-1.10.2.jar \
  --class-path target/classes:target/test-classes --scan-class-path
```

**Using Maven (when available):**
```bash
mvn test
# or
./mvnw test
```

---

## Post–Modern Java Refactor Run

(After applying lambdas, `var`, `Arrays.fill`, switch expressions, higher-order `forEach`, etc.)

| Metric | Value |
|--------|--------|
| **Tests found** | 92 |
| **Tests passed** | 92 |
| **Tests failed** | 0 |
| **Status** | **All pass** |

### Modern Java features applied

- **`var`** – local variable type inference in `AbstractContainer`, `AbstractGraph`, `AbstractTree`, `BinaryTree`, etc.
- **Lambdas** – `AbstractContainer.toString()` uses `forEach(obj -> { ... })` instead of anonymous `AbstractVisitor`.
- **Higher-order function** – `AbstractContainer.forEach(Consumer<Object>)` builds a `Visitor` from a lambda.
- **Switch expression** – `BinaryTree.getSubtree(int i)` uses `return switch (i) { case 0 -> getLeft(); case 1 -> getRight(); default -> throw ... }`.
- **`Arrays.fill`** – replaces manual `for` loops for initializing boolean/int arrays in `AbstractGraph`.
- **Pattern matching** – `AbstractGraph.toString()` uses `if (obj instanceof Vertex v)`.
