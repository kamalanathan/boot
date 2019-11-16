mvn clean install package -Dmaven.test.skip=true -Ddocker.skip=false
mvn -f ./e2s_app/pom.xml dockerfile:push -Ddocker.skip=false
mvn -f ./e2sasyncexec/pom.xml dockerfile:push -Ddocker.skip=false
