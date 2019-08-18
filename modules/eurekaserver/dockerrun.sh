#echo kamal | sudo -S docker run -t -d --name docker-eurekaserver -p 6070:6070 docker-eurekaserver &
echo kamal | sudo -S docker start -ai docker-eurekaserver &
