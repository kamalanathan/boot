cd ./eurekaserver
mvn clean install
cd ./cloud-config
mvn clean install
cd ./matcher
mvn clean install
cd ./DemandSupplyFE
mvn clean install
cd ./profiles
mvn clean install
cd ./zuulfilter
mvn clean install

#Zuul
curl -X POST http://master:6073/actuator/shutdown
#Profiles
curl -X POST http://slave02:6071/actuator/shutdown
curl -X POST http://slave04:6071/actuator/shutdown
#Matcher
curl -X POST http://master:6069/actuator/shutdown
#DemandSupplyFE
curl -X POST http://slave02:6068/actuator/shutdown
curl -X POST http://slave04:6068/actuator/shutdown
#Config
curl -X POST http://master:6072/actuator/shutdown
#eurekaserver
curl -X POST http://master:6070/actuator/shutdown

cd ..
scp ./profiles/target/*.jar kamal@slave02:/home/kamal/Documents/workspace/profiles
scp ./profiles/target/*.jar kamal@slave04:/home/kamal/Documents/workspace/profiles 

scp ./DemandSupplyFE/target/*.jar kamal@slave02:/home/kamal/Documents/workspace/DemandSupplyFE
scp ./DemandSupplyFE/target/*.jar kamal@slave04:/home/kamal/Documents/workspace/DemandSupplyFE

java -jar ./eurekaserver/target/eurekaserver-0.0.1-SNAPSHOT.jar &
sleep 1m
java -jar ./cloud-config/target/cloud-config-0.0.1-SNAPSHOT.jar &
sleep 1m
java -jar ./matcher/target/matcher-0.0.1-SNAPSHOT.jar &

ssh kamal@slave02 'java -jar /home/kamal/Documents/workspace/DemandSupplyFE/demandsupplyfe-0.0.1-SNAPSHOT.jar &'
ssh kamal@slave04 'java -jar /home/kamal/Documents/workspace/DemandSupplyFE/demandsupplyfe-0.0.1-SNAPSHOT.jar &'

ssh kamal@slave02 'java -jar /home/kamal/Documents/workspace/profiles/target/profiles-0.0.1-SNAPSHOT.jar &'
ssh kamal@slave04 'java -jar /home/kamal/Documents/workspace/profiles/target/profiles-0.0.1-SNAPSHOT.jar &'

java -jar ./zuulfilter/target/zuulfilter-0.0.1-SNAPSHOT.jar &
