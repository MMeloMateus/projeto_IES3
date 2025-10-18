package ClassesPuras;
import Util.*;

public class Teste {

    public static void main(String[] args) {

        //Teste Classes

        Pessoa p = new Pessoa();
        Pessoa m = new Morador();
        System.out.println(p.getClass());
        System.out.println(m.getClass());

        //Acesso acesso = new Acesso(12,25,45,null,TipoAcesso.ENTRADA,StatusAcesso.PERMITIDO);
        Acesso acesso = new Acesso(25,45,null,TipoAcesso.ENTRADA,StatusAcesso.PERMITIDO);
        System.out.println(acesso);

        //Autorizacao autorizacao = new Autorizacao(10,12,13,1);
        Autorizacao autorizacao = new Autorizacao(12,13,1);
        System.out.println(autorizacao);

        //Casa casa = new Casa(1,"Rua das graças");
        Casa casa = new Casa("Rua das graças");
        System.out.println(casa);

        //LocalControlado localControlado = new LocalControlado(3,"Academia");
        LocalControlado localControlado = new LocalControlado("Academia");
        System.out.println(localControlado);

        //Morador morador = new Morador(10,"Carlinhos","123",null,"123","@");
        Morador morador = new Morador("Carlinhos","123",null,"123","@");
        System.out.println(morador);

        //MoradorCasa moradorCasa = new MoradorCasa(15,10,5,TipoVinculo.PROPRIETARIO);
        MoradorCasa moradorCasa = new MoradorCasa(10,5,TipoVinculo.PROPRIETARIO);
        System.out.println(moradorCasa);

        //Pessoa pessoa = new Pessoa(11,"Carlão","456",null,"987","@");
        Pessoa pessoa = new Pessoa("Carlão","456",null,"987","@");
        System.out.println(pessoa);

        //Prestador prestador = new Prestador(11,"Carlão","456",null,"987","@","123","Carlão Supermercados");
        Prestador prestador = new Prestador("Carlão","456",null,"987","@","123","Carlão Supermercados");
        System.out.println(prestador);

        //Servico servico = new Servico(125,10,11,"Elétrica",null,null);
        Servico servico = new Servico(10,11,"Elétrica",null,null);

        System.out.println(servico);

        Veiculo veiculo = new Veiculo("123",11,"vermelho","Ferrari");
        System.out.println(veiculo);

        //Visitante visitante = new Visitante(13,"Carlos","789",null,"456","@",11,null);
        Visitante visitante = new Visitante("Carlos","789",null,"456","@",11,null);
        System.out.println(visitante);


        //Testes Enum

        StatusAcesso statusAcesso1 = StatusAcesso.PERMITIDO;
        StatusAcesso statusAcesso2 = StatusAcesso.NEGADO;

        TipoAcesso tipoAcesso1 = TipoAcesso.ENTRADA;
        TipoAcesso tipoAcesso2 = TipoAcesso.SAIDA;

        TipoVinculo tipoVinculo1 = TipoVinculo.PROPRIETARIO;
        TipoVinculo tipoVinculo2 = TipoVinculo.INQUILINO;
        TipoVinculo tipoVinculo3 = TipoVinculo.DEPENDENTE;

        TipoPessoa tipoPessoa1 = TipoPessoa.MORADOR;
        TipoPessoa tipoPessoa2 = TipoPessoa.VISITANTE;
        TipoPessoa tipoPessoa3 = TipoPessoa.PRESTADOR;

        System.out.println(statusAcesso1);
        System.out.println(statusAcesso2);
        System.out.println(tipoAcesso1);
        System.out.println(tipoAcesso2);
        System.out.println(tipoVinculo1);
        System.out.println(tipoVinculo2);
        System.out.println(tipoVinculo3);
        System.out.println(tipoPessoa1);
        System.out.println(tipoPessoa2);
        System.out.println(tipoPessoa3);
    }
}
