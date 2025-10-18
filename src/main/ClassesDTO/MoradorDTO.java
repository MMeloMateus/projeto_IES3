package ClassesDTO;

import java.util.Date;

public class MoradorDTO extends PessoaDTO {

    public MoradorDTO(String nome, String cpf, Date dataNasc, String telefone, String email){
        super(nome, cpf, dataNasc, telefone, email);
    }

    public MoradorDTO(int id, String nome, String cpf, Date dataNasc, String telefone, String email, boolean ativa) {
        super(id, nome, cpf, dataNasc, telefone, email, ativa);
    }

    public MoradorDTO(){
    };

    @Override
    public String toString() {
        return super.toString();
    }
}
