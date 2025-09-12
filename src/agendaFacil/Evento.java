package agendaFacil;

import java.util.HashMap;

public abstract class Evento {
	protected String titulo;
	protected String descricao;
	protected String data;
	protected int qtdMaxParticipantes;
	protected int id;
	protected HashMap<String,Estudante> estudantesParticipantes;
	
	public Evento(String titulo, String descricao, String data, int qtdMaxParticipantes) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.data = data;
		this.qtdMaxParticipantes = qtdMaxParticipantes;
		this.estudantesParticipantes = new HashMap<>();
	}
	
	public abstract int inscreverParticipante(Estudante e);
	
	public abstract String toString();
	
	public abstract void setId(int id);
	
	public abstract int getId();
	
	public abstract int getQtdMaxParticipantes();
	
	public abstract HashMap<String,Estudante> getEstudantesParticipantes();
}
