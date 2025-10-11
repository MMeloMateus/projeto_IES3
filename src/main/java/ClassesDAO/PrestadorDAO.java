import java.ClassesPuras.Prestador;
import java.ClassesPuras.BasePessoaDAO;
import java.sql.*;

public class PrestadorDAO extends BasePessoaDAO{

    // Gravar
    public int incluirPrestador(Prestador prestador) throws SQLException {

        String sql = "INSERT INTO PRESTADORES (prestador_cnpj, prestador_empresa, prestador_id) VALUES (?,?,?)";

        // Abre e fecha o conector
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, prestador.getCnpj());
            stmt.setString(2, prestador.getEmpresa());
            stmt.setInt(3, prestador.getId());

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
    public Prestador consultarPrestador(Prestador prestador) throws SQLException {

        String sql = "SELECT * FROM PRESTADORES WHERE prestador_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prestador.getId());

            try (ResultSet rs = stmt.executeQuery()) {

                // cursor mostra a linha n-1
                if (rs.next()) {
                    Prestador p = new Prestador();
                    p.setNome(rs.getString("prestador_cnpj"));
                    p.setCpf(rs.getString("prestador_empresa"));
                    return p;
                }
                return null;
            }
        }
    }

    // Atualizar - Retorna boolean
    public boolean atualizarPrestador(Prestador prestador) throws SQLException {

        String sql = "UPDATE PRESTADORES SET prestador_cnpj=?, prestador_empresa=? WHERE prestador_id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, prestador.getCnpj());
            stmt.setString(2, prestador.getEmpresa());
            stmt.setInt(3, prestador.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    // Deletar - Retorna boolean
    public boolean deletarPrestador(Prestador prestador) throws SQLException {
        String sql = "DELETE * FROM PRESTADORES WHERE prestador_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();

             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prestador.getId());

            return stmt.executeUpdate() > 0;
        }
    }
}