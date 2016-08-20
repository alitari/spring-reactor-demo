echo $DOCKER_HOST
java -Djava.security.egd=file:/dev/./urandom -Dspring.boot.admin.client.url=http://localhost:9001 -jar /app.jar
