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
sleep 10s
#Config
curl -X POST http://master:6072/actuator/shutdown
sleep 10s
#eurekaserver
curl -X POST http://master:6070/actuator/shutdown