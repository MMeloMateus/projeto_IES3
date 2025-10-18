package ClassesDAO;

import ClassesPuras.MoradorCasa;
import ClassesPuras.Pessoa;
import ClassesPuras.Morador;
import ClassesPuras.Casa;
import Util.TipoVinculo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoradorCasaDAO {

    // Gravar
    public int incluirMoradorCasa(MoradorCasa moradorCasa) throws SQLException {

        String sql = "INSERT INTO MORADOR_CASA (morador_Id, casa_id, tipo_vinculo ) VALUES (?,?,?)";

        // Abre e fecha o conector
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, moradorCasa.getMoradorId());
            stmt.setInt(2, moradorCasa.getCasaId());
            stmt.setString(3,  moradorCasa.getTipoVinculo().name());

            // Linha afetada pela ação
            int affectedRows = stmt.executeUpdate();

            // Cursos sempre fica um antes
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // id gerado
                }
            }

            // Retornar a linha afetada pela ação
            return affectedRows;
        }
    }

    // Consultar
    public MoradorCasa consultarMoradorCasa(MoradorCasa moradorCasa) throws SQLException {

        String sql = "SELECT * FROM MORADOR_CASA WHERE casa_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, moradorCasa.getCasaId());

            try (ResultSet rs = stmt.executeQuery()) {

                // cursor mostra a linha n-1
                if (rs.next()) {
                    MoradorCasa mc = new MoradorCasa();
                    mc.setMoradorId(rs.getInt("morador_Id"));
                    mc.setCasaId(rs.getInt("casa_id"));
                    mc.setTipoVinculo(
                            TipoVinculo.valueOf(
                                    rs.getString("tipo_vinculo")
                            )
                    );
                    return mc;
                }
                return null;
            }
        }
    }

    // Consultar Casas relacionada a um Morador
    public Casa[] consultarCasasMorador(Morador morador) throws SQLException {

        String sql =  "SELECT A.* " +
                "FROM PESSOAS A " +
                "INNER JOIN MORADORCASA B ON A.pessoa_id = B.pessoa_id " +
                "WHERE B.pessoa_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, morador.getId());

            try (ResultSet rs = stmt.executeQuery()) {

                List<Casa> listaCasa = new ArrayList<>();

                // cursor mostra a linha n-1
                while (rs.next()) {
                    Casa c = new Casa();
                    c.setEndereco(rs.getString("casa_ender"));
                    c.setId(rs.getInt("casa_id"));
                    listaCasa.add(c);
                }
                return listaCasa.toArray(new Casa[0]);
            }
        }
    }

    // Consultar Pessoas relacionada a uma Casa
    public Pessoa[] consultarMoradoresCasa(Casa casa) throws SQLException {

        String sql =  "SELECT A.* " +
                "FROM PESSOAS A " +
                "INNER JOIN MORADORCASA B ON A.pessoa_id = B.pessoa_id " +
                "WHERE A.casa_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, casa.getId());

            try (ResultSet rs = stmt.executeQuery()) {

                List<Pessoa> listaPessoas = new ArrayList<>();

                // cursor mostra a linha n-1
                while (rs.next()) {
                    Pessoa p = new Pessoa();
                    p.setId(rs.getInt("pessoa_id"));
                    p.setNome(rs.getString("pessoa_nome"));
                    p.setCpf(rs.getString("pessoa_cpf"));
                    p.setEmail(rs.getString("pessoa_email"));
                    p.setTelefone(rs.getString("pessoa_telefone"));
                    Date d = rs.getDate("pessoa_data_nasc");
                    p.setDataNasc(d);
                    listaPessoas.add(p);
                }
                return listaPessoas.toArray(new Pessoa[0]);
            }
        }
    }

    // Atualizar - Retorna boolean
    public boolean atualizarMoradorCasa(MoradorCasa moradorCasa) throws SQLException {

        String sql = "UPDATE MORADOR_CASA SET morador_Id=?, casa_id=?, tipo_vinculo=? WHERE casa_id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, moradorCasa.getMoradorId());
            stmt.setInt(2, moradorCasa.getCasaId());
            stmt.setString(3, moradorCasa.getTipoVinculo().name());
            stmt.setInt(4, moradorCasa.getCasaId());

            return stmt.executeUpdate() > 0;
        }
    }

    // Deletar - Retorna boolean
    public boolean deletarMoradorCasa(MoradorCasa moradorCasa) throws SQLException {
        String sql = "DELETE FROM MORADOR_CASA WHERE morador_Id = ? and casa_id=? ";
        try (Connection conn = ConnectionFactory.getConnection();

             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, moradorCasa.getMoradorId());
            stmt.setInt(2, moradorCasa.getCasaId());

            return stmt.executeUpdate() > 0;
        }
    }

}
