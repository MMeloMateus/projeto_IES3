package ClassesDTO;

import Util.TipoVinculo;

public class MoradorCasaDTO {
    private int id;
    private int moradorId;
    private int casaId;
    private TipoVinculo tipoVinculo;

    public MoradorCasaDTO(int moradorId, int casaId, TipoVinculo tipoVinculo) {
        this.moradorId = moradorId;
        this.casaId = casaId;
        this.tipoVinculo = tipoVinculo;
    }

    public MoradorCasaDTO(int id,int moradorId, int casaId, TipoVinculo tipoVinculo){
        this(moradorId,casaId,tipoVinculo);
        this.id = id;
    }

    public MoradorCasaDTO(){
    };

    public int getMoradorId() {
        return moradorId;
    }

    public void setMoradorId(int moradorId) {
        this.moradorId = moradorId;
    }

    public int getCasaId() {
        return casaId;
    }

    public void setCasaId(int casaId) {
        this.casaId = casaId;
    }

    public TipoVinculo getTipoVinculo() {
        return tipoVinculo;
    }

    public void setTipoVinculo(TipoVinculo tipoVinculo) {
        this.tipoVinculo = tipoVinculo;
    }

    @Override
    public String toString() {
        return "MoradorCasaDTO{" +
                "id=" + id +
                ", moradorId=" + moradorId +
                ", casaId=" + casaId +
                ", tipoVinculo=" + tipoVinculo +
                '}';
    }
}


