package agendaFacil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Evento {
    private final int id;
    private final String titulo;
    private final String descricao;
    private final String data;
    private final int maxParticipantes;
    private final List<String> emailsParticipantes = new ArrayList<>();

    protected Evento(int id, String titulo, String descricao, String data, int maxParticipantes) {
        if (maxParticipantes <= 0) {
            throw new IllegalArgumentException("O limite de participantes deve ser positivo.");
        }
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.maxParticipantes = maxParticipantes;
    }

    public int getId() { return id; }

    public String getTitulo() { return titulo; }

    public String getDescricao() { return descricao; }

    public String getData() { return data; }

    public int getMaxParticipantes() { return maxParticipantes; }

    public List<String> getEmailsParticipantes() {
        return Collections.unmodifiableList(emailsParticipantes);
    }

    public int getQuantidadeParticipantes() { return emailsParticipantes.size(); }

    public boolean temVaga() { return emailsParticipantes.size() < maxParticipantes; }
    
    public boolean possuiParticipante(String email) { return emailsParticipantes.contains(email); }

    public boolean adicionarParticipante(String email) {
        if (!temVaga() || possuiParticipante(email)) {
            return false;
        }
        emailsParticipantes.add(email);
        return true;
    }

    public abstract String getTipo();

    public String exibirDetalhesBase() {
        return "ID: " + id
                + "\nTipo: " + getTipo()
                + "\nTítulo: " + titulo
                + "\nDescrição: " + descricao
                + "\nData: " + data
                + "\nPontuação por participante: " + getPontuacao()
                + "\nParticipantes: " + String.join(", ", emailsParticipantes)
                + "\nVagas: " + getQuantidadeParticipantes() + "/" + maxParticipantes;
    }

    public abstract int getPontuacao();
}
