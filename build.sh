export MVN_HOME=/Users/ivan/install/apache-maven-3.8.7
$MVN_HOME/bin/mvn clean install

docker build --tag springbootaws-docker .
docker tag springbootaws-docker:latest 497325665583.dkr.ecr.eu-west-2.amazonaws.com/spring-boot-aws

aws ecr get-login-password --region eu-west-2 | docker login --username AWS --password-stdin 497325665583.dkr.ecr.eu-west-2.amazonaws.com
#aws ecr create-repository --repository-name spring-boot-aws
docker push 497325665583.dkr.ecr.eu-west-2.amazonaws.com/spring-boot-aws
