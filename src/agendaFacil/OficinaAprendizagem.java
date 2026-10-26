package agendaFacil;

public class OficinaAprendizagem extends Evento {
    private final int duracaoHoras;
    private final boolean emiteCertificado;

    public OficinaAprendizagem(int id, String titulo, String descricao, String data,
                               int maxParticipantes, int duracaoHoras, boolean emiteCertificado) {
        super(id, titulo, descricao, data, maxParticipantes);
        if (duracaoHoras <= 0) {
            throw new IllegalArgumentException("A duração deve ser positiva.");
        }
        this.duracaoHoras = duracaoHoras;
        this.emiteCertificado = emiteCertificado;
    }

    public int getDuracaoHoras() { return duracaoHoras; }
    public boolean isEmiteCertificado() { return emiteCertificado; }

    @Override
    public String getTipo() { return "Oficina de Aprendizagem"; }

    @Override
    public int getPontuacao() {
        return duracaoHoras + (emiteCertificado ? 10 : 0);
    }

    @Override
    public String exibirDetalhesBase() {
        return super.exibirDetalhesBase()
                + "\nDuração: " + duracaoHoras + " hora(s)"
                + "\nEmite certificado: " + (emiteCertificado ? "Sim" : "Não");
    }
}
