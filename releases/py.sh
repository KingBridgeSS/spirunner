#/bin/bash
docker build --tag __img__ .
docker run --name __ctn__ __img__
docker logs __ctn__ > output.log
docker rm __ctn__
docker rmi __img__