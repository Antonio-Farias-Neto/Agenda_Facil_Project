package agendaFacil;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestarCalculoDaPontuacaoERanking {
	private Estudante e;
	private Evento p;
	private Evento w;
	private Evento o;
	private Evento o2;
	
	@BeforeEach
	void preparaEventos() {
		p = new Palestra("POO", "O que é POO?","24/04/2025",100);
		w = new Workshop("Java","Como funciona o Java?","30/01/2025",30,3);
		o = new OficinaDeAprendizagem("Python3", "Aprendendo Python3","04/10/2025",25,5,true);
		o2 = new OficinaDeAprendizagem("Python3", "Aprendendo Python3","04/10/2025",25,5,false);
		e = new Estudante("Hugo", "Hugo@gmail.com");
	}
	@Test
	void calculoPontuacaoPalestra() {
		assertEquals(p.inscreverParticipante(e), 2);
	}
	
	@Test
	void calculoPontuacaoWorkshop() {
		assertEquals(w.inscreverParticipante(e), 3);
	}
	
	@Test
	void calculoPontuacaoOficinaComCertificado() {
		assertEquals(o.inscreverParticipante(e), 15);
	}
	
	@Test
	void calculoPontuacaoOficinaSemCertificado() {
		assertEquals(o2.inscreverParticipante(e), 5);
	}
	
	@Test
	void testarListagemDeEstudantesPorPontuacaoDecrescente() {
		AgendaFacilController af = new AgendaFacilController();
		af.cadastrarEstudante("Hugo", "Hugo@gmail.com");
		af.cadastrarEstudante("Diego","Diego@gmail.com");
		af.cadastrarEstudante("Clara","Clara@gmail.com");
		af.cadastrarPalestra("POO", "O que é POO?","24/04/2025",100);
		af.cadastrarWorkshop("Java","Como funciona o Java?","30/01/2025",30,3);
		af.cadastrarOficinaAprendizagem("Python3", "Aprendendo Python3","04/10/2025",25,5,true);
		af.inscreverParticipanteEmEvento("Hugo@gmail.com", 1);
		af.inscreverParticipanteEmEvento("Diego@gmail.com", 3);
		af.inscreverParticipanteEmEvento("Clara@gmail.com", 2);
		
		String[] estudantes = {"Diego","Clara","Hugo"};
		
		assertEquals(af.listarEstudantes()[0], estudantes[0]);
		assertEquals(af.listarEstudantes()[1], estudantes[1]);
		assertEquals(af.listarEstudantes()[2], estudantes[2]);
	}

}
