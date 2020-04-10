## Create jenkins-master image

`docker build . -t jenkins-master`

## Create jenkins-master container

Note: `/run/docker.sock` may be `/var/run/docker.sock` depending on the OS.

```
docker run \
    --restart=always \
    -d \
    -p 8888:8080 \
    -p 55555:5000 \
    -v /run/docker.sock:/var/run/docker.sock \
    -v jenkins_home:/var/jenkins_home \
    --name jenkins-master \
    jenkins-master
```

## Usage

Access [http://localhost:8888](http://localhost:8888) with **root/root**
