package agendaFacil;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class AgendaFacilController {
	private HashMap<String,Estudante> estudantes;
	private HashMap<Integer,Evento> eventos;
	private int contadorIdEventos;
	
	public AgendaFacilController() {
		this.estudantes = new HashMap<>();
		this.eventos = new HashMap<>();
		this.contadorIdEventos = 1;
	}
	
	public boolean cadastrarEstudante(String nome, String email) {
		if (this.estudantes.containsKey(email)) {
			throw new IllegalArgumentException("Estudante ja existe no sistema");
		}
		Estudante e = new Estudante(nome,email);
		this.estudantes.put(email, e);
		return true;
	}
	
	public String exibirEstudante(String email) {
		return this.estudantes.get(email).toString();
	}
	
	public String[] listarEstudantes() {
		ArrayList<Integer> estudantesParaOrdenar = new ArrayList<>();
		for (Estudante e: this.estudantes.values()) {
			estudantesParaOrdenar.add(e.getQtdPontos());
		}
		Collections.sort(estudantesParaOrdenar);
		String estudantesOrdenados[] = new String[estudantesParaOrdenar.size()];
		int contador = 0;
		for (int i = estudantesParaOrdenar.size()-2; i != 0; i--) {
			for (Estudante es: this.estudantes.values()) {
				if (estudantesParaOrdenar.get(i) == es.getQtdPontos()) {
					estudantesOrdenados[contador] = es.getNome();
					contador++;
				}
			}
		}
		return estudantesOrdenados;
		
	}
	
	public int cadastrarPalestra(String titulo, String descricao, String data, int maxParticipantes) {
		Evento p = new Palestra(titulo,descricao,data,maxParticipantes);
		this.eventos.put(this.contadorIdEventos, p);
		p.setId(contadorIdEventos);
		this.contadorIdEventos++;
		return this.contadorIdEventos -1;
		
	}
	
	public int cadastrarWorkshop(String titulo, String descricao, String data, int maxParticipantes, int duracao) {
		Evento w = new Workshop(titulo,descricao,data,maxParticipantes,duracao);
		this.eventos.put(this.contadorIdEventos, w);
		w.setId(contadorIdEventos);
		this.contadorIdEventos++;
		return this.contadorIdEventos -1;
	}

	public int cadastrarOficinaAprendizagem(String titulo, String descricao, String data, int maxParticipantes, int duracao, boolean certificacao) {
		Evento o = new OficinaDeAprendizagem(titulo,descricao,data,maxParticipantes,duracao,certificacao);
		this.eventos.put(this.contadorIdEventos, o);
		o.setId(contadorIdEventos);
		this.contadorIdEventos++;
		return this.contadorIdEventos -1;
	}
	
	public boolean inscreverParticipanteEmEvento(String emailParticipante, int idEvento) {
		if (this.eventos.get(idEvento).getEstudantesParticipantes().containsKey(emailParticipante)) {
			throw new IllegalArgumentException("Estudante já está nesse evento");
		}
		if (this.eventos.get(idEvento).getEstudantesParticipantes().size() == this.eventos.get(idEvento).getQtdMaxParticipantes()) {
			throw new IllegalArgumentException("Tamanho Máximo do evento atingido");
		}
		this.eventos.get(idEvento).getEstudantesParticipantes().put(emailParticipante, this.estudantes.get(emailParticipante));
		return true;
	}
	
	public String exibirDetalhesEvento(int idEvento) {
		return this.eventos.get(idEvento).toString();
	}
}
