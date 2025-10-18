package ClassesDTO;

public class CasaDTO{
    private int id;
    private String endereco;

    public CasaDTO(String endereco){
        this.endereco = endereco;
    }

    public CasaDTO(int id, String endereco) {
        this(endereco);
        this.id = id;
    }

    public CasaDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "CasaDTO{" +
                "id=" + id +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
