**Deploy with Helm**
Start Minikube and deploy the Helm charts.
```shell
minikube start
helm install spring-boot-microservice ./helm/spring-boot-microservice
helm install prometheus ./helm/prometheus
helm install grafana ./helm/grafana
```

**Access Services**
Use minikube service to access the services.
```shell
minikube service spring-boot-microservice
minikube service prometheus
minikube service grafana
```