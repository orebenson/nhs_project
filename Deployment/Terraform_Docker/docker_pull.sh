#!/usr/bin/bash

whoami

mkdir -p /home/debian
cd /home/debian
echo in directory $PWD

sudo apt-get update
sudo apt-get upgrade -y
sudo apt update
sudo apt upgrade -y

echo "..."
echo "..."
echo "######################## Installing Tools ########################"
sudo apt install curl -y
sudo apt-get install jq -y

echo "..."
echo "..."
echo "######################## Installing Docker ########################"
curl -fsSL https://get.docker.com -o get-docker.sh
sh get-docker.sh
docker --version

echo "..."
echo "..."
echo "######################## Running MariaDB in Container ########################"
sudo docker pull mariadb:10.11
sudo docker run -p 127.0.0.1:3306:3306 --name mdb -e MARIADB_ROOT_PASSWORD=comsc -d --network host mariadb:10.11

echo "..."
echo "..."
echo "######################## Pulling and running docker image nodes ########################"
# get all tags from the docker repo, pipe results into jq, filter, sort by jenkins build number, and return most recent one
LATEST_TAG=$(curl -s https://registry.hub.docker.com/v2/repositories/orebenson/nhs-project/tags/?page_size=100 | jq -r '.results | map(.name) | sort_by(split("_")[0] | tonumber) | .[-1]')
echo "PULLING IMAGE VERSION $LATEST_TAG with format: [jenkins build no.]_[git commit hash]"
sudo docker pull orebenson/nhs-project:$LATEST_TAG
sudo docker run -e SERVER_PORT=8081 -p 8081:8081 --name myapp1 -d --network host orebenson/nhs-project:$LATEST_TAG
sudo docker run -e SERVER_PORT=8082 -p 8082:8082 --name myapp2 -d --network host orebenson/nhs-project:$LATEST_TAG
sudo docker run -e SERVER_PORT=8083 -p 8083:8083 --name myapp3 -d --network host orebenson/nhs-project:$LATEST_TAG

echo "..."
echo "..."
echo "######################## Running nginx and configuring load balancer and compression ########################"
sudo apt install nginx -y
sudo cat /etc/nginx/nginx.conf > /home/debian/backup_nginx.txt
sudo rm /etc/nginx/nginx.conf

sudo sh -c 'cat <<EOF > /etc/nginx/nginx.conf
user www-data;
worker_processes auto;
pid /run/nginx.pid;
include /etc/nginx/modules-enabled/*.conf;

events {
    worker_connections 768;
}

http {

    sendfile on;
    tcp_nopush on;
    tcp_nodelay on;
    keepalive_timeout 65;
    types_hash_max_size 2048;
    
    include /etc/nginx/mime.types;
    default_type application/octet-stream;

    ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
    ssl_prefer_server_ciphers on;

    access_log /var/log/nginx/access.log;
    error_log /var/log/nginx/error.log;

    gzip on;

    include /etc/nginx/conf.d/*.conf;
    include /etc/nginx/sites-enabled/*;

    upstream nhs-project {
        ip_hash;
        server localhost:8081;
        server localhost:8082;
        server localhost:8083;
    }

    server {
        listen 8080;

        location / {
            proxy_pass http://nhs-project;
        }
    }
}
EOF'
sudo systemctl restart nginx

echo "..."
echo "..."
echo "..."
echo "..."
echo "@@@@@@@@@    @@@@@@@@     @@@@@     @@@@@@@@@   @@"
echo "@@@   @@@@  @@@    @@@  @@@@@@@@@  @@@    @@@   @@"
echo "@@@    @@@  @@@    @@@  @@@   @@@  @@@          @@"
echo "@@@    @@@  @@@    @@@  @@@   @@@  @@@@@@       @@"
echo "@@@    @@@  @@@    @@@  @@@   @@@  @@@@@@       @@"
echo "@@@    @@@  @@@    @@@  @@@   @@@  @@@          @@"
echo "@@@   @@@@  @@@    @@@  @@@   @@@  @@@    @@@   "
echo "@@@@@@@@@    @@@@@@@@    @@   @@    @@@@@@@@@   @@"
echo "..."
echo "..."
echo "..."
echo "..."
