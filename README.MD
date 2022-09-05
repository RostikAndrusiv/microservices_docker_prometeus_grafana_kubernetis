 # local setup
 Docker and java11 is required  
 Build micro-collector, micro-recipient, micro-sender modules     
 Build docker containers for micro-collector, micro-recipient, micro-sender
   - `docker build . -t micro-collector`
   - `docker build . -t micro-sender`
   - `docker build . -t micro-recipient`

 Install loki driver `docker plugin install grafana/loki-docker-driver:latest --alias loki --grant-all-permissions`  
 run `docker volume create --name=grafana-data`  
 Run application `docker-compose up`    
 micro-sender is available on http://localhost:9000    
 RabbitMQ is available on http://localhost:15672    
 Grafana is available on http://localhost:3000  
 Use http://host.docker.internal:9090 URL to create Prometheus dashbord in grafana