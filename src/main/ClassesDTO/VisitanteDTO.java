package ClassesDTO;

import java.util.Date;

public class VisitanteDTO extends PessoaDTO {
    private int morCadastraId;
    private Date dataAutorizacao;

    public VisitanteDTO(String nome, String cpf, Date dataNasc, String telefone, String email, int morCadastraId, Date dataAutorizacao){
        super(nome, cpf, dataNasc, telefone, email);
        this.morCadastraId = morCadastraId;
        this.dataAutorizacao = dataAutorizacao;
    }

    public VisitanteDTO(int id, String nome, String cpf, Date dataNasc, String telefone, String email, boolean ativa, int morCadastraId, Date dataAutorizacao) {
        super(id, nome, cpf, dataNasc, telefone, email, ativa);
        this.morCadastraId = morCadastraId;
        this.dataAutorizacao = dataAutorizacao;
    }

    public VisitanteDTO(){}

    public int getMorCadastraId() {
        return morCadastraId;
    }

    public void setMorCadastraId(int morCadastraId) {
        this.morCadastraId = morCadastraId;
    }

    public Date getDataAutorizacao() {
        return dataAutorizacao;
    }

    public void setDataAutorizacao(Date dataAutorizacao) {
        this.dataAutorizacao = dataAutorizacao;
    }

    @Override
    public String toString() {
        return super.toString() +
                "VisitanteDTO{" +
                "morCadastraId=" + morCadastraId +
                ", dataAutorizacao=" + dataAutorizacao +
                '}';
    }
}
