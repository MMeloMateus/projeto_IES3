package ClassesDTO;
import java.util.Date;
import Util.*;

public class TesteDTO {

    public static void main(String[] args) {

        //Teste Classes DTO

        PessoaDTO p = new PessoaDTO();
        PessoaDTO m = new MoradorDTO();
        System.out.println(p.getClass());
        System.out.println(m.getClass());

        AcessoDTO acesso = new AcessoDTO(12,25,45,null,TipoAcesso.ENTRADA,StatusAcesso.PERMITIDO);
        System.out.println(acesso);

        AutorizacaoDTO autorizacao = new AutorizacaoDTO(12,13,1);
        System.out.println(autorizacao);

        CasaDTO casa = new CasaDTO(1,"Rua das graças");
        System.out.println(casa);

        LocalControladoDTO localControlado = new LocalControladoDTO(3,"Academia");
        System.out.println(localControlado);


        MoradorDTO morador = new MoradorDTO(10,"Carlinhos","123",null,"123","@");
        System.out.println(morador);

        MoradorCasaDTO moradorCasa = new MoradorCasaDTO(10,5,TipoVinculo.PROPRIETARIO);
        System.out.println(moradorCasa);

        PessoaDTO pessoa = new PessoaDTO(11,"Carlão","456",null,"987","@");
        System.out.println(pessoa);

        PrestadorDTO prestador = new PrestadorDTO(11,"Carlão","456",null,"987","@","123","Carlão Supermercados");
        System.out.println(prestador);

        ServicoDTO servico = new ServicoDTO(125,10,11,"Elétrica",null,null);
        System.out.println(servico);

        VeiculoDTO veiculo = new VeiculoDTO("123",11,"vermelho","Ferrari");
        System.out.println(veiculo);

        VisitanteDTO visitante = new VisitanteDTO(13,"Carlos","789",null,"456","@",11,null);
        System.out.println(visitante);

    }
}
