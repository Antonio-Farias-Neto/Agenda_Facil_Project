package agendaFacil;

public class Palestra extends Evento {
    public Palestra(int id, String titulo, String descricao, String data, int maxParticipantes) {
        super(id, titulo, descricao, data, maxParticipantes);
    }

    @Override
    public String getTipo() { return "Palestra"; }

    @Override
    public int getPontuacao() { return 2; }
}
