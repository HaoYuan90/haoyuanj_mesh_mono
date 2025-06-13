# ChatService

Very simple Discord like chat service backend

## Running the app with mixer

```bash
mix run --no-halt
```

## Testing the app using CLI

Install websocat CLI tool

Get port number using with "docker ps" if service is created with "docker run".

```bash
  websocat -v ws://localhost:{port}/ws/chat
```

To test against serivce on Minikube, do

```bash
  websocat -v ws://haoyuanj-mesh.homelab/ws/chat
```

