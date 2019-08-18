#echo kamal | sudo -S docker run --add-host=master:192.168.1.3 -p 6072:6072 cloud-config &
echo kamal | sudo -S docker start -ai cloud-config
#echo kamal | sudo -S docker run -t -d --name cloud-config --add-host=master:192.168.1.3 -p 6072:6072  cloud-config &

