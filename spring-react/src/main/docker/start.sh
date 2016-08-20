echo $DOCKER_HOST
java -Djava.security.egd=file:/dev/./urandom -DDOCKER_TLS_VERIFY=1 -DDOCKER_HOST=$DOCKER_HOST -DDOCKER_CERT_PATH=/certs -jar /app.jar 
