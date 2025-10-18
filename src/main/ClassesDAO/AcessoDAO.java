package ClassesDAO;

import Util.StatusAcesso;
import Util.TipoAcesso;

import ClassesPuras.Acesso;
import ClassesPuras.LocalControlado;
import ClassesPuras.Pessoa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcessoDAO {

    // Gravar
    public int incluirAcesso(Acesso acesso) throws SQLException {

        String sql = "INSERT INTO ACESSOS (local_id, pessoa_id, acesso_data, acesso_tipo, acesso_status) VALUES (?,?,?,?,?)";

        // Abre e fecha o conector
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, acesso.getLocId());
            stmt.setInt(2, acesso.getPesId());
            stmt.setTimestamp(3, acesso.getData());
            //stmt.setTimestamp(3, Timestamp.valueOf(acesso.getData()));
            stmt.setString(4, acesso.getTipoAcesso().name());

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
    public Acesso consultarAcesso(Acesso acesso) throws SQLException {

        String sql = "SELECT * FROM ACESSOS WHERE acesso_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, acesso.getId());

            try (ResultSet rs = stmt.executeQuery()) {

                // cursor mostra a linha n-1
                if (rs.next()) {
                    Acesso a = new Acesso();
                    a.setId(rs.getInt("acesso_id"));
                    a.setLocId(rs.getInt("local_id"));
                    a.setPesId(rs.getInt("pessoa_id"));
                    a.setData(rs.getTimestamp("acesso_data"));
                    a.setTipoAcesso(TipoAcesso.valueOf(rs.getString("acesso_tipo")));
                    a.setStatusAcesso(StatusAcesso.valueOf(rs.getString("acesso_status")));
                    return a;
                }
                return null;
            }
        }
    }

    // Consultar relacionados a Local
    public Acesso[] consultarAcessosPorLocal(LocalControlado local) throws SQLException {

        String sql = "SELECT * FROM ACESSOS WHERE local_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, local.getId());

            try (ResultSet rs = stmt.executeQuery()) {

                List<Acesso> listaAcessos = new ArrayList<>();

                // cursor mostra a linha n-1
                while (rs.next()) {
                    Acesso a = new Acesso();
                    a.setId(rs.getInt("acesso_id"));
                    a.setLocId(rs.getInt("local_id"));
                    a.setPesId(rs.getInt("pessoa_id"));
                    a.setData(rs.getTimestamp("acesso_data"));
                    a.setTipoAcesso(TipoAcesso.valueOf(rs.getString("acesso_tipo")));
                    a.setStatusAcesso(StatusAcesso.valueOf(rs.getString("acesso_status")));
                    listaAcessos.add(a);
                }
                return listaAcessos.toArray(new Acesso[0]);
            }
        }
    }

    // Consultar relacionados a Pessoas
    public Acesso[] consultarAcessosPorPessoa(Pessoa pessoa) throws SQLException {

        String sql = "SELECT * FROM ACESSOS WHERE pessoa_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pessoa.getId());

            try (ResultSet rs = stmt.executeQuery()) {

                List<Acesso> listaAcessos = new ArrayList<>();

                // cursor mostra a linha n-1
                while (rs.next()) {
                    Acesso a = new Acesso();
                    a.setId(rs.getInt("acesso_id"));
                    a.setLocId(rs.getInt("local_id"));
                    a.setPesId(rs.getInt("pessoa_id"));
                    a.setData(rs.getTimestamp("acesso_data"));
                    a.setTipoAcesso(TipoAcesso.valueOf(rs.getString("acesso_tipo")));
                    a.setStatusAcesso(StatusAcesso.valueOf(rs.getString("acesso_status")));
                    listaAcessos.add(a);
                }
                return listaAcessos.toArray(new Acesso[0]);
            }
        }
    }

    // Consultar relacionados a Pessoas
    // Necessidade de Melhora no Try
    public Acesso[] consultarAcessosOrderData( ) throws SQLException {

        String sql = "SELECT * FROM ACESSOS ORDER BY acesso_data ASC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {

                List<Acesso> listaAcessos = new ArrayList<>();

                // cursor mostra a linha n-1
                while (rs.next()) {
                    Acesso a = new Acesso();
                    a.setId(rs.getInt("acesso_id"));
                    a.setLocId(rs.getInt("local_id"));
                    a.setPesId(rs.getInt("pessoa_id"));
                    a.setData(rs.getTimestamp("acesso_data"));
                    a.setTipoAcesso(TipoAcesso.valueOf(rs.getString("acesso_tipo")));
                    a.setStatusAcesso(StatusAcesso.valueOf(rs.getString("acesso_status")));
                    listaAcessos.add(a);
                }
                return listaAcessos.toArray(new Acesso[0]);
            }
        }
    }
}
