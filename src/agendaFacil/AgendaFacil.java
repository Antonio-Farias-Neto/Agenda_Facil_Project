package agendaFacil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AgendaFacil {
    private final Map<String, Estudante> estudantes = new LinkedHashMap<>();
    private final Map<Integer, Evento> eventos = new LinkedHashMap<>();
    private int proximoIdEvento = 1;

    public boolean cadastrarEstudante(String nome, String email) {
        if (nome == null || nome.isBlank() || email == null || email.isBlank()
                || estudantes.containsKey(email)) {
            return false;
        }
        estudantes.put(email, new Estudante(nome, email));
        return true;
    }

    public String exibirEstudante(String email) {
        Estudante estudante = estudantes.get(email);
        return estudante == null ? null : estudante.exibirDetalhes();
    }

    public String[] listarEstudantes() {
        return estudantes.values().stream()
                .sorted(Comparator.comparingInt(Estudante::getPontos).reversed()
                        .thenComparing(Estudante::getNome))
                .map(Estudante::getNome)
                .toArray(String[]::new);
    }

    public int cadastrarPalestra(String titulo, String descricao, String data, int maxParticipantes) {
        int x = proximoIdEvento++;
        eventos.put(x, new Palestra(x, titulo, descricao, data, maxParticipantes));
        return x;
    }

    public int cadastrarWorkshop(String titulo, String descricao, String data,
                                 int maxParticipantes, int duracao) {
        int x = proximoIdEvento++;
        eventos.put(x, new Workshop(x, titulo, descricao, data, maxParticipantes, duracao));
        return x;
    }

    public int cadastrarOficinaAprendizagem(String titulo, String descricao, String data,
                                             int maxParticipantes, int duracao,
                                             boolean certificacao) {
        int x = proximoIdEvento++;
        eventos.put(x, new OficinaAprendizagem(x, titulo, descricao, data,
                maxParticipantes, duracao, certificacao));
        return x;
    }

    public boolean inscreverParticipanteEmEvento(String emailParticipante, int idEvento) {
        Estudante estudante = estudantes.get(emailParticipante);
        Evento evento = eventos.get(idEvento);

        if (estudante == null || evento == null || estudante.estaInscrito(idEvento)
                || !evento.temVaga()) {
            return false;
        }

        if (!evento.adicionarParticipante(emailParticipante)) {
            return false;
        }
        estudante.inscrever(idEvento);
        estudante.adicionarPontos(calcularPontosDoEvento(evento));
        return true;
    }

    private int calcularPontosDoEvento(Evento e) {
        if (e instanceof Palestra) {
            return 2;
        } else if (e instanceof Workshop) {
            return ((Workshop) e).getDuracaoHoras();
        } else if (e instanceof OficinaAprendizagem) {
            OficinaAprendizagem a = (OficinaAprendizagem) e;
            int pontos = a.getDuracaoHoras();
            if (a.isEmiteCertificado()) {
                pontos += 10;
            }
            return pontos;
        }
        return 0;
    }

    public String exibirDetalhesEvento(int idEvento) {
        Evento evento = eventos.get(idEvento);
        if (evento == null) {
            return null;
        }
        return evento.exibirDetalhesBase();
    }

    public String[] listarEventos() {
        return eventos.values().stream()
                .map(Evento::exibirDetalhesBase)
                .toArray(String[]::new);
    }

    public int getQuantidadeEstudantes() { return estudantes.size(); }
    
    public int getQuantidadeEventos() { return eventos.size(); }

    public List<String> getEmailsParticipantes(int idEvento) {
        Evento evento = eventos.get(idEvento);
        if (evento == null) {
            return new ArrayList<>();
        }
        return evento.getEmailsParticipantes().stream().collect(Collectors.toList());
    }
}
