#/bin/bash
docker build --tag spi_img0 .
docker run --name spi_ctn0 spi_img0
docker logs spi_ctn0 > output.log
docker rm spi_ctn0
docker rmi spi_img0