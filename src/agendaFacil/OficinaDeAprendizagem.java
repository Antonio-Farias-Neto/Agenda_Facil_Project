package agendaFacil;

import java.util.HashMap;

public class OficinaDeAprendizagem extends Evento{
	private int duracao;
	private boolean certificacao;
	
	public OficinaDeAprendizagem(String titulo, String descricao, String data, int qtdMaxParticipantes, int duracao, boolean certificacao) {
		super(titulo, descricao, data, qtdMaxParticipantes);
		this.duracao = duracao;
		this.certificacao = certificacao;
	}
	
	private String exibirParticipantes() {
		String out = "";
		for (Estudante e: super.estudantesParticipantes.values()) {
			out += e.getNome() + "\n";
		}
		return out;
	}
	
	private String emiteCertificado() {
		if(certificacao) {
			return "Sim";
		}
		return "Não";
	}

	@Override
	public int inscreverParticipante(Estudante e) {
		super.estudantesParticipantes.put(e.getEmail(), e);
		if (this.certificacao) {
			e.setQtdPontos(e.getQtdPontos() + this.duracao + 10);
		} else {
			e.setQtdPontos(e.getQtdPontos() + this.duracao);
		}
		return e.getQtdPontos();
	}

	@Override
	public String toString() {
		return super.titulo + ": " + super.descricao + "\nData: " + super.data + "\nDuração" + this.duracao + "\nEmite Certificado :" + this.emiteCertificado() + "\nParticipantes:" + "\n" + this.exibirParticipantes();
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
