package ClassesDTO;

import java.util.Date;

public class PessoaDTO {
    private int id;
    private String nome;
    private String cpf;
    private Date dataNasc;
    private String telefone;
    private String email;
    private boolean ativa;

    public PessoaDTO(int id, String nome, String cpf, Date dataNasc, String telefone, String email, boolean ativa) {
        this(nome,cpf,dataNasc,telefone,email);
        this.id = id;
        this.ativa = ativa;
    }

    public PessoaDTO(String nome, String cpf, Date dataNasc, String telefone, String email){
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.telefone = telefone;
        this.email = email;
    }

    public PessoaDTO(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PessoaDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNasc=" + dataNasc +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", ativa=" + ativa +
                '}';
    }
}
