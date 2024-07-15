# Jenkins setup:
- start terraform instance using scripts provided
- setup admin account in jenkins, using password from initialization log
- install jacoco and gitlab plugins
- create keypair 
- put public key in gitlab repo settings, private key in jenkins credentials for each job
- set docker_pass and c23097744_pass credentials in jenkins
- create each of the following build jobs:

## NHS-Tests-Dev - triggered by test branch webhook
#!/usr/bin/bash
echo "############ RUNNING BUILD ############"
pwd

mysql -u root -pcomsc < src/main/resources/schema.sql
mysql -u root -pcomsc < src/main/resources/data.sql
/opt/gradle/gradle-8.0.2/bin/gradle clean
/opt/gradle/gradle-8.0.2/bin/gradle build
/opt/gradle/gradle-8.0.2/bin/gradle jacocoTestReport
/opt/gradle/gradle-8.0.2/bin/gradle bootjar

## NHS-Deployment-Dev - triggered by NHS-Tests-Dev
cp -r "/var/lib/jenkins/workspace/NHS-Tests-Dev/." ./
mysql -u root -pcomsc -e "DROP DATABASE IF EXISTS nhsdb; CREATE DATABASE nhsdb;"
mysql -u root -pcomsc < src/main/resources/schema.sql
mysql -u root -pcomsc < src/main/resources/data.sql
cd build/libs
java -jar NHS_project-0.0.1-SNAPSHOT.jar --server.port=8080

## NHS-Tests-Prod - triggered by production branch webhook
#!/usr/bin/bash
echo "############ RUNNING BUILD ############"
pwd

mysql -u root -pcomsc < src/main/resources/schema.sql
mysql -u root -pcomsc < src/main/resources/data.sql
/opt/gradle/gradle-8.0.2/bin/gradle clean
/opt/gradle/gradle-8.0.2/bin/gradle build
/opt/gradle/gradle-8.0.2/bin/gradle jacocoTestReport
/opt/gradle/gradle-8.0.2/bin/gradle bootjar

## NHS-Deployment-Prod - triggered by NHS-Tests-Prod
#!/usr/bin/bash
echo "############ RUNNING BUILD ############"
pwd

BUILD_NUMBER=${BUILD_NUMBER}
GIT_COMMIT=$(git --git-dir=/var/lib/jenkins/workspace/NHS-Tests-Prod/.git rev-parse --short HEAD)
VERSION="${BUILD_NUMBER}_${GIT_COMMIT}"

echo "BUILDING IMAGE VERSION $VERSION with format: [jenkins build no.]_[git commit hash]"
rsync -av --exclude='.git' "/var/lib/jenkins/workspace/NHS-Tests-Prod/." ./
/opt/gradle/gradle-8.0.2/bin/gradle clean bootjar
docker build -t nhs-project:$VERSION .

echo "PUSHING IMAGE TO DOCKERHUB"
docker login -u orebenson -p $docker_pass
docker tag nhs-project:$VERSION orebenson/nhs-project:$VERSION
docker push orebenson/nhs-project:$VERSION

echo "RUNNING TERRAFORM DEPLOYMENT"
cd Deployment
export OS_AUTH_URL=https://cscloud.cf.ac.uk:5000
export OS_PROJECT_ID=bfa4a12c0f5e48b0911fbb60f85d542f
export OS_PROJECT_NAME="c23097744"
export OS_USER_DOMAIN_NAME="cardiff.ac.uk"
if [ -z "$OS_USER_DOMAIN_NAME" ]; then unset OS_USER_DOMAIN_NAME; fi
export OS_PROJECT_DOMAIN_ID="3693afdd0603423a9e8984fd32df7a0c"
if [ -z "$OS_PROJECT_DOMAIN_ID" ]; then unset OS_PROJECT_DOMAIN_ID; fi
unset OS_TENANT_ID
unset OS_TENANT_NAME
export OS_USERNAME="c23097744"
export OS_PASSWORD=$c23097744_pass
export OS_REGION_NAME="RegionOne"
if [ -z "$OS_REGION_NAME" ]; then unset OS_REGION_NAME; fi
export OS_INTERFACE=public
export OS_IDENTITY_API_VERSION=3

if [ -d Terraform_Docker ]; then
cd Terraform_Docker
/usr/local/bin/terraform destroy -auto-approve
rm *.*
cd ../
fi

cd Terraform_Docker
/usr/local/bin/terraform init
/usr/local/bin/terraform plan
/usr/local/bin/terraform apply -auto-approve
