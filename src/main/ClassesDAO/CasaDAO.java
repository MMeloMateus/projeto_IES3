package ClassesDAO;

import ClassesPuras.Casa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CasaDAO {
    // Gravar
    public int incluirCasa(Casa casa) throws SQLException {

        String sql = "INSERT INTO CASAS (casa_ender) VALUES (?)";

        // Abre e fecha o conector
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, casa.getEndereco());

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
    public Casa consultarCasa(Casa casa) throws SQLException {

        String sql = "SELECT * FROM CASAS WHERE casa_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, casa.getId());

            try (ResultSet rs = stmt.executeQuery()) {

                // cursor mostra a linha n-1
                if (rs.next()) {
                    Casa c = new Casa();
                    c.setId(rs.getInt("casa_id"));
                    c.setEndereco(rs.getString("casa_ender"));
                    return c;
                }
                return null;
            }
        }
    }

    // Deletar - Retorna boolean
    public boolean deletarCasa(Casa casa) throws SQLException {
        String sql = "DELETE FROM CASAS WHERE casa_id=? ";
        try (Connection conn = ConnectionFactory.getConnection();

             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, casa.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    // Consultar casas no geral (arrayList)
    public Casa[] consultarCasaGeral(Casa casa) throws SQLException {

        String sql = "SELECT * FROM CASAS";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {

                List<Casa> listaCasa = new ArrayList<>();

                // cursor mostra a linha n-1
                while (rs.next()) {
                    Casa c = new Casa();
                    c.setId(rs.getInt("casa_id"));
                    c.setEndereco(rs.getString("casa_ender"));
                    listaCasa.add(c);
                }
                return listaCasa.toArray(new Casa[0]);
            }
        }
    }
}
