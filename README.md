# 📅 Agenda Fácil

**Agenda Fácil** é um sistema simples de gerenciamento de eventos acadêmicos, voltado para o controle de participação de estudantes em palestras, workshops e oficinas de aprendizagem. O sistema permite o cadastro de estudantes, inscrição em eventos e contabilização de pontos por participação.

---

## 📌 Funcionalidades

- Cadastro de estudantes com nome e e-mail.
  - Não são aceitos nome ou e-mail vazios, nem e-mails já cadastrados.
- Exibição dos dados de um estudante (nome, e-mail, quantidade de eventos e pontos).
- Listagem de estudantes em formato de ranking: ordenados por pontuação (do maior para o menor) e, em caso de empate, por nome em ordem alfabética.
- Cadastro de três tipos de eventos:
  - **Palestras**
  - **Workshops**
  - **Oficinas de Aprendizagem** (com ou sem emissão de certificado)
- Inscrição de estudantes nos eventos, respeitando o limite de participantes.
  - A inscrição é recusada se o estudante ou o evento não existirem, se o estudante já estiver inscrito ou se o evento estiver lotado.
- Contabilização de pontos por tipo de evento:
  - Palestras: +2 pontos
  - Workshops: +duração (em horas)
  - Oficinas: +duração (em horas) e +10 se emitir certificado
- Exibição dos detalhes de um evento e listagem de todos os eventos, com participantes e vagas ocupadas.
- Consulta dos e-mails dos participantes de um evento.

---

## 📋 Regras de Validação

- O limite de participantes de um evento deve ser positivo.
- A duração de workshops e oficinas deve ser positiva.
- Valores inválidos nesses campos lançam `IllegalArgumentException`.

---

## 📁 Estrutura de Classes

#### AgendaFacil.java
- Classe principal de controle do sistema

#### Estudante.java
- Representa um estudante

#### Evento.java
- Classe abstrata base para eventos

#### Palestra.java
- Subclasse de Evento (pontos fixos)

#### Workshop.java
- Subclasse de Evento (pontos por duração)

#### OficinaAprendizagem.java
- Subclasse de Evento (pontos por duração e certificado)

#### GeradorRelatorio.java
- Interface genérica para geração de relatórios

#### TestarAgendaFacil.java
- Testes automatizados (JUnit 5) das funcionalidades do sistema

---

## ▶️ Como Executar

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/agenda-facil.git
cd agenda-facil
```

2. Compile todos os arquivos `.java` (a partir da pasta que contém o diretório `agendaFacil`):

```bash
javac agendaFacil/*.java
```

3. Crie sua própria classe `Main` para interagir com a `AgendaFacil`, ou use os testes automatizados para simular o uso do sistema.

---

## 🧪 Como Executar os Testes

Os testes ficam em `TestarAgendaFacil.java` e usam **JUnit 5**. Para executá-los:

- Em uma IDE (VS Code, Eclipse, IntelliJ), adicione o JUnit 5 ao projeto e execute a classe `TestarAgendaFacil`.
- Pela linha de comando, usando o `junit-platform-console-standalone`:

```bash
javac -cp junit-platform-console-standalone.jar agendaFacil/*.java
java -jar junit-platform-console-standalone.jar -cp . --scan-classpath
```

---

## 🔧 Tecnologias Utilizadas

- Java 11+
- Estruturas de dados: `LinkedHashMap`, `LinkedHashSet`, `ArrayList`
- JUnit 5 (testes)
- Programação Orientada a Objetos (POO)
- Princípios de Herança e Polimorfismo

---

## 📝 Exemplo de Uso

```java
AgendaFacil agenda = new AgendaFacil();

agenda.cadastrarEstudante("João Silva", "joao@email.com");
agenda.cadastrarEstudante("Maria Oliveira", "maria@email.com");

int idPalestra = agenda.cadastrarPalestra("Semana de Engenharia", "Palestra com engenheiros da área", "10/09/2025", 50);
int idOficina = agenda.cadastrarOficinaAprendizagem("Python3", "Aprendendo Python3", "04/10/2025", 25, 5, true);

agenda.inscreverParticipanteEmEvento("joao@email.com", idPalestra);   // João: 2 pontos
agenda.inscreverParticipanteEmEvento("maria@email.com", idOficina);   // Maria: 5 + 10 = 15 pontos

System.out.println(agenda.exibirDetalhesEvento(idPalestra));

// Ranking de estudantes por pontuação: Maria Oliveira, João Silva
for (String nome : agenda.listarEstudantes()) {
    System.out.println(nome);
}
```

---

## 👨‍💻 Autor

Desenvolvido por Antonio Farias Lopes Neto
