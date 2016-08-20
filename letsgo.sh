# preparation
export DOCKER_HOST_IP=$(docker-machine ip) 

docker-compose kill
docker-compose rm -f

# remove dangling images
docker images --quiet --filter=dangling=true | xargs --no-run-if-empty docker rmi

mvn clean package

cd spring-react/
mvn docker:build

cd spring-rest/
mvn docker:build

docker-compose up -d
sleep 20

cd ../performance-test
mvn clean gatling:test -Dhost=$DOCKER_HOST_IP -Drestport=9001 -Dreactport=9000





