# haoyuanj_mesh_mono
Playing around with service mesh stack

Run the following command to build target
```bash
  make all
```
## Features

### Nginx ingress controller

For convenience's sake, update /etc/hosts so domain names defined in nginx resolve to `127.0.0.1`.

### Log stack (optional)

Implemented with fluentd and Elastic. Simply do not run Elastic
to disable (fluentd daemon will die failing to connect to Elastic).

Steps to set up:
1. Start Elastic locally ([guide](https://www.elastic.co/docs/solutions/search/run-elasticsearch-locally))
2. Create secrets for Elastic credentials:
   ```bash
   kubectl create secret generic elastic --from-literal=user={user} --from-literal=password={password}
   ```