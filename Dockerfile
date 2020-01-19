# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM openjdk:8
VOLUME C:/docker/tmp
ADD target/cocktails-0.0.1-SNAPSHOT.jar cocktails.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cocktails.jar"]