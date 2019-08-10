mvn clean install
scp target/*.jar kamal@slave02:/home/kamal/Documents/workspace/DemandSupplyFE
scp target/*.jar kamal@slave04:/home/kamal/Documents/workspace/DemandSupplyFE

curl http://slave02:6068/shutdown
curl http://slave04:6068/shutdown
ssh kamal@slave02 'java -jar /home/kamal/Documents/workspace/DemandSupplyFE/demandsupplyfe-0.0.1-SNAPSHOT.jar &'
ssh kamal@slave04 'java -jar /home/kamal/Documents/workspace/DemandSupplyFE/demandsupplyfe-0.0.1-SNAPSHOT.jar &'
