# Simple-Data-structures build and test
JAVAC = javac
JAVAC_FLAGS = -d target/classes --release 17
MAIN_SOURCES = src/main/java/ics202/*.java src/main/java/ics202/lab01/*.java src/main/java/ics202/lab04/*.java src/main/java/ics202/lab08/*.java
TEST_SOURCES = src/test/java/ics202/*.java
JUNIT_JAR = lib/junit-platform-console-standalone-1.10.2.jar
CLASSPATH = target/classes:target/test-classes
TEST_CLASSPATH = target/classes:$(JUNIT_JAR)

.PHONY: all build test clean

all: build

build:
	@mkdir -p target/classes
	$(JAVAC) $(JAVAC_FLAGS) $(MAIN_SOURCES)

test: build
	@mkdir -p target/test-classes lib
	@if [ ! -f $(JUNIT_JAR) ]; then echo "Downloading JUnit..."; curl -sL -o $(JUNIT_JAR) "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.2/junit-platform-console-standalone-1.10.2.jar"; fi
	$(JAVAC) -d target/test-classes -cp "$(TEST_CLASSPATH)" --release 17 $(TEST_SOURCES)
	java -jar $(JUNIT_JAR) --class-path $(CLASSPATH) --scan-class-path

clean:
	rm -rf build target
