FROM frolvlad/alpine-oraclejdk8:slim
ADD spring-rest*.jar app.jar
RUN sh -c 'touch /app.jar'
ADD start.sh /start.sh
CMD sh -C '/start.sh'
