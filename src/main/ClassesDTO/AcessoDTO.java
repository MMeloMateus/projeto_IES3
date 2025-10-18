package ClassesDTO;

import Util.TipoAcesso;
import Util.StatusAcesso;
import java.sql.Timestamp;

public class AcessoDTO {
    private int id;
    private int pesId;
    private int locId;
    private Timestamp data;
    private TipoAcesso tipoAcesso;
    private StatusAcesso statusAcesso;

    public AcessoDTO(int pesId, int locId, Timestamp data, TipoAcesso tipoAcesso, StatusAcesso statusAcesso){
        this.pesId = pesId;
        this.locId = locId;
        this.data = data;
        this.tipoAcesso = tipoAcesso;
        this.statusAcesso = statusAcesso;
    }

    public AcessoDTO(int id, int pesId, int locId, Timestamp data, TipoAcesso tipoAcesso, StatusAcesso statusAcesso) {
        this(pesId, locId, data, tipoAcesso, statusAcesso);
        this.id = id;
    }

    public AcessoDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPesId() {
        return pesId;
    }

    public void setPesId(int pesId) {
        this.pesId = pesId;
    }

    public int getLocId() {
        return locId;
    }

    public void setLocId(int locId) {
        this.locId = locId;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public TipoAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(TipoAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public StatusAcesso getStatusAcesso() {
        return statusAcesso;
    }

    public void setStatusAcesso(StatusAcesso statusAcesso) {
        this.statusAcesso = statusAcesso;
    }

    @Override
    public String toString() {
        return "AcessoDTO{" +
                "id=" + id +
                ", pesId=" + pesId +
                ", locId=" + locId +
                ", data=" + data +
                ", tipoAcesso=" + tipoAcesso +
                ", statusAcesso=" + statusAcesso +
                '}';
    }
}
