package agendaFacil;

public class Workshop extends Evento {
    private final int duracaoHoras;

    public Workshop(int id, String titulo, String descricao, String data,
                    int maxParticipantes, int duracaoHoras) {
        super(id, titulo, descricao, data, maxParticipantes);
        if (duracaoHoras <= 0) {
            throw new IllegalArgumentException("A duração deve ser positiva.");
        }
        this.duracaoHoras = duracaoHoras;
    }

    public int getDuracaoHoras() { return duracaoHoras; }

    @Override
    public String getTipo() { return "Workshop"; }

    @Override
    public int getPontuacao() { return duracaoHoras; }

    @Override
    public String exibirDetalhesBase() {
        return super.exibirDetalhesBase() + "\nDuração: " + duracaoHoras + " hora(s)";
    }
}
