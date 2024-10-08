define SERVICE_CREATE
[Unit]
Description=Modem Zxyel C3000Z Controller
After=syslog.target

[Service]
User=root
ExecStart=/var/modem-zyxel-c3000z/modem-zyxel-c3000z.jar
SuccessExitStatus=143
TimeoutStopSec=10
Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target
endef
export SERVICE_CREATE

publish:
	mvn clean install
	mkdir -p /var/modem-zyxel-c3000z
	cp target/modem-zyxel-c3000z-0.0.1-SNAPSHOT.jar /var/modem-zyxel-c3000z/modem-zyxel-c3000z.jar
	rm -f /etc/systemd/system/modem-zyxel-c3000z.service/
	touch /etc/systemd/system/modem-zyxel-c3000z.service
	echo "$$SERVICE_CREATE" > /etc/systemd/system/modem-zyxel-c3000z.service
	systemctl daemon-reload
	systemctl enable modem-zyxel-c3000z
	systemctl restart modem-zyxel-c3000z
	systemctl status modem-zyxel-c3000z