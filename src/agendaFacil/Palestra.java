package agendaFacil;

import java.util.HashMap;

public class Palestra extends Evento{
	public Palestra(String titulo, String descricao, String data, int qtdMaxParticipantes) {
		super(titulo, descricao, data, qtdMaxParticipantes);
	}
	
	private String exibirParticipantes() {
		String out = "";
		for (Estudante e: super.estudantesParticipantes.values()) {
			out += e.getNome() + "\n";
		}
		return out;
	}

	@Override
	public int inscreverParticipante(Estudante e) {
		super.estudantesParticipantes.put(e.getEmail(),e);
		e.setQtdPontos(e.getQtdPontos() + 2);
		return e.getQtdPontos();
	}

	@Override
	public String toString() {
		return super.titulo + ": " + super.descricao + "\n" + "Data: " + super.data + "Participantes:" + "\n" + this.exibirParticipantes();
	}

	@Override
	public HashMap<String, Estudante> getEstudantesParticipantes() {
		return super.estudantesParticipantes;
	}

	@Override
	public void setId(int id) {
		super.id = id;
	}

	@Override
	public int getId() {
		return super.id;
	}

	@Override
	public int getQtdMaxParticipantes() {
		return super.qtdMaxParticipantes;
	}
	
	
}
