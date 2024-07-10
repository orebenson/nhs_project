#!/usr/bin/bash
whoami
lsb_release -a

echo "update logging configuration..."
sudo sh -c "echo '*.info;mail.none;authpriv.none;cron.none /dev/ttyS0' >> /etc/rsyslog.conf"
sudo systemctl restart rsyslog

mkdir -p /home/debian
cd /home/debian
echo in directory $PWD

sudo apt-get update && sudo apt-get upgrade
sudo apt update && sudo apt upgrade


echo "######################## Installing MariaDB ########################"
# sudo yum install mysql -y

# sudo apt remove mariadb-server -y
# sudo apt purge mariadb-server -y
# sudo apt autoremove -y

sudo apt install curl -y
curl -LsS https://r.mariadb.com/downloads/mariadb_repo_setup | sudo bash -s -- --mariadb-server-version="mariadb-10.11.2"
sudo apt update
sudo apt install mariadb-server -y
sudo systemctl start mariadb
sudo systemctl status mariadb
sudo systemctl enable mariadb

# echo "creating mysql_secure_installation.txt..."
# touch mysql_secure_installation.txt
# cat <<EOF > mysql_secure_installation.txt

# n
# Y
# comsc
# comsc
# Y
# Y
# Y
# Y
# Y
# EOF

# echo "running mysql_secure_installation..."
# sudo mysql_secure_installation < mysql_secure_installation.txt
sudo mysql -u root -e "UPDATE mysql.user SET plugin='mysql_native_password' WHERE User='root'";
sudo mysql -u root -e "USE mysql; UPDATE user SET password=PASSWORD('comsc') WHERE User='root' AND Host = 'localhost'; FLUSH PRIVILEGES;"
sudo mysql -u root -e "GRANT ALL PRIVILEGES on *.* TO root@localhost IDENTIFIED BY 'comsc' WITH GRANT OPTION;"


echo "######################## Installing Tools ########################"
sudo apt-get install unzip -y
sudo apt-get install wget -y
sudo apt-get install git -y


echo "######################## Installing Java 17 ########################"
# Install using .deb
wget https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.deb
sudo apt install ./jdk-17_linux-x64_bin.deb -y
sudo apt install default-jre -y

# cat <<EOF | sudo tee /etc/profile.d/jdk.sh
# export JAVA_HOME=/usr/lib/jvm/jdk-17-oracle-x64/
# export PATH=\$PATH:\$JAVA_HOME/bin
# EOF
# source /etc/profile.d/jdk.sh

## Install using .tar.gz
# wget https://download.java.net/java/GA/jdk17/0d483333a00540d886896bac774ff48b/35/GPL/openjdk-17_linux-x64_bin.tar.gz
# tar zxvf openjdk-17_linux-x64_bin.tar.gz
# sudo mv jdk-17 /usr/local/
# export JAVA_HOME=/usr/local/jdk-17
# export PATH=$PATH:$JAVA_HOME/bin
java --version


echo "######################## Installing gradle ########################"
wget https://services.gradle.org/distributions/gradle-8.0.2-bin.zip
sudo mkdir /opt/gradle
sudo unzip -d /opt/gradle gradle-8.0.2-bin.zip
export PATH=$PATH:/opt/gradle/gradle-8.0.2/bin
gradle -v


echo "######################## Installing Terraform ########################"
cd /home/debian
wget https://releases.hashicorp.com/terraform/1.1.5/terraform_1.1.5_linux_amd64.zip
unzip terraform_1.1.5_linux_amd64.zip
sudo mv terraform /usr/local/bin/
terraform -v


echo "######################## Installing Docker ########################"
curl -fsSL https://get.docker.com -o get-docker.sh
sh get-docker.sh
sudo groupadd docker
sudo usermod -aG docker jenkins
docker --version


echo "######################## Installing Selenium Runner ########################"
# install chrome
sudo apt update
wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
sudo apt install ./google-chrome-stable_current_amd64.deb -y

# install firefox
sudo install -d -m 0755 /etc/apt/keyrings
wget -q https://packages.mozilla.org/apt/repo-signing-key.gpg -O- | sudo tee /etc/apt/keyrings/packages.mozilla.org.asc > /dev/null
gpg -n -q --import --import-options import-show /etc/apt/keyrings/packages.mozilla.org.asc | awk '/pub/{getline; gsub(/^ +| +$/,""); if($0 == "35BAA0B33E9EB396F59CA838C0BA5CE6DC6315A3") print "\nThe key fingerprint matches ("$0").\n"; else print "\nVerification failed: the fingerprint ("$0") does not match the expected one.\n"}'
echo "deb [signed-by=/etc/apt/keyrings/packages.mozilla.org.asc] https://packages.mozilla.org/apt mozilla main" | sudo tee -a /etc/apt/sources.list.d/mozilla.list > /dev/null
echo '
Package: *
Pin: origin packages.mozilla.org
Pin-Priority: 1000
' | sudo tee /etc/apt/preferences.d/mozilla
sudo apt-get update && sudo apt-get install firefox

# install nvm package manager
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"
[ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"
nvm install 20
node --version
npm -version

# install further dependencies
sudo apt install jq -y
npm install -g selenium-webdriver
npm install -g selenium-side-runner
npm install -g geckodriver
npm install -g chromedriver

selenium-side-runner --version
geckodriver --version
chromedriver --version


echo "######################## Installing JMeter ########################"
curl -O https://archive.apache.org/dist/jmeter/binaries/apache-jmeter-5.5.zip
sudo mkdir /opt/jmeter
sudo unzip -d /opt/jmeter apache-jmeter-5.5.zip
export PATH=$PATH:/opt/jmeter/apache-jmeter-5.5/bin
jmeter --version


echo "######################## Installing Jenkins ########################"
sudo wget -O /usr/share/keyrings/jenkins-keyring.asc \
  https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt-get update
sudo apt-get install jenkins
jenkins --version

# Setting up gitlab
echo "######################## installing gitlab server key ########################"
echo "(Has to be added to the jenkins user home (~) dir )"
mkdir /var/lib/jenkins/.ssh
sudo touch /var/lib/jenkins/.ssh/known_hosts
sudo ssh-keyscan git.cardiff.ac.uk >> /var/lib/jenkins/.ssh/known_hosts
sudo chmod 644 /var/lib/jenkins/.ssh/known_hosts

# sudo systemctl stop jenkins
# sudo sed -i 's/HTTP_PORT=8080/HTTP_PORT=8081/g' /etc/default/jenkins
# sudo sed -i '/#JAVA_ARGS="-Djava.awt.headless=true"/s/#//g' /etc/default/jenkins
# sudo systemctl restart jenkins
sudo sed -i 's/JENKINS_PORT=8080/JENKINS_PORT=8081/g' /usr/lib/systemd/system/jenkins.service
sudo systemctl daemon-reload
sudo systemctl restart jenkins.service

# Starting Jenkins after config
# sudo systemctl start jenkins
sudo systemctl status jenkins
sudo systemctl enable jenkins

