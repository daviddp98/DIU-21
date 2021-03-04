FROM daviddp1998/tutorialesapp:1
COPY target/Tutoriales-0.0.1-SNAPSHOT.jar /demo-tutoriales.jar

ENTRYPOINT ["java","-jar", "/demo-tutoriales.jar"]
