## Minikube Instructions

- Make sure Docker app is signed in

- Start minikube
```bash
  minikube start
```

- Apply deployment and service

- Start tunnel to assign IP to service in load balancer mode
```bash
  minikube tunnel
```

- Consume endpoint
```bash
  # Get external IP of service
  kubectl get svc
  # Curl it
```