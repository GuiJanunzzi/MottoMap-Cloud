# ☁️ MotoMap – Backend Java (Deploy com ACR + ACI na Azure)

Este repositório contém o backend em **Java (Spring Boot)** do sistema **MotoMap – Sistema Inteligente de Gestão de Pátios**.  
O projeto está configurado para um fluxo de deploy moderno na nuvem, utilizando contêineres Docker gerenciados pelos serviços da Azure.

---

## 👥 Desenvolvedores
- **Nome:** Caike Dametto – **RM:** 558614
- **Nome:** Guilherme Janunzi – **RM:** 558461

---

## 🧾 Descrição da Solução

O **MotoMap** é uma solução criada para resolver a complexidade na gestão de pátios de motocicletas da empresa Mottu. O sistema ataca uma dor real da operação: a dificuldade de controlar centenas de veículos espalhados por diversas filiais, o que gera ineficiência e perda de tempo.

O backend, desenvolvido em Java com Spring Boot, centraliza todas as regras de negócio e serve como uma API RESTful para gerenciar as 
principais entidades do sistema:

- **Filiais:** Cadastro e controle das unidades da empresa.
- **Usuários:** Gerenciamento de colaboradores e seus respectivos cargos.
- **Motos:** Registro de cada veículo, incluindo chassi, placa, modelo e status.
- **Posições no Pátio:** Mapeamento físico de onde cada moto está estacionada.
- **Problemas:** Apontamento de manutenções ou pendências associadas a uma moto.

Nesta entrega, a aplicação é containerizada com Docker e publicada no **Azure Container Registry (ACR)**. A execução é feita de forma serverless através do **Azure Container Instance (ACI)**, que se conecta a um banco de dados **SQL Server na Azure**, garantindo uma arquitetura robusta, escalável e de fácil manutenção.

---

## 📈 Benefícios para o Negócio
- **Centralização da Informação**: Consolida os dados de todas as filiais em um único banco de dados na nuvem, permitindo uma visão unificada da operação.  
- **Agilidade na Localização de Veículos**: Reduz drasticamente o tempo necessário para encontrar uma moto específica no pátio.
- **Otimização de Processos**: Automatiza o registro de entrada, saída e movimentação de motos, diminuindo a necessidade de controles manuais e planilhas.  
- **Escalabilidade**: A arquitetura em nuvem permite que o sistema cresça junto com a empresa, suportando novas filiais e um volume maior de veículos sem grandes mudanças  

---

## 🚀 Arquitetura da Solução
A solução segue práticas modernas de **DevOps & Cloud Computing**:

1. **Código-Fonte** → O backend em Java é mantido em um repositório no GitHub.  
2. **Containerização** → Um Dockerfile define o ambiente de execução da aplicação, que é empacotada em uma imagem Docker.  
3. **Registro de Imagem** → A imagem Docker é enviada e armazenada de forma segura no **Azure Container Registry (ACR)**.
4. **Execução do Contêiner** → O **Azure Container Instance (ACI)** é utilizado para executar a imagem armazenada no ACR. O ACI provisiona um ambiente de contêiner sob demanda, sem a necessidade de gerenciar máquinas virtuais.
  
5. **Banco de Dados** → O ACI se conecta a uma instância do **Banco de Dados SQL da Azure**, que armazena todos os dados da aplicação de forma persistente e segura.  
6. **Acesso Externo** → A aplicação fica acessível na internet através de um FQDN (Nome de Domínio Totalmente Qualificado) público, fornecido pelo ACI.  

---

## 📋 Pré-requisitos
Antes de começar, garanta que você tenha as seguintes ferramentas instaladas e configuradas:
- [Git](https://git-scm.com/)  
- [Docker](https://www.docker.com/)  
- Conta ativa na [Microsoft Azure](https://azure.microsoft.com/)  

---

## ⚙️ Passo a Passo para Deploy e Testes
Siga os passos abaixo para configurar a infraestrutura na Azure, publicar a imagem da aplicação e realizar os testes.

### **1. Clonar o Repositório**
```bash
git clone https://github.com/GuiJanunzzi/MottoMap-Cloud.git
cd MottoMap-Cloud
```

## **Passo 2 – Criar a Infraestrutura no Portal da Azure**
Acesse o Portal da [Azure](https://azure.microsoft.com/) e siga as instruções abaixo.
### 1. Grupo de Recursos
- Buscar **“Grupos de Recursos”** no portal.  
- **+ Criar** → Informar:  
  - Assinatura: selecionar sua assinatura.  
  - Nome: `rg-mottomap`  
  - Região: **East US 2**  
- **Revisar + Criar** → **Criar**.

---

### 2. Banco de Dados SQL Server
- Buscar **“Banco de Dados SQL”** → **+ Criar**.  
- Configurar:  
  - Grupo de Recursos: `rg-mottomap`  
  - Nome: `mottomap-db`  
  - Servidor: **Criar novo**  
    - Nome: `mottomapdb` (único global)  
    - Localização: East US 2  
    - Autenticação: **Use SQL authentication**  
    - Usuário: `admin_mottomap`  
    - Senha: `Senha@Test123`  
  - Ambiente: Desenvolvimento   
- **Rede**:  
  - Conectividade: **Ponto de extremidade público**  
  - Firewall: Permitir acesso de serviços do Azure.  
- **Revisar + Criar** → **Criar**.

---

### 3. Azure Container Registry (ACR)
- Buscar **“Registros de Contêiner”** → **+ Criar**.  
- Configurar:  
  - Grupo de Recursos: `rg-mottomap`  
  - Nome: `mottomapacr` (único global)  
  - Localização: East US 2  
  - SKU: **Básico**  
- **Revisar + Criar** → **Criar**.

---

## **Passo 3 – Build e Push da Imagem Docker**

1. Construir a imagem Docker localmente:

```bash
  docker build -t mottomap_backend:v1 .
```

2. Autenticar no ACR via Azure CLI

```bash
  az acr login --name mottomapacr
```

3. Taguear a imagem para o ACR:

```bash
  docker tag mottomap_backend:v1 mottomapacr.azurecr.io/mottomap_backend:v1
```

4. Enviar a imagem para o ACR:

```bash
  docker push mottomapacr.azurecr.io/mottomap_backend:v1
```

## **Passo 4 – Executar o Contêiner**

### 1. Criar a Instância de Contêiner
No **Portal da Azure**:  
- Buscar por **Instâncias de contêiner** → **+ Criar**.  

**Configuração básica**:
- Grupo de Recursos: `rg-mottomap`  
- Nome do contêiner: `mottomap-container`  
- Região: **East US 2**  
- Origem da imagem: **Azure Container Registry**  
- Registro: `mottomapacr`  
- Imagem: `mottomap_backend`  
- Tag da imagem: `v1`  
- Tipo de SO: **Linux**  

**Rede**:
- DNS: `mottomap-app` (nome globalmente único)  
- Porta: **8080 / TCP**  

**Avançado → Variáveis de ambiente**:
```env
DB_HOST=mottomap-db-server.database.windows.net
DB_NAME=mottomap-db
DB_USERNAME=admin_mottomap
DB_PASSWORD=Senha@Test123
```
- Clique em Revisar + Criar → Criar.
- Após a criação, anotar o FQDN (URL completa) exibido na visão geral do recurso **mottomap-container**.

## Passo 5: Testes da Aplicação

Após a execução do ACI, a aplicação estará disponível na internet.
### 1. Testar os Endpoints (POST e PUT)
Use um cliente de API como o Insomnia/Postman.
> Exemplo: Criar (POST) uma nova Filial
````
{
    "nome":"Mottu Rio",
    "endereco":"Avenida Hilario Pereira de Sousa, 406",
    "cidade":"Osasco",
    "siglaEstado":"SP",
    "numeroColuna":"10",
    "numeroLinha":"10",
    "capacidadeMaxima":"100"
}
````
>Exemplo: Atualizar (PUT) a Filial com ID 1
````
{
    "nome":"Mottu Londrina",
    "endereco":"Avenida das Flores, 210",
    "cidade":"Londrina",
    "siglaEstado":"PR",
    "numeroColuna":"20",
    "numeroLinha":"30",
    "capacidadeMaxima":"600"
}
````

### 2. Acessar a Documentação da API (Swagger UI)
Abra o seguinte link no seu navegador, substituindo `<SEU_FQDN>` pela URL anotada no passo anterior:

```
http://<SEU_FQDN>:8080/swagger-ui/index.html
```
## 🐳 Dockerfile

O `Dockerfile` utilizado no projeto segue a prática de **multi-stage builds**, que otimiza o tamanho da imagem final e aumenta a segurança ao rodar a aplicação com um **usuário não-root**.

````dockerfile
# Estágio 1: Build da aplicação com Maven
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

# Estágio 2: Criação da imagem final de execução
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Variável de ambiente para o profile do Spring
ENV ACTIVE_PROFILE=prod

# Copia o .jar gerado no estágio anterior
COPY --from=build /build/target/*.jar app.jar

# Cria um usuário e grupo específicos para rodar a aplicação
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser

# Define o usuário que irá rodar a aplicação
USER appuser

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
````