FROM ubuntu:20.04

RUN apt-get update && apt-get install -y openjdk-17-jdk maven

COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

CMD ["sh", "entrypoint.sh"]
