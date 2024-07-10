# NHS-Tests-Dev
#!/usr/bin/bash
echo "############ RUNNING BUILD ############"
pwd

mysql -u root -pcomsc < src/main/resources/schema.sql
mysql -u root -pcomsc < src/main/resources/data.sql
/opt/gradle/gradle-8.0.2/bin/gradle clean
/opt/gradle/gradle-8.0.2/bin/gradle build
/opt/gradle/gradle-8.0.2/bin/gradle jacocoTestReport
/opt/gradle/gradle-8.0.2/bin/gradle bootjar

# NHS-Deployment-Dev
cp -r "/var/lib/jenkins/workspace/NHS-Tests-Dev/*" ./
cd build/libs
java -jar NHS_project-0.0.1-SNAPSHOT.jar --server.port=8080

# NHS-Tests-Prod
#!/usr/bin/bash
echo "############ RUNNING BUILD ############"
pwd

mysql -u root -pcomsc < src/main/resources/schema.sql
mysql -u root -pcomsc < src/main/resources/data.sql
/opt/gradle/gradle-8.0.2/bin/gradle clean
/opt/gradle/gradle-8.0.2/bin/gradle build
/opt/gradle/gradle-8.0.2/bin/gradle jacocoTestReport
/opt/gradle/gradle-8.0.2/bin/gradle bootjar

# NHS-Deployment-Prod
- create docker file and upload to repo
- create live instance which will pull the dockerfile and run