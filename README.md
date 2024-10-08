# modem-zxyel-c3000z

This application is used to connect and disconnect Zxyel C3000Z modem to the internet.

This app should run in the same network as the modem. Multicast DNS is used to broadcast the endpoint details of this app.

If you are running linux based system, `sudo make publish` will install this app as a service.

You may check the logs by running `sudo journalctl -u modem-zyxel-c3000z`