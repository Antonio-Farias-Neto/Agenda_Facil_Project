package agendaFacil;

import java.util.Objects;

public class Estudante {
	private String nome;
	private String email;
	private int qtdEventos;
	private int qtdPontos;
	
	public Estudante(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudante other = (Estudante) obj;
		return Objects.equals(email, other.email);
	}
	
	
	public int getQtdPontos() {
		return qtdPontos;
	}

	public void setQtdPontos(int qtdPontos) {
		this.qtdPontos = qtdPontos;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getNome() {
		return this.nome;
	}

	@Override
	public String toString() {
		return "Nome: " + this.nome + "\n" + "Quantidade de eventos: " + this.qtdEventos + "\n"  + "Pontos: " + this.qtdPontos;
	}
}
