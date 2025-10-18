package ClassesDTO;

public class AutorizacaoDTO {
    private int id;
    private int pesId;
    private int locId;
    private int autorizacao;

    public AutorizacaoDTO(int pesId, int locId, int autorizacao) {
        this.pesId = pesId;
        this.locId = locId;
        this.autorizacao = autorizacao;
    }

    public AutorizacaoDTO(int id,int pesId, int locId, int autorizacao ){
        this(pesId,locId,autorizacao);
        this.id = id;
    }

    public AutorizacaoDTO() {
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

    public int getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(int autorizacao) {
        this.autorizacao = autorizacao;
    }

    @Override
    public String toString() {
        return "AutorizacaoDTO{" +
                "id=" + id +
                ", pesId=" + pesId +
                ", locId=" + locId +
                ", autorizacao=" + autorizacao +
                '}';
    }
}