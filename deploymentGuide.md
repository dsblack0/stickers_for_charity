**Deployment is necessary so that our website is publicly accessible on the internet. Our team will use raspberry pi to deploy our project.**

## Updating our project
### Policy:
**Deployment manager will update the website at least once a week (on Sunday), but usually every 3 days in order to keep up with commits**
 - All branches will be directed to main repo, so only main repository will be deployed
 - 

### Steps:
1. Cd into stickers_for_charity then pull from Github using command `git pull` 
2. Rebuild java application using command `./mvnw package`
3. Restart service file using `sudo systemctl stop stickers_for_charity`, `sudo systemctl start stickers_for_charity`, `sudo systemctl enable stickers_for_charity`
4. Restart nginx file using `sudo systemctl  restart nginx`
  
--------------------------------------------------------------------
**In case deployment manager is unable to deploy, team members can follow these steps**
## Preparing the Raspberrypi
### Requirements:
 - Raspberrypi 4 with power supply and heat sinks preferably
 - 32 GB Micro SD card (16 GB worked for me tbh)
 - MicroSD to SD card adapter OR MicroSD USB reader (basically needed so that computer can read and download stuff on the microSD card)
 - Ethernet cable 
 - Install [PuTTY](https://www.chiark.greenend.org.uk/~sgtatham/putty/latest.html) on your computer 
### Setup:
1. Place the MicroSD card into the card adapter and plug it into your computer (an open folder may pop up)
2. Download and run [Raspberry Pi Image](https://www.raspberrypi.com/software/) (aka NOOBS) onto your MicroSD Card by selecting "Raspberry Pi OS" for the operating system and selecting your MicroSD card's folder for the storage when you run the Imager.
3. To configure SSH and wifi: In the boot directory on the MicroSD card, create an empty folder and name it "ssh". Then create a file called "wpa_supplicant.conf". Open this file with text editor, paste in (Note: replace the words in quotation marks with your wifi's name and password):   
country=US  
ctrl_interface=DIR=/var/run/wpa_supplicant GROUP=netdev  
network={  
ssid="Enter_YOUR_NETWORK_NAME"  
psk="Enter_YOUR_PASSWORD"  
key_mgmt=WPA-PSK  
}
4. Plug in the MicroSD card into the raspberry pi
5. Connect your computer to your raspberrypi using an ethernet cable
>  - Once plugged in, go to "View Network Connections" in the control panel, right click the Wifi and click "Status", click "Properties", go to "Sharing" tab on the top, check the box "Allow other network users to connect through this computer's Internet connection", select the Ethernet that the raspberrypi is connected to in the dropdown menu, Press OK
>  - Then right click the Ethernet and click "Status" --> "Properties", scroll through the menu until "Internet Protocol Version 4" and check it, if you click on its "Properties", a page should pop up with "Use the following IP address" checked. Restart your computer for changes to work.
6. Enable SSH on raspberrypi
>  - Open PuTTY and input the raspberrypi's ip address (found by using terminal command `ping raspberrypi`) as the host name, the port is 22, the connection type is SSH, then click "Open".
>  - A terminal should open that wants a username and password. The default login for raspberrypi is username: "pi" and password: "raspberry".
>  - Type the command `sudo raspi-config` and a menu should appear
>  - Use arrow keys and enter to go select "Interfacing Options" --> "SSH" then select Yes to enable SSH.
We're done setting up the Raspberry pi! (You can now unplug the ethernet cable, the raspberrypi will still be connected )
--------------------------------------------------------------------
## Configuring the Raspberry pi
### Requirements:
 - Finish the previous steps
 - Install [VNC Viewer](https://www.realvnc.com/en/connect/download/viewer/raspberrypi/)
### Configure:
1. Open VNC Viewer, click "File" --> "New Connection", enter raspberrypi's ip address for the VNC Server and press OK, a desktop interface should appear
2. Open the terminal and type in `sudo apt update` and `sudo apt upgrade` to setup java, then type `sudo apt install default-jre default-jdk maven` to install Java Runtime, Java Development Kit, and Maven.
3. Then clone and run the repository: `cd` --> `git clone https://github.com/dsblack0/stickers_for_charity.git` --> `cd stickers_for_charity` --> `sudo mvn spring-boot:run`
4. Your code should be running now. To test, click on the browser in the pi's desktop and type in "localhost:8080"
5. Create a service that runs the repository forever on nginx
>  - Create a new file with `sudo nano /etc/systemd/system/stickers_for_charity.service` and paste in the code:  
[Unit]  
Description=Java  
After=network.target  
// linebreak  
[Service]  
User=ubuntu  
Restart=always  
ExecStart=mvn spring-boot:run  
// line break  
[Install]  
WantedBy=multi-user.target  
>  - Start and check the service with `sudo systemctl start stickers_for_charity` and `sudo systemctl status stickers_for_charity` and `sudo systemctl enable stickers_for_charity`
>  - We then configure nginx with `sudo nano /etc/nginx/sites-available/stickers_for_charity` and paste in the code:  
server {  
    listen 80;  
    server_name stickersforcharity.cf;  
    location / {  
        proxy_pass http://localhost:8080;  
    }  
}  
>  - test and finish configuration with `sudo ln -s /etc/nginx/sites-available/stickers_for_charity /etc/nginx/sites-enabled` then `sudo nginx -t` then `sudo systemctl restart nginx`
--------------------------------------------------------------------
## Connect to the internet
1. Go to freenom.com and log in to account (credentials are shared)
2. In the dropdown menu, click Services --> My Domains --> Manage Domain in the stickersforcharity.cf row --> Manage Freenom DNS
3. In the modify records table, change the IP address under target to match your public IP address, be sure to Save Changes
4. In order to port forward, log into your router (oftentimes you can type your router's IP address into search and website will appear)
5. Navigate to the port forwarding settings and add a new service called HTTP with port 80 for external and internal port, protocol with both TCP and UDP, and use your computer's private IP address. Do the same thing with SSH and VNC Viewer following the image below:
![](https://github.com/nighthawkcoders/nighthawk_csp/blob/master/static/assets/portforward.png)  
**The website should now be deployed! Note: the website doesn't work on your home wifi that you deployed on**
