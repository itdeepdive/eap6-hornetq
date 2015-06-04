# eap6-hornetq

Prerequisite:
1) Download Jboss-eap-6.4
2) cd JBOSS_HOME/bin/client
3) Execute the Below command to install the jboss client jar to local maven repo
mvn install:install-file -Dfile=jboss-client.jar -DgroupId=org.jboss -DartifactId=jboss-client -Dversion=6.4.0 -Dpackaging=jar

Steps:
 
1) Download the project from git repo eap6-hornetq  

 cp standalone-full.xml to JBOSS_HOME/standalone/configuration/.
 
2) Execute mvn clean install

3) cp target/jms-0.0.1-SNAPSHOT.war JBOSS_HOME/standalone/deployments/.

Testing:


curl http://localhost:8080/jms-0.0.1-SNAPSHOT/greeting

The above command will post Hello world Message to Z topic.


Now Look at the JBOSS Logs.This will show the listeners that are trigged after posting it to Z topic.

Ideally we should see Three log messages from three listeners




