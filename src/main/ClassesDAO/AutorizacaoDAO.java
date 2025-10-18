package ClassesDAO;

import ClassesPuras.Autorizacao;
import ClassesPuras.Pessoa;
import ClassesPuras.LocalControlado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorizacaoDAO {

    // Gravar
    public int incluirAutorizacao(Autorizacao autorizacao) throws SQLException {

        String sql = "INSERT INTO AUTORIZACOES (pessoa_id, local_id, autorizacao) VALUES (?,?,?)";

        // Abre e fecha o conector
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, autorizacao.getPesId());
            stmt.setInt(2, autorizacao.getLocId());
            stmt.setInt(3, autorizacao.getAutorizacao());

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
    public boolean atualizarAutorizacao(Autorizacao autorizacao, LocalControlado locaisControlados, boolean novoValorAutorizacao) throws SQLException {

        String sql = "UPDATE AUTORIZACOES " +
                "SET autorizacao=? " +
                "WHERE pessoa_id=? AND local_id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, novoValorAutorizacao ? 1:0 );
            stmt.setInt(2, autorizacao.getPesId());
            stmt.setInt(3, locaisControlados.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    // Deletar - Retorna boolean
    public boolean deletarAutorizacao(Autorizacao autorizacao, LocalControlado locaisControlados,Pessoa pessoa) throws SQLException {

        String sql = "DELETE FROM AUTORIZACOES WHERE pessoa_id=? AND local_id=?";

        try (Connection conn = ConnectionFactory.getConnection();

             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pessoa.getId());
            stmt.setInt(2, locaisControlados.getId());


            return stmt.executeUpdate() > 0;
        }
    }

    // Consultar autorizações relacionas a uma pessoa(arrayList)
    public Autorizacao[] consultarAutorizacoesPorPessoa(Pessoa pessoa) throws SQLException {

        String sql = "SELECT A.*, C.local_nome " +
                "FROM AUTORIZACOES A " +
                "INNER JOIN PESSOAS B ON A.pessoa_id = B.pessoa_id " +
                "INNER JOIN LOCAIS_CONTROLADOS C ON A.local_id = C.local_id " +
                "WHERE A.pessoa_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pessoa.getId());

            try (ResultSet rs = stmt.executeQuery()) {

                List<Autorizacao> listaAutorizacao = new ArrayList<>();

                // cursor mostra a linha n-1

                while (rs.next()) {
                    Autorizacao a = new Autorizacao();
                    a.setPesId(rs.getInt("pessoa_id"));
                    a.setLocId(rs.getInt("local_id"));
                    a.setAutorizacao(rs.getInt("autorizacao"));
                    listaAutorizacao.add(a);
                }
                return listaAutorizacao.toArray(new Autorizacao[0]);
            }
        }
    }

    // Consultar autorizações relacionadas a um local(arrayList)
    public Autorizacao[] consultarAutorizacoesPorLocal(LocalControlado locaisControlados) throws SQLException {

        String sql = "SELECT A.* " +
                "FROM AUTORIZACOES A " +
                "INNER JOIN LOCAIS_CONTROLADOS B " +
                "ON A.local_id = B.local_id" +
                "WHERE A.local_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, locaisControlados.getId());
            stmt.setInt(2, locaisControlados.getId());
            stmt.setInt(3, locaisControlados.getId());

            try (ResultSet rs = stmt.executeQuery()) {

                List<Autorizacao> listaAutorizacao = new ArrayList<>();

                // cursor mostra a linha n-1

                // AUTORIZACOES
                // pessoa_id INT
                // local_id INT
                // autorizacao TINYINT
                while (rs.next()) {
                    Autorizacao a = new Autorizacao();
                    a.setPesId(rs.getInt("pessoa_id"));
                    a.setLocId(rs.getInt("local_id"));
                    a.setAutorizacao(rs.getInt("autorizacao"));
                    listaAutorizacao.add(a);
                }
                return listaAutorizacao.toArray(new Autorizacao[0]);
            }
        }
    }
}
