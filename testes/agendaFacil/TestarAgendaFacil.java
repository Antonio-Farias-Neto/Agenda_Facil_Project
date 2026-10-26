package agendaFacil;
 
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestarAgendaFacil {
 
	private AgendaFacil af;
	private Evento palestra;
	private Evento workshop;
	private Evento oficinaComCertificado;
	private Evento oficinaSemCertificado;
 
	@BeforeEach
	void preparar() {
		af = new AgendaFacil();
		palestra = new Palestra(1, "POO", "O que é POO?", "24/04/2025", 100);
		workshop = new Workshop(2, "Java", "Como funciona o Java?", "30/01/2025", 30, 3);
		oficinaComCertificado = new OficinaAprendizagem(3, "Python3", "Aprendendo Python3", "04/10/2025", 25, 5, true);
		oficinaSemCertificado = new OficinaAprendizagem(4, "Python3", "Aprendendo Python3", "04/10/2025", 25, 5, false);
	}
 
	@Test
	void pontuacaoDaPalestraEhFixaEmDois() {
		assertEquals(2, palestra.getPontuacao());
	}
 
	@Test
	void pontuacaoDoWorkshopEhIgualADuracao() {
		assertEquals(3, workshop.getPontuacao());
	}
 
	@Test
	void pontuacaoDaOficinaComCertificadoSomaDezPontos() {
		assertEquals(15, oficinaComCertificado.getPontuacao());
	}
 
	@Test
	void pontuacaoDaOficinaSemCertificadoEhIgualADuracao() {
		assertEquals(5, oficinaSemCertificado.getPontuacao());
	}
 
	@Test
	void tipoDeCadaEvento() {
		assertEquals("Palestra", palestra.getTipo());
		assertEquals("Workshop", workshop.getTipo());
		assertEquals("Oficina de Aprendizagem", oficinaComCertificado.getTipo());
	}
 
	@Test
	void eventoComLimiteZeroDeParticipantesLancaExcecao() {
		assertThrows(IllegalArgumentException.class, () -> new Palestra(1, "T", "D", "01/01/2025", 0));
	}
 
	@Test
	void eventoComLimiteNegativoDeParticipantesLancaExcecao() {
		assertThrows(IllegalArgumentException.class, () -> new Workshop(1, "T", "D", "01/01/2025", -5, 2));
	}
 
	@Test
	void workshopComDuracaoInvalidaLancaExcecao() {
		assertThrows(IllegalArgumentException.class, () -> new Workshop(1, "T", "D", "01/01/2025", 10, 0));
	}
 
	@Test
	void oficinaComDuracaoInvalidaLancaExcecao() {
		assertThrows(IllegalArgumentException.class,
				() -> new OficinaAprendizagem(1, "T", "D", "01/01/2025", 10, -1, true));
	}
 
	@Test
	void cadastrarEventoInvalidoNaAgendaPropagaExcecao() {
		assertThrows(IllegalArgumentException.class, () -> af.cadastrarPalestra("T", "D", "01/01/2025", 0));
		assertThrows(IllegalArgumentException.class, () -> af.cadastrarWorkshop("T", "D", "01/01/2025", 10, 0));
		assertThrows(IllegalArgumentException.class,
				() -> af.cadastrarOficinaAprendizagem("T", "D", "01/01/2025", 10, 0, false));
	}
 
	@Test
	void eventoInvalidoNaoConsomeIdNemEntraNaAgenda() {
		try {
			af.cadastrarPalestra("T", "D", "01/01/2025", 0);
		} catch (IllegalArgumentException ignorada) {}
		assertEquals(0, af.getQuantidadeEventos());
	}
 
	@Test
	void eventoNovoTemVagaEZeroParticipantes() {
		assertTrue(palestra.temVaga());
		assertEquals(0, palestra.getQuantidadeParticipantes());
		assertTrue(palestra.getEmailsParticipantes().isEmpty());
	}
 
	@Test
	void adicionarParticipanteRegistraEmail() {
		assertTrue(palestra.adicionarParticipante("hugo@gmail.com"));
		assertTrue(palestra.possuiParticipante("hugo@gmail.com"));
		assertEquals(1, palestra.getQuantidadeParticipantes());
	}
 
	@Test
	void naoPermiteAdicionarMesmoParticipanteDuasVezes() {
		palestra.adicionarParticipante("hugo@gmail.com");
		assertFalse(palestra.adicionarParticipante("hugo@gmail.com"));
		assertEquals(1, palestra.getQuantidadeParticipantes());
	}
 
	@Test
	void naoPermiteAdicionarParticipanteComEventoLotado() {
		Evento pequeno = new Palestra(9, "Mini", "Lotação 1", "01/01/2025", 1);
		assertTrue(pequeno.adicionarParticipante("a@a.com"));
		assertFalse(pequeno.temVaga());
		assertFalse(pequeno.adicionarParticipante("b@b.com"));
		assertEquals(1, pequeno.getQuantidadeParticipantes());
	}
 
	@Test
	void listaDeParticipantesDoEventoNaoPodeSerModificadaDeFora() {
		palestra.adicionarParticipante("hugo@gmail.com");
		assertThrows(UnsupportedOperationException.class,
				() -> palestra.getEmailsParticipantes().add("intruso@x.com"));
	}
 
	@Test
	void detalhesDaPalestraSemParticipantes() {
		String esperado = "ID: 1"
				+ "\nTipo: Palestra"
				+ "\nTítulo: POO"
				+ "\nDescrição: O que é POO?"
				+ "\nData: 24/04/2025"
				+ "\nPontuação por participante: 2"
				+ "\nParticipantes: "
				+ "\nVagas: 0/100";
		assertEquals(esperado, palestra.exibirDetalhesBase());
	}
 
	@Test
	void detalhesDoWorkshopIncluemDuracao() {
		String detalhes = workshop.exibirDetalhesBase();
		assertTrue(detalhes.contains("Tipo: Workshop"));
		assertTrue(detalhes.contains("Pontuação por participante: 3"));
		assertTrue(detalhes.endsWith("\nDuração: 3 hora(s)"));
	}
 
	@Test
	void detalhesDaOficinaIncluemDuracaoECertificado() {
		String com = oficinaComCertificado.exibirDetalhesBase();
		String sem = oficinaSemCertificado.exibirDetalhesBase();
 
		assertTrue(com.contains("Tipo: Oficina de Aprendizagem"));
		assertTrue(com.contains("Pontuação por participante: 15"));
		assertTrue(com.contains("\nDuração: 5 hora(s)"));
		assertTrue(com.endsWith("\nEmite certificado: Sim"));
		assertTrue(sem.endsWith("\nEmite certificado: Não"));
	}
 
	@Test
	void detalhesListamParticipantesEVagasOcupadas() {
		palestra.adicionarParticipante("a@a.com");
		palestra.adicionarParticipante("b@b.com");
 
		String detalhes = palestra.exibirDetalhesBase();
		assertTrue(detalhes.contains("\nParticipantes: a@a.com, b@b.com"));
		assertTrue(detalhes.contains("\nVagas: 2/100"));
	}
 
	@Test
	void estudanteNovoComecaSemPontosENemEventos() {
		Estudante e = new Estudante("Hugo", "hugo@gmail.com");
		assertEquals("Hugo", e.getNome());
		assertEquals("hugo@gmail.com", e.getEmail());
		assertEquals(0, e.getPontos());
		assertEquals(0, e.getQuantidadeEventos());
	}
 
	@Test
	void estudanteAcumulaPontos() {
		Estudante e = new Estudante("Hugo", "hugo@gmail.com");
		e.adicionarPontos(2);
		e.adicionarPontos(15);
		assertEquals(17, e.getPontos());
	}
 
	@Test
	void estudanteNaoSeInscreveDuasVezesNoMesmoEvento() {
		Estudante e = new Estudante("Hugo", "hugo@gmail.com");
		assertFalse(e.estaInscrito(1));
		assertTrue(e.inscrever(1));
		assertTrue(e.estaInscrito(1));
		assertFalse(e.inscrever(1));
		assertEquals(1, e.getQuantidadeEventos());
	}
 
	@Test
	void getEventosInscritosRetornaCopiaDefensiva() {
		Estudante e = new Estudante("Hugo", "hugo@gmail.com");
		e.inscrever(1);
		e.getEventosInscritos().add(99);
		assertFalse(e.estaInscrito(99));
		assertEquals(1, e.getQuantidadeEventos());
	}
 
	@Test
	void detalhesDoEstudante() {
		Estudante e = new Estudante("Hugo", "hugo@gmail.com");
		e.inscrever(1);
		e.adicionarPontos(2);
		String esperado = "Nome: Hugo"
				+ "\nE-mail: hugo@gmail.com"
				+ "\nQuantidade de eventos: 1"
				+ "\nPontos: 2";
		assertEquals(esperado, e.exibirDetalhes());
	}
 
	@Test
	void cadastrarEstudanteValido() {
		assertTrue(af.cadastrarEstudante("Hugo", "hugo@gmail.com"));
		assertEquals(1, af.getQuantidadeEstudantes());
	}
 
	@Test
	void naoCadastraEstudanteComEmailRepetido() {
		af.cadastrarEstudante("Hugo", "hugo@gmail.com");
		assertFalse(af.cadastrarEstudante("Outro Hugo", "hugo@gmail.com"));
		assertEquals(1, af.getQuantidadeEstudantes());
	}
 
	@Test
	void naoCadastraEstudanteComDadosInvalidos() {
		assertFalse(af.cadastrarEstudante(null, "a@a.com"));
		assertFalse(af.cadastrarEstudante("", "a@a.com"));
		assertFalse(af.cadastrarEstudante("   ", "a@a.com"));
		assertFalse(af.cadastrarEstudante("Hugo", null));
		assertFalse(af.cadastrarEstudante("Hugo", ""));
		assertFalse(af.cadastrarEstudante("Hugo", "   "));
		assertEquals(0, af.getQuantidadeEstudantes());
	}
 
	@Test
	void exibirEstudanteCadastrado() {
		af.cadastrarEstudante("Hugo", "hugo@gmail.com");
		String esperado = "Nome: Hugo"
				+ "\nE-mail: hugo@gmail.com"
				+ "\nQuantidade de eventos: 0"
				+ "\nPontos: 0";
		assertEquals(esperado, af.exibirEstudante("hugo@gmail.com"));
	}
 
	@Test
	void exibirEstudanteInexistenteRetornaNull() {
		assertNull(af.exibirEstudante("ninguem@gmail.com"));
	}
 
	@Test
	void idsDosEventosSaoSequenciaisAPartirDeUm() {
		int id1 = af.cadastrarPalestra("POO", "O que é POO?", "24/04/2025", 100);
		int id2 = af.cadastrarWorkshop("Java", "Como funciona o Java?", "30/01/2025", 30, 3);
		int id3 = af.cadastrarOficinaAprendizagem("Python3", "Aprendendo Python3", "04/10/2025", 25, 5, true);
 
		assertEquals(1, id1);
		assertEquals(2, id2);
		assertEquals(3, id3);
		assertEquals(3, af.getQuantidadeEventos());
	}
 
	@Test
	void exibirDetalhesDeEventoCadastrado() {
		int id = af.cadastrarPalestra("POO", "O que é POO?", "24/04/2025", 100);
		assertEquals(palestra.exibirDetalhesBase(), af.exibirDetalhesEvento(id));
	}
 
	@Test
	void exibirDetalhesDeEventoInexistenteRetornaNull() {
		assertNull(af.exibirDetalhesEvento(42));
	}
 
	@Test
	void listarEventosMantemOrdemDeCadastro() {
		af.cadastrarPalestra("POO", "O que é POO?", "24/04/2025", 100);
		af.cadastrarWorkshop("Java", "Como funciona o Java?", "30/01/2025", 30, 3);
 
		String[] eventos = af.listarEventos();
 
		assertEquals(2, eventos.length);
		assertTrue(eventos[0].contains("Tipo: Palestra"));
		assertTrue(eventos[1].contains("Tipo: Workshop"));
	}
 
	@Test
	void listarEventosSemCadastroRetornaVazio() {
		assertEquals(0, af.listarEventos().length);
	}
 
	@Test
	void inscricaoEmPalestraDaDoisPontos() {
		af.cadastrarEstudante("Hugo", "hugo@gmail.com");
		int id = af.cadastrarPalestra("POO", "O que é POO?", "24/04/2025", 100);
 
		assertTrue(af.inscreverParticipanteEmEvento("hugo@gmail.com", id));
		assertTrue(af.exibirEstudante("hugo@gmail.com").endsWith("Pontos: 2"));
	}
 
	@Test
	void inscricaoEmWorkshopDaPontosIguaisADuracao() {
		af.cadastrarEstudante("Hugo", "hugo@gmail.com");
		int id = af.cadastrarWorkshop("Java", "Como funciona o Java?", "30/01/2025", 30, 3);
 
		assertTrue(af.inscreverParticipanteEmEvento("hugo@gmail.com", id));
		assertTrue(af.exibirEstudante("hugo@gmail.com").endsWith("Pontos: 3"));
	}
 
	@Test
	void inscricaoEmOficinaComCertificadoDaDuracaoMaisDez() {
		af.cadastrarEstudante("Hugo", "hugo@gmail.com");
		int id = af.cadastrarOficinaAprendizagem("Python3", "Aprendendo Python3", "04/10/2025", 25, 5, true);
 
		assertTrue(af.inscreverParticipanteEmEvento("hugo@gmail.com", id));
		assertTrue(af.exibirEstudante("hugo@gmail.com").endsWith("Pontos: 15"));
	}
 
	@Test
	void inscricaoEmOficinaSemCertificadoDaApenasADuracao() {
		af.cadastrarEstudante("Hugo", "hugo@gmail.com");
		int id = af.cadastrarOficinaAprendizagem("Python3", "Aprendendo Python3", "04/10/2025", 25, 5, false);
 
		assertTrue(af.inscreverParticipanteEmEvento("hugo@gmail.com", id));
		assertTrue(af.exibirEstudante("hugo@gmail.com").endsWith("Pontos: 5"));
	}
 
	@Test
	void pontosDaInscricaoCorrespondemAoGetPontuacaoDoEvento() {
		af.cadastrarEstudante("Hugo", "hugo@gmail.com");
		int id = af.cadastrarOficinaAprendizagem("Python3", "Aprendendo Python3", "04/10/2025", 25, 5, true);
 
		af.inscreverParticipanteEmEvento("hugo@gmail.com", id);
 
		assertTrue(af.exibirEstudante("hugo@gmail.com").endsWith("Pontos: " + oficinaComCertificado.getPontuacao()));
	}
 
	@Test
	void pontosSaoAcumuladosEntreEventos() {
		af.cadastrarEstudante("Hugo", "hugo@gmail.com");
		int p = af.cadastrarPalestra("POO", "O que é POO?", "24/04/2025", 100);
		int w = af.cadastrarWorkshop("Java", "Como funciona o Java?", "30/01/2025", 30, 3);
		int o = af.cadastrarOficinaAprendizagem("Python3", "Aprendendo Python3", "04/10/2025", 25, 5, true);
 
		assertTrue(af.inscreverParticipanteEmEvento("hugo@gmail.com", p));
		assertTrue(af.inscreverParticipanteEmEvento("hugo@gmail.com", w));
		assertTrue(af.inscreverParticipanteEmEvento("hugo@gmail.com", o));
 
		String detalhes = af.exibirEstudante("hugo@gmail.com");
		assertTrue(detalhes.contains("Quantidade de eventos: 3"));
		assertTrue(detalhes.endsWith("Pontos: 20")); // 2 + 3 + 15
	}
 
	@Test
	void inscricaoRegistraEmailNoEvento() {
		af.cadastrarEstudante("Hugo", "hugo@gmail.com");
		int id = af.cadastrarPalestra("POO", "O que é POO?", "24/04/2025", 100);
 
		af.inscreverParticipanteEmEvento("hugo@gmail.com", id);
 
		assertEquals(List.of("hugo@gmail.com"), af.getEmailsParticipantes(id));
		assertTrue(af.exibirDetalhesEvento(id).contains("Vagas: 1/100"));
	}
 
	@Test
	void naoInscreveEstudanteInexistente() {
		int id = af.cadastrarPalestra("POO", "O que é POO?", "24/04/2025", 100);
 
		assertFalse(af.inscreverParticipanteEmEvento("fantasma@gmail.com", id));
		assertTrue(af.getEmailsParticipantes(id).isEmpty());
	}
 
	@Test
	void naoInscreveEmEventoInexistente() {
		af.cadastrarEstudante("Hugo", "hugo@gmail.com");
 
		assertFalse(af.inscreverParticipanteEmEvento("hugo@gmail.com", 99));
		assertTrue(af.exibirEstudante("hugo@gmail.com").endsWith("Pontos: 0"));
	}
 
	@Test
	void naoInscreveDuasVezesNoMesmoEventoNemDuplicaPontos() {
		af.cadastrarEstudante("Hugo", "hugo@gmail.com");
		int id = af.cadastrarPalestra("POO", "O que é POO?", "24/04/2025", 100);
 
		assertTrue(af.inscreverParticipanteEmEvento("hugo@gmail.com", id));
		assertFalse(af.inscreverParticipanteEmEvento("hugo@gmail.com", id));
 
		String detalhes = af.exibirEstudante("hugo@gmail.com");
		assertTrue(detalhes.contains("Quantidade de eventos: 1"));
		assertTrue(detalhes.endsWith("Pontos: 2"));
		assertEquals(1, af.getEmailsParticipantes(id).size());
	}
 
	@Test
	void naoInscreveEmEventoLotadoENaoDaPontos() {
		af.cadastrarEstudante("Hugo", "hugo@gmail.com");
		af.cadastrarEstudante("Diego", "diego@gmail.com");
		int id = af.cadastrarPalestra("POO", "Lotação 1", "24/04/2025", 1);
 
		assertTrue(af.inscreverParticipanteEmEvento("hugo@gmail.com", id));
		assertFalse(af.inscreverParticipanteEmEvento("diego@gmail.com", id));
 
		assertTrue(af.exibirEstudante("diego@gmail.com").contains("Quantidade de eventos: 0"));
		assertTrue(af.exibirEstudante("diego@gmail.com").endsWith("Pontos: 0"));
		assertEquals(List.of("hugo@gmail.com"), af.getEmailsParticipantes(id));
	}
 
	@Test
	void emailsDeEventoInexistenteRetornaListaVazia() {
		List<String> emails = af.getEmailsParticipantes(99);
		assertNotNull(emails);
		assertTrue(emails.isEmpty());
	}
 
	@Test
	void emailsMantemOrdemDeInscricao() {
		af.cadastrarEstudante("Hugo", "hugo@gmail.com");
		af.cadastrarEstudante("Diego", "diego@gmail.com");
		af.cadastrarEstudante("Clara", "clara@gmail.com");
		int id = af.cadastrarPalestra("POO", "O que é POO?", "24/04/2025", 100);
 
		af.inscreverParticipanteEmEvento("diego@gmail.com", id);
		af.inscreverParticipanteEmEvento("clara@gmail.com", id);
		af.inscreverParticipanteEmEvento("hugo@gmail.com", id);
 
		assertEquals(List.of("diego@gmail.com", "clara@gmail.com", "hugo@gmail.com"), af.getEmailsParticipantes(id));
	}
 
	@Test
	void alterarListaRetornadaNaoAfetaOEvento() {
		af.cadastrarEstudante("Hugo", "hugo@gmail.com");
		int id = af.cadastrarPalestra("POO", "O que é POO?", "24/04/2025", 100);
		af.inscreverParticipanteEmEvento("hugo@gmail.com", id);
 
		List<String> copia = af.getEmailsParticipantes(id);
		copia.clear();
 
		assertEquals(1, af.getEmailsParticipantes(id).size());
	}

	@Test
	void rankingSemEstudantesRetornaVazio() {
		assertEquals(0, af.listarEstudantes().length);
	}
 
	@Test
	void listagemDeEstudantesPorPontuacaoDecrescente() {
		af.cadastrarEstudante("Hugo", "Hugo@gmail.com");
		af.cadastrarEstudante("Diego", "Diego@gmail.com");
		af.cadastrarEstudante("Clara", "Clara@gmail.com");
		af.cadastrarPalestra("POO", "O que é POO?", "24/04/2025", 100); // id 1 -> 2 pts
		af.cadastrarWorkshop("Java", "Como funciona o Java?", "30/01/2025", 30, 3); // id 2 -> 3 pts
		af.cadastrarOficinaAprendizagem("Python3", "Aprendendo Python3", "04/10/2025", 25, 5, true); // id 3 -> 15 pts
		af.inscreverParticipanteEmEvento("Hugo@gmail.com", 1);
		af.inscreverParticipanteEmEvento("Diego@gmail.com", 3);
		af.inscreverParticipanteEmEvento("Clara@gmail.com", 2);
 
		assertArrayEquals(new String[] { "Diego", "Clara", "Hugo" }, af.listarEstudantes());
	}
 
	@Test
	void empateNoRankingEhDesempatadoPorNomeEmOrdemAlfabetica() {
		af.cadastrarEstudante("Zeca", "zeca@gmail.com");
		af.cadastrarEstudante("Ana", "ana@gmail.com");
		af.cadastrarEstudante("Bruno", "bruno@gmail.com");
		int id = af.cadastrarPalestra("POO", "O que é POO?", "24/04/2025", 100);
 
		af.inscreverParticipanteEmEvento("zeca@gmail.com", id);
		af.inscreverParticipanteEmEvento("ana@gmail.com", id);
		af.inscreverParticipanteEmEvento("bruno@gmail.com", id);
 
		assertArrayEquals(new String[] { "Ana", "Bruno", "Zeca" }, af.listarEstudantes());
	}
 
	@Test
	void estudantesSemPontosFicamNoFimDoRanking() {
		af.cadastrarEstudante("Ana", "ana@gmail.com");
		af.cadastrarEstudante("Bruno", "bruno@gmail.com");
		int id = af.cadastrarPalestra("POO", "O que é POO?", "24/04/2025", 100);
 
		af.inscreverParticipanteEmEvento("bruno@gmail.com", id);
 
		assertArrayEquals(new String[] { "Bruno", "Ana" }, af.listarEstudantes());
	}
 
	@Test
	void rankingSeAtualizaQuandoNovaInscricaoMudaAPontuacao() {
		af.cadastrarEstudante("Ana", "ana@gmail.com");
		af.cadastrarEstudante("Bruno", "bruno@gmail.com");
		int palestra1 = af.cadastrarPalestra("POO", "O que é POO?", "24/04/2025", 100);
		int oficina = af.cadastrarOficinaAprendizagem("Python3", "Aprendendo Python3", "04/10/2025", 25, 5, true);
 
		af.inscreverParticipanteEmEvento("ana@gmail.com", palestra1);
		af.inscreverParticipanteEmEvento("bruno@gmail.com", palestra1);
		assertArrayEquals(new String[] { "Ana", "Bruno" }, af.listarEstudantes());
 
		af.inscreverParticipanteEmEvento("bruno@gmail.com", oficina);
		assertArrayEquals(new String[] { "Bruno", "Ana" }, af.listarEstudantes());
	}
}