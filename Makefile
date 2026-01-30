# Simple-Data-structures build
JAVAC = javac
JAVAC_FLAGS = -d build --release 17
SOURCES = $(wildcard *.java)

.PHONY: all build clean

all: build

build:
	@mkdir -p build
	$(JAVAC) $(JAVAC_FLAGS) $(SOURCES)

clean:
	rm -rf build
