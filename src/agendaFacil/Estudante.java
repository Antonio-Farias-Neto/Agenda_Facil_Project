package agendaFacil;

import java.util.LinkedHashSet;
import java.util.Set;

public class Estudante {
    private final String nome;
    private final String email;
    private int pontos;
    private final Set<Integer> eventosInscritos = new LinkedHashSet<>();

    public Estudante(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.pontos = 0;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public int getPontos() { return pontos; }
    public int getQuantidadeEventos() { return eventosInscritos.size(); }

    public boolean estaInscrito(int idEvento) {
        return eventosInscritos.contains(idEvento);
    }

    public boolean inscrever(int idEvento) {
        return eventosInscritos.add(idEvento);
    }

    public void adicionarPontos(int quantidade) {
        pontos += quantidade;
    }

    public Set<Integer> getEventosInscritos() {
        return new LinkedHashSet<>(eventosInscritos);
    }

    public String exibirDetalhes() {
        return "Nome: " + nome
                + "\nE-mail: " + email
                + "\nQuantidade de eventos: " + getQuantidadeEventos()
                + "\nPontos: " + pontos;
    }
}
