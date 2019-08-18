FROM openjdk:8
ADD eurekaserver/target/eurekaserver-0.0.1-SNAPSHOT.jar eurekaserver-0.0.1-SNAPSHOT.jar
ADD cloud-config/target/cloud-config-0.0.1-SNAPSHOT.jar cloud-config-0.0.1-SNAPSHOT.jar
ADD DemandSupplyFE/target/demandsupplyfe-0.0.1-SNAPSHOT.jar demandsupplyfe-0.0.1-SNAPSHOT.jar
ADD matcher/target/matcher-0.0.1-SNAPSHOT.jar matcher-0.0.1-SNAPSHOT.jar
ADD oauth2/target/oauth2-0.0.1-SNAPSHOT.jar oauth2-0.0.1-SNAPSHOT.jar
ADD offers/target/offers-0.0.1-SNAPSHOT.jar offers-0.0.1-SNAPSHOT.jar
ADD profiles/target/oauth2-0.0.1-SNAPSHOT.jar profiles-0.0.1-SNAPSHOT.jar
ADD zuulfilter/target/zuulfilter-0.0.1-SNAPSHOT.jar zuulfilter-0.0.1-SNAPSHOT.jar

EXPOSE 6069 6070 6071 6072 6073 6074 6075
ENTRYPOINT ["java", "-jar", "eurekaserver-0.0.1-SNAPSHOT.jar"]
