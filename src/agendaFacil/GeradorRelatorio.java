package agendaFacil;

public interface GeradorRelatorio<T, R> {
    R gerar(T dados);
}
