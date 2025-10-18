package ClassesDTO;

import java.util.Date;

public class PrestadorDTO extends PessoaDTO {
    private String cnpj;
    private String empresa;

    public PrestadorDTO(String nome, String cpf, Date dataNasc, String telefone, String email, String cnpj, String empresa) {
        super(nome, cpf, dataNasc, telefone, email);
        this.cnpj = cnpj;
        this.empresa = empresa;
    }

    public PrestadorDTO(int id, String nome, String cpf, Date dataNasc, String telefone, String email, boolean ativa, String cnpj, String empresa) {
        super(id, nome, cpf, dataNasc, telefone, email, ativa);
        this.cnpj = cnpj;
        this.empresa = empresa;
    }

    public PrestadorDTO() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "PrestadorDTO{" +
                "cnpj='" + cnpj + '\'' +
                ", empresa='" + empresa + '\'' +
                '}';
    }
}
