FROM maven:alpine  

WORKDIR /usr/src/app  

COPY . ./  

RUN mvn install  

ENV ACME_ACCESS_KEY_ID adsfasd
ENV ACME_SECRET_ACCESS_KEY asdfasdf

EXPOSE 8081  

CMD java -jar target/acmefileaccess-1.0.0.jar