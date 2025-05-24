# ☁️ MotoMap – Backend Java (Cloud Deployment)

Este repositório contém o backend em Java (Spring Boot) do sistema MotoMap – Sistema Inteligente de Gestão de Pátios, com foco no processo de containerização via Docker e deployment em uma máquina virtual na Azure.

## 👥 Desenvolvedores

* Caike Dametto – RM55861
* Guilherme Janunzi – RM558461

## 🧾 Descrição

O MotoMap é um sistema desenvolvido para auxiliar na organização e gestão dos pátios de motos da empresa Mottu. A ideia surgiu a partir de uma dor real da empresa: a dificuldade em manter o controle físico e visual de centenas de motos distribuídas entre diferentes filiais.

Para resolver isso, o MotoMap oferece um sistema integrado com:

- Organização visual dos pátios
- Controle de movimentações
- Triagem de veículos com auxílio de visão computacional

Esta parte do projeto trata da disponibilização da API Java responsável por gerenciar:
- Filiais
- Motos
- Posições no Pátio
- Problemas
- Usuários

A aplicação é empacotada em um container Docker e hospedada em uma máquina virtual Linux no Azure, garantindo escalabilidade e acesso externo via IP público.

## ⚙️ Como executar

1.  **Clonar o repositório**
    ```bash
    git clone https://github.com/GuiJanunzzi/MottoMap-JavaBackend.git
    ```
2.  **Criar o Dockerfile**
    Crie um arquivo chamado `Dockerfile` na raiz do projeto com o seguinte conteúdo:

    ```dockerfile
    FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
    WORKDIR /build

    COPY MottoMap-JavaBackend/ ./MottoMap-JavaBackend/
    WORKDIR /build/MottoMap-JavaBackend

    RUN mvn clean package -DskipTests

    FROM eclipse-temurin:17-jre-alpine
    WORKDIR /app

    COPY --from=build /build/MottoMap-JavaBackend/target/*.jar app.jar

    RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser

    USER appuser
    EXPOSE 8080
    ENTRYPOINT ["java", "-jar", "app.jar"]
    ```
3.  **Build e execução local**
    ```bash
    docker build -t mottomap_backend:1.0 .
    docker run -d -p 8080:8080 mottomap_backend:1.0
    ```
    Testar via browser (swagger):
    ```
    http://host.docker.internal:8080/swagger-ui/index.html
    ```
    Ou via API client como Insomnia/Postman.

4.  **Publicar no Docker Hub**
    ```bash
    docker tag mottomap_backend:1.0 guijanunzzi/mottomap_backend:1.0
    docker push guijanunzzi/mottomap_backend:1.0
    ```
5.  **Criar infraestrutura na Azure**
    ```bash
    az group create -l eastus -n rg-mottomap

    az vm create --resource-group rg-mottomap \
      --name vm-linux-mottomap \
      --image Canonical:ubuntu-24_04-lts:minimal:24.04.202505020 \
      --size Standard_B2s \
      --admin-username admin_fiap \
      --admin-password admin_fiap@123

    az network nsg rule create --resource-group rg-mottomap \
      --nsg-name vm-linux-mottomapNSG \
      --name port_8080 --protocol tcp --priority 1010 --destination-port-range 8080

    az network nsg rule create --resource-group rg-mottomap \
      --nsg-name vm-linux-mottomapNSG \
      --name port_80 --protocol tcp --priority 1020 --destination-port-range 80
    ```
6.  **Instalar Docker na VM**
    Acesse a VM via SSH e instale o Docker conforme a documentação oficial: \
    https://docs.docker.com/engine/install/ubuntu/#install-using-the-repository

7.  **Executar o container na VM**
    ```bash
    docker run -d -p 8080:8080 guijanunzzi/mottomap_backend:1.0
    ```
8.  **Testar via IP público da VM** \
    Swagger:
    ```
    http://<IP_Público_VM>:8080/swagger-ui/index.html
    ```
    Insomnia/Postman:
    ```
    http://<IP_Público_VM>:8080/filial
    ```