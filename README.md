# ms-auth

## :information_source: Information 

Development of two authentication projects in microservices, using two models: stateful and stateless. Auth represents an authentication API. Any represents any API, which will only have to validate the access token. The operating model of the applications will be shown below.

## Stateless vs Stateful

This table highlights the key differences between stateless and stateful systems:

| **Stateless**        | **Stateful**        |
|-----------------------|---------------------|
| No session           | Session-based       |
| No login             | Login functionality |
| No basket            | Basket functionality|
| Static content       | Dynamic content     |

---

Stateless systems are typically simpler and scale better, but stateful systems are more suitable for interactive and personalized applications.


## Stateless Model

![Stateless Authentication  JWT Token](https://github.com/user-attachments/assets/7e88b08d-57ab-49fc-9302-70eb6113542e)


## Stateful Model

![Stateful Authentication  Opaque Token](https://github.com/user-attachments/assets/e3a329a0-5ba5-4804-ac0e-f005d62c43cd)



## :rocket: Installation

![](https://img.shields.io/badge/Linux-FCC624?style=for-the-badge&logo=linux&logoColor=black)


```
git clone https://github.com/RamonBecker/ms-auth.git
```

![](https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white)
```
git clone https://github.com/RamonBecker/ms-auth.git
or install github https://desktop.github.com/ 
```

## 🔨 Docker

Before cloning the project, you will need to install docker on your operating system.

For windows, enter the following from the link:

```
https://docs.docker.com/desktop/windows/install/
```

For linux, follow the procedure below:
- Update your existing list of packages:

```
sudo apt update
```

- Install some prerequisite packages that let apt use packages over HTTPS:

```
sudo apt install apt-transport-https ca-certificates curl software-properties-common

```

- Add the GPG key to the official Docker repository on your system:

```
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
```
- Add the Docker repository to the APT sources:

```
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable"

```

- Update the package database with Docker packages from the newly added repository:

```
sudo apt update
```

- Make sure you are about to install from the Docker repository instead of the default Ubuntu repository:

```
apt-cache policy docker-ce
```

- Install Docker:

```
sudo apt install docker-ce
```

- Check if it is working:

```
sudo systemctl status docker
```


After cloning the project, perform the following
Enter the services folder, then enter the v2 folder and run the following command:

```
docker-compose up   
```

# 🔨 Python Installation
## Installation on Windows

1. **Download the Python Installer**
   - Visit the official Python website: [https://www.python.org/downloads/](https://www.python.org/downloads/).
   - Click the button to download the latest version of Python 3 for Windows.

2. **Run the Installer**
   - Locate the downloaded file and double-click to open it.
   - **Important**: Check the **"Add Python 3.x to PATH"** option before clicking "Install Now."

3. **Choose the Installation Type**
   - **Recommended**: Click "Install Now" for a default installation.
   - To customize, click "Customize Installation."

4. **Complete the Installation**
   - Once the installation finishes, click "Close."

5. **Verify the Installation**
   - Open the **Command Prompt** and type:
     ```bash
     python --version
     ```
     or
     ```bash
     python3 --version
     ```
   - This will display the installed Python version.

---

## Installation on Linux

1. **Update Repositories**
   - Open the terminal and run:
     ```bash
     sudo apt update && sudo apt upgrade -y
     ```

2. **Install Python 3**
   - For Debian/Ubuntu-based distributions:
     ```bash
     sudo apt install python3
     ```
   - For Fedora/CentOS-based distributions:
     ```bash
     sudo dnf install python3
     ```

3. **Install Pip**
   - On Debian/Ubuntu:
     ```bash
     sudo apt install python3-pip
     ```
   - On Fedora/CentOS:
     ```bash
     sudo dnf install python3-pip
     ```

4. **Verify the Installation**
   - In the terminal, type:
     ```bash
     python3 --version
     ```
   - This will display the installed Python version.

---

## Extra Tips

- **Virtual Environments**:
  To manage dependencies in isolation, create a virtual environment:
  ```bash
  python3 -m venv env_name
  source env_name/bin/activate  # Linux/Mac
  env_name\Scripts\activate     # Windows

## **APIs**

Below are the host and port details for the APIs:

| **Application**         | **URL**                                              |
|--------------------------|------------------------------------------------------|
| `stateless-auth-api`     | [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) |
| `stateless-any-api`      | [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html) |
| `stateful-auth-api`      | [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html) |
| `stateful-any-api`       | [http://localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html) |

---

## **Databases**

Below are the host and port details for the databases:

| **Database**             | **Type**     | **Host**           | **Port** |
|--------------------------|--------------|--------------------|----------|
| `stateless-auth-db`      | PostgreSQL   | `localhost`        | `5432`   |
| `stateful-auth-db`       | PostgreSQL   | `localhost`        | `5433`   |
| `token-redis`            | Redis        | `localhost`        | `6379`   |


## **Running Everything with the Utility Script**

To run all applications and databases using the utility script, execute the following command:

```bash
python3 build.py
```

## **Docker Tips**

Here are some useful Docker commands to manage containers and logs:

### **Remove Existing Containers**
- **Stop all containers**:
  ```bash
  docker stop $(docker ps -aq)
  ```
- **Remove all containers**:
  ```bash
  docker container prune -f
  ```

### **View Logs**
- Follow logs for the `token-redis` container:
  ```bash
  docker logs --follow token-redis
  ```

### **Connect to Redis via Shell**
- Execute a shell command to connect to Redis:
  ```bash
  docker exec -it token-redis redis-cli

## **Download DBeaver for Windows**

1. Go to the official DBeaver download page: [DBeaver Download](https://dbeaver.io/download/).
2. Select the **Windows** version (typically **Windows 64-bit**).
3. The installer will be downloaded as an `.exe` file.

### **Run the Installer**

1. Locate the downloaded `.exe` file and double-click to run the installer.
2. The **DBeaver Setup Wizard** will appear. Follow the on-screen instructions:
   - Choose the installation directory or leave the default.
   - Select additional options, such as whether to create shortcuts on the Start Menu and Desktop.

### **Complete the Installation**

1. Once the installation is complete, click **Finish**.
2. DBeaver will launch automatically, or you can open it from the Start Menu or Desktop.

### **Configure DBeaver**

1. Upon first launch, you will be prompted to configure some initial preferences:
   - Select your preferred UI theme (light or dark).
   - Choose any additional plugins or settings you want to configure.
2. After setting up, you can start connecting to your databases.

---

## **Installation on Linux**

There are installation methods for both **Debian-based** distributions (such as Ubuntu) and **RPM-based** distributions (such as Fedora). Additionally, you can install DBeaver via **Snap** or **Flatpak**.

### **Debian-based Distributions (e.g., Ubuntu, Linux Mint)**

1. **Download the .deb Package**
   - Visit the official DBeaver download page: [DBeaver Download](https://dbeaver.io/download/).
   - Choose the **Debian (64-bit)** version for your system.

2. **Install the .deb Package**
   - Open a terminal and navigate to the folder where the `.deb` file was downloaded.
   - Run the following command to install DBeaver:
     ```bash
     sudo dpkg -i dbeaver-ce_<version>.deb
     ```
   - If there are any missing dependencies, run:
     ```bash
     sudo apt-get install -f
     ```

3. **Run DBeaver**
   - Once the installation is complete, you can launch DBeaver from the application menu or run it from the terminal:
     ```bash
     dbeaver
     ```

### **RPM-based Distributions (e.g., Fedora, CentOS, RHEL)**

1. **Download the .rpm Package**
   - Visit the official DBeaver download page: [DBeaver Download](https://dbeaver.io/download/).
   - Choose the **RPM (64-bit)** version for your system.

2. **Install the .rpm Package**
   - Open a terminal and navigate to the folder where the `.rpm` file was downloaded.
   - Run the following command to install DBeaver:
     ```bash
     sudo rpm -i dbeaver-ce-<version>.rpm
     ```

3. **Run DBeaver**
   - After installation, you can run DBeaver from the application menu or execute it via the terminal:
     ```bash
     dbeaver
     ```

### **Using Snap or Flatpak**

You can also install DBeaver using **Snap** or **Flatpak** on Linux.

#### **Install using Snap**:
1. Open a terminal and run:
   ```bash
   sudo snap install dbeaver-ce
   
## :zap: Technologies	

- Java
-  Spring Boot
- API REST
- PostgreSQL (Container)
- Docker
-  Docker-compose
- JWT
- Token

## :memo: Developed features

- [x] Login Validation
- [x] Logout Validation
- [x] Token Validation


## :technologist:	 Author

By Ramon Becker 👋🏽 Get in touch!



[<img src='https://cdn.jsdelivr.net/npm/simple-icons@3.0.1/icons/github.svg' alt='github' height='40'>](https://github.com/RamonBecker)  [<img src='https://cdn.jsdelivr.net/npm/simple-icons@3.0.1/icons/linkedin.svg' alt='linkedin' height='40'>](https://www.linkedin.com/in/https://www.linkedin.com/in/ramon-becker-da-silva-96b81b141//)
![Gmail Badge](https://img.shields.io/badge/-ramonbecker68@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:ramonbecker68@gmail.com)
