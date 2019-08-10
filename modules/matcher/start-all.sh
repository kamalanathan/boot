scp target/*.jar kamal@slave02:/home/kamal/Documents/workspace/matcher
scp target/*.jar kamal@slave04:/home/kamal/Documents/workspace/matcher

ssh kamal@slave04 'java -jar /home/kamal/Documents/workspace/matcher/matcher-0.0.1-SNAPSHOT.jar'
ssh kamal@slave04 'java -jar /home/kamal/Documents/workspace/matcher/matcher-0.0.1-SNAPSHOT.jar'
