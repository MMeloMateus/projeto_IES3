package ClassesDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ClassesPuras.LocalControlado;

public class LocalControladoDAO {

    // Gravar
    public int incluirLocalControlado(LocalControlado localControlado) throws SQLException {

        String sql = "INSERT INTO LOCAIS_CONTROLADOS (local_nome) VALUES (?)";

        // Abre e fecha o conector
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, localControlado.getNome());

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

    // Atualizar - Retorna boolean
    public boolean atualizarLocalControlado(LocalControlado localControlado) throws SQLException {

        String sql = "UPDATE LOCAIS_CONTROLADOS SET autorizacao=? WHERE local_nome=? ";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, localControlado.getNome());

            return stmt.executeUpdate() > 0;
        }
    }

    // Consultar
    public LocalControlado consultarLocalControlado(LocalControlado localControlado) throws SQLException {

        String sql = "SELECT * FROM LOCAIS_CONTROLADOS WHERE local_nome = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, localControlado.getNome());

            try (ResultSet rs = stmt.executeQuery()) {

                // cursor mostra a linha n-1
                if (rs.next()) {
                    LocalControlado l = new LocalControlado();
                    l.setId(rs.getInt("local_id"));
                    l.setNome(rs.getString("local_id"));
                    return l;
                }
                return null;
            }
        }
    }

    // Consultar de forma geral
    public LocalControlado[] consultarLocaisControladosGeral(LocalControlado localControlado) throws SQLException {

        String sql = "SELECT * FROM LOCAIS_CONTROLADOS";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, localControlado.getNome());

            try (ResultSet rs = stmt.executeQuery()) {

                List<LocalControlado> listaLocaisControlados = new ArrayList<>();

                // cursor mostra a linha n-1
                while (rs.next()) {
                    LocalControlado l = new LocalControlado();
                    l.setId(rs.getInt("local_id"));
                    l.setNome(rs.getString("local_id"));
                    listaLocaisControlados.add(l);
                }
                return listaLocaisControlados.toArray(new LocalControlado[0]);
            }
        }
    }

    // Deletar - Retorna boolean
    public boolean deletarLocalControlado(LocalControlado localControlado) throws SQLException {

        String sql = "DELETE FROM LOCAIS_CONTROLADOS WHERE local_nome = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, localControlado.getNome());

            return stmt.executeUpdate() > 0;
        }
    }

}
