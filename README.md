# poc-clean-arch-conceitual

## 📖 Descrição
Este projeto é uma **prova de conceito (POC)** para demonstrar a aplicação da **Clean Architecture** em um sistema de gestão de motoristas.  
Foi desenvolvido em **Java com Spring Boot**, estruturado de forma a isolar regras de negócio do restante da aplicação.

---

## ✅ Regras de Negócio
O sistema implementa as seguintes validações no cadastro de motoristas:

- 🚫 Não é permitido cadastrar motoristas com o **mesmo CPF**.  
- 🚫 Não é permitido cadastrar motoristas **menores de idade**.  
- 🚫 Não é permitido cadastrar motoristas **sem automóvel**.  
- 🚫 Não é permitido cadastrar motoristas **com carros antigos**.  
- ⚠️ Se o valor total de **multas ultrapassar R$ 15.000**, o sistema **notifica os administradores** sobre o risco, mas o cadastro ainda deve ocorrer.  
- ➕ O cálculo do valor total de multas considera:
  - Se o motorista possuir **alguma infração grave**, acrescentar **R$ 50** ao valor total.  

---

## 🗂 Estrutura do Projeto
O projeto segue os princípios da Clean Architecture:

- **Core (domínio + use cases):** contém entidades, regras de negócio e casos de uso.  
- **Gateways (interfaces):** definem contratos para persistência, notificações, etc.  
- **Adapters/Infraestrutura:** implementam os gateways usando Spring Boot e tecnologias externas.  
