# 📅 Agenda Fácil

**Agenda Fácil** é um sistema simples de gerenciamento de eventos acadêmicos, voltado para o controle de participação de estudantes em palestras, workshops e oficinas de aprendizagem. O sistema permite o cadastro de estudantes, inscrição em eventos e contabilização de pontos por participação.

---

## 📌 Funcionalidades

- Cadastro de estudantes com nome e e-mail.
- Listagem e exibição de estudantes cadastrados.
- Cadastro de três tipos de eventos:
  - **Palestras**
  - **Workshops**
  - **Oficinas de Aprendizagem** (com ou sem emissão de certificado)
- Inscrição de estudantes nos eventos, respeitando o limite de participantes.
- Contabilização de pontos por tipo de evento:
  - Palestras: +2 pontos
  - Workshops: +duração
  - Oficinas: +duração e +10 se emitir certificado
- Exibição dos detalhes de cada evento e seus participantes.

---

## 📁 Estrutura de Classes

#### AgendaFacilController.java 
- Classe principal de controle do sistema

#### Estudante.java  
- Representa um estudante

#### Evento.java 
- Classe abstrata base para eventos

#### Palestra.java 
- Subclasse de Evento (pontos fixos)

#### Workshop.java 
- Subclasse de Evento (pontos por duração)

#### OficinaDeAprendizagem.java 
- Subclasse de Evento (pontos por duração e certificado)

---
## ▶️ Como Executar

1 Clone o repositório:

- git clone https://github.com/seu-usuario/agenda-facil.git
- cd agenda-facil

2 Compile todos os arquivos .java:

- javac agendaFacil/*.java

3 Crie sua própria classe Main para interagir com o AgendaFacilController, ou implemente testes manuais automatizados para simular o uso do sistema.

---

## 🔧 Tecnologias Utilizadas

- Java 8+
- Estruturas de dados: `HashMap`, `ArrayList`
- Programação Orientada a Objetos (POO)
- Princípios de Herança e Polimorfismo

---

## 📝 Exemplo de Uso
```java
AgendaFacilController controller = new AgendaFacilController();

controller.cadastrarEstudante("João Silva", "joao@email.com");
controller.cadastrarEstudante("Maria Oliveira", "maria@email.com");

int idEvento = controller.cadastrarPalestra("Semana de Engenharia", "Palestra com engenheiros da área", "10/09/2025", 50);
controller.inscreverParticipanteEmEvento("joao@email.com", idEvento);

System.out.println(controller.exibirDetalhesEvento(idEvento));
```
---

## 👨‍💻 Autor

Desenvolvido por Antonio Farias Lopes Neto




