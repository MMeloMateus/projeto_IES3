package ClassesPuras;

import java.util.Date;

public class Morador extends Pessoa {

    public Morador(String nome, String cpf, Date dataNasc, String telefone, String email){
        super(nome, cpf, dataNasc, telefone, email);
    }

    public Morador(int id, String nome, String cpf, Date dataNasc, String telefone, String email, boolean ativa) {
        super(id, nome, cpf, dataNasc, telefone, email, ativa);
    }

    public Morador(){}

    @Override
    public String toString() {
        return super.toString();
    }
}
