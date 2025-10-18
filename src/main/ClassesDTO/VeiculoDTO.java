package ClassesDTO;

public class VeiculoDTO {
    private String placa;
    private int pesId;
    private String cor;
    private String modelo;

    public VeiculoDTO(String placa, int pesId, String cor, String modelo) {
        this.placa = placa;
        this.pesId = pesId;
        this.cor = cor;
        this.modelo = modelo;
    }

    public VeiculoDTO() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getPesId() {
        return pesId;
    }

    public void setPesId(int pesId) {
        this.pesId = pesId;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "VeiculoDTO{" +
                "placa='" + placa + '\'' +
                ", pesId=" + pesId +
                ", cor='" + cor + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}
