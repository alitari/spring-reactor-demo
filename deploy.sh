# docker host
export DOCKER_HOST_IP=$(docker-machine ip) 



# remove container
export DOCKER_HOST_IP=$(docker-machine ip)docker rm -f react rest admin

docker run -d -p 8123:8123 enmobile/docker-spring-boot-admin

# remove dangling images
docker images --quiet --filter=dangling=true | xargs --no-run-if-empty docker rmi

mvn clean package

cd spring-react/
mvn docker:build
docker run -d --name react -p 9000:9000 alitari/spring-react
cd ..


cd spring-rest/
mvn docker:build
docker run -d --name rest -p 9001:9001 -e ADMIN_HOST=192.168.99.100 alitari/spring-rest

cd ../performance-test
mvn clean gatling:test -Dhost=$(docker-machine ip) -Drestport=9001 -Dreactport=9000





