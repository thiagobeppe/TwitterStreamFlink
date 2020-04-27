#!/bin/bash
figlet Docker!;
sudo sysctl -w vm.max_map_count=262144 ;
docker-compose up -d;
gnome-terminal  -- bash -c "figlet -c Kafka;cd twitteFlink; sbt \"runMain com.github.example.kafka.TwitterStreamProducer\"";

echo "Configurando ElasticSearch"
for i in 1 2 3 4 5; do
	echo "Iniciando em $i";
	sleep 1;
done
gnome-terminal  -- bash -c "figlet -c Consumidor Flink;cd twitteFlink; sbt \"runMain com.github.example.flinkStreamApplication\""
