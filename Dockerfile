FROM openjdk:8-jdk-alpine
EXPOSE 8080/udp
EXPOSE 8080/tcp
#Agregamos el codigo fuente que se estara corriendo en el container por propositos de depuracion
ENV ZUUL_DIR=/home/alexl/zuul-server
WORKDIR $ZUUL_DIR
#VOLUME $ZUUL_DIR
ADD . .
COPY . .
RUN keytool printf 'changeit\nchangeit\ny\n' | keytool -importcert -file src/main/resources/static/equifax-certificate.cer -alias equifax-certificate -trustcacerts
#agregamos el ejecutable que se correra
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} zuul-server.jar
ENTRYPOINT ["java","-jar","zuul-server.jar"]