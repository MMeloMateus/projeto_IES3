package ClassesDTO;

public class LocalControladoDTO {

    private int id;
    private String nome;

    public LocalControladoDTO(String nome){
        this.nome = nome;
    }
    public LocalControladoDTO(int id, String nome) {
        this(nome);
        this.id = id;
    }

    public LocalControladoDTO(){};

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

    @Override
    public String toString() {
        return "LocalControladoDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}

