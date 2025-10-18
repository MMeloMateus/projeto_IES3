package ClassesDAO;

import ClassesPuras.Morador;
import ClassesPuras.Prestador;
import ClassesPuras.Servico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    // Gravar
    public int incluirServico(Servico servico) throws SQLException {

        String sql = "INSERT INTO SERVICOS (morador_Id, prestador_id, serv_tipo, data_inicio, data_fim) VALUES (?,?,?,?,?)";

        // Abre e fecha o conector
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, servico.getMorId());
            stmt.setInt(2, servico.getPrestId());
            stmt.setString(3, servico.getServTipo());
            stmt.setDate(4, (Date) servico.getDataInicio());
            stmt.setDate(5, (Date) servico.getDataFim());

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

    // Consultar Geral
    public Servico[] consultarServicos() throws SQLException {

        String sql = "SELECT * FROM SERVICOS";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {

                List<Servico> listaServico = new ArrayList<>();

                // cursor mostra a linha n-1
                while (rs.next()) {
                    Servico s = new Servico();
                    s.setId(rs.getInt("serv_id"));
                    s.setMorId(rs.getInt("morador_id"));
                    s.setPrestId(rs.getInt("prestador_id"));
                    s.setServTipo(rs.getString("serv_tipo"));
                    s.setDataInicio(rs.getDate("data_inicio"));
                    s.setDataFim(rs.getDate("data_fim"));
                    listaServico.add(s);
                }
                return listaServico.toArray(new Servico[0]);
            }
        }
    }

    // Consultar serviços relacionados a um morador(arrayList)
    public Servico[] consultarServicosMorador(Morador morador) throws SQLException {

        String sql = "SELECT A.* " +
                "FROM SERVICOS A " +
                "INNER JOIN MORADOR B " +
                "ON A.morador_id = B.morador_id" +
                "WHERE A.morador_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, morador.getId());

            try (ResultSet rs = stmt.executeQuery()) {

                List<Servico> listaServico = new ArrayList<>();

                // cursor mostra a linha n-1
                while (rs.next()) {
                    Servico s = new Servico();
                    s.setId(rs.getInt("serv_id"));
                    s.setMorId(rs.getInt("morador_id"));
                    s.setPrestId(rs.getInt("prestador_id"));
                    s.setServTipo(rs.getString("serv_tipo"));
                    s.setDataInicio(rs.getDate("data_inicio"));
                    s.setDataFim(rs.getDate("data_fim"));
                    listaServico.add(s);
                }
                return listaServico.toArray(new Servico[0]);
            }
        }
    }

    // Consultar serviços relacionados a um prestador(arrayList)
    public Servico[] consultarServicosPrestador(Prestador prestador) throws SQLException {

        String sql = "SELECT A.* " +
                "FROM SERVICOS A " +
                "INNER JOIN PRESTADORES B " +
                "ON A.prestador_id = B.prestador_id" +
                "WHERE A.prestador_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prestador.getId());

            try (ResultSet rs = stmt.executeQuery()) {

                List<Servico> listaServico = new ArrayList<>();

                // cursor mostra a linha n-1
                while (rs.next()) {
                    Servico s = new Servico();
                    s.setId(rs.getInt("serv_id"));
                    s.setMorId(rs.getInt("morador_id"));
                    s.setPrestId(rs.getInt("prestador_id"));
                    s.setServTipo(rs.getString("serv_tipo"));
                    s.setDataInicio(rs.getDate("data_inicio"));
                    s.setDataFim(rs.getDate("data_fim"));
                    listaServico.add(s);
                }
                return listaServico.toArray(new Servico[0]);
            }
        }
    }

    // Atualizar - Retorna boolean
    public boolean atualizarServico(Servico servico) throws SQLException {

        String sql = "UPDATE SERVICOS SET morador_id=?, prestador_id=?, serv_tipo=?, data_inicio=?,data_fim=? WHERE serv_id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, servico.getMorId());
            stmt.setInt(2, servico.getPrestId());
            stmt.setString(3, servico.getServTipo());
            stmt.setDate(4, (Date) servico.getDataInicio());
            stmt.setDate(4, (Date) servico.getDataFim());
            stmt.setInt(5, servico.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    // Deletar - Retorna boolean
    public boolean deletarServico(Servico servico) throws SQLException {
        String sql = "DELETE FROM SERVICOS WHERE serv_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();

             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, servico.getId());

            return stmt.executeUpdate() > 0;
        }
    }
}
