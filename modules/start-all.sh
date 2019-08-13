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

cd ..
scp ./profiles/target/*.jar kamal@slave02:/home/kamal/Documents/workspace/profiles
scp ./profiles/target/*.jar kamal@slave04:/home/kamal/Documents/workspace/profiles 

scp ./DemandSupplyFE/target/*.jar kamal@slave02:/home/kamal/Documents/workspace/DemandSupplyFE
scp ./DemandSupplyFE/target/*.jar kamal@slave04:/home/kamal/Documents/workspace/DemandSupplyFE

scp ./matcher/target/*.jar kamal@slave02:/home/kamal/Documents/workspace/matcher
scp ./matcher/target/*.jar kamal@slave04:/home/kamal/Documents/workspace/matcher 

java -jar ./eurekaserver/target/eurekaserver-0.0.1-SNAPSHOT.jar &
java -jar ./cloud-config/target/cloud-config-0.0.1-SNAPSHOT.jar &


#java -jar ./matcher/target/matcher-0.0.1-SNAPSHOT.jar &

java -jar /home/kamal/Documents/workspace/profiles/profiles-0.0.1-SNAPSHOT.jar &
java -jar /home/kamal/Documents/workspace/matcher/matcher-0.0.1-SNAPSHOT.jar &
java -jar /home/kamal/Documents/workspace/DemandSupplyFE/demandsupplyfe-0.0.1-SNAPSHOT.jar &

ssh kamal@slave02 'java -jar /home/kamal/Documents/workspace/profiles/profiles-0.0.1-SNAPSHOT.jar &'
ssh kamal@slave04 'java -jar /home/kamal/Documents/workspace/profiles/profiles-0.0.1-SNAPSHOT.jar &'

ssh kamal@slave02 'java -jar /home/kamal/Documents/workspace/matcher/matcher-0.0.1-SNAPSHOT.jar &'
ssh kamal@slave04 'java -jar /home/kamal/Documents/workspace/matcher/matcher-0.0.1-SNAPSHOT.jar &'

ssh kamal@slave02 'java -jar /home/kamal/Documents/workspace/DemandSupplyFE/demandsupplyfe-0.0.1-SNAPSHOT.jar &'
ssh kamal@slave04 'java -jar /home/kamal/Documents/workspace/DemandSupplyFE/demandsupplyfe-0.0.1-SNAPSHOT.jar &'



java -jar ./zuulfilter/target/zuulfilter-0.0.1-SNAPSHOT.jar &
