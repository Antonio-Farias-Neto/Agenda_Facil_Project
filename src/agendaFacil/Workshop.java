package agendaFacil;

import java.util.HashMap;

public class Workshop extends Evento{
	private int duracao;
	
	public Workshop(String titulo, String descricao, String data, int qtdMaxParticipantes, int duracao) {
		super(titulo, descricao, data, qtdMaxParticipantes);
		this.duracao = duracao;
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
		super.estudantesParticipantes.put(e.getEmail(), e);
		e.setQtdPontos(e.getQtdPontos() + this.duracao);
		return e.getQtdPontos();
	}
	@Override
	public String toString() {
		return super.titulo + ": " + super.descricao + "\nData: " + super.data + "\nDuração" + this.duracao + "\nParticipantes:" + "\n" + this.exibirParticipantes();
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
