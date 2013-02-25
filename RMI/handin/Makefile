
.SUFFIXES: .java .class 
SOURCES = ../src/*/*.java
CLASSES = ./*.class
JFLAGS = -g -classpath .

.java.class:
	javac $(JFLAGS) $*.java -d ./

default:
	javac -classpath . $(SOURCES) -d ./

all:
	javac -classpath . $(SOURCES) -d ./

demo:
	@echo 'Please open multiple terminal (e.g.1 master 2 slave)'
	@echo 'Usage: make master'
	@echo 'Usage: make masterport PORT=<port num>'
	@echo 'Usage: make slave NAME=<master name>'
	@echo 'Usage: make slaveport NAME=<master name> MP=<master port num> SP=<slave port num>'
	@echo 'Usage when running process: GzipProcess data/test.txt data/r1.gz'
	@echo 'Usage when running process: EdgeProcess data/img.jpg data/i1.jpg'

client: 
	java testzip.ZipCodeClient localhost 4435 ZipCodeServer ./data.txt

reg:
	java registry.RMIRegistry 4435

server:
	java ror.RMIServer testzip.ZipCodeServerImpl localhost 4435 ZipCodeServer

clean:
	rm $(CLASSES)

doc: $(SOURCES)
	javadoc -author $(SOURCES)


