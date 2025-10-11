import java.ClassesPuras.Acesso;
import java.ClassesPuras.LocaisControlados;
import java.ClassesPuras.Pessoas;

public class AcessoDAO {

    // Gravar
    // TODO
    // Tenho que analisar e pensar em como vou fazer para gravar o tipo de Entrada, Data e Status
    // Data estou pensando em deixar no SGBD, mas depois vou pensar nessa questão
    public int incluirAcesso(Pessoas pessoas,LocaisControlados locaisControlados) throws SQLException {

        String sql = "INSERT INTO ACESSOS (local_id, pessoa_id ) VALUES (?,?)";

        // Abre e fecha o conector
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, locaisControlados.getLocalId());
            stmt.setInt(2, pessoas.getId());

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

    // Consultar Geral - Consulta todos os acessos
    // Obs: Vai dar ruim... Eu tô avisando... Isso não vai ser escalável...
    public List<Acesso> consultarAcessoGeral() throws SQLException {

        List<Acesso> listaAcesso = new ArrayList<>();

        String sql = "SELECT *  FROM ACESSOS";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // cursor mostra a linha n-1
            while (rs.next()) {
                Acesso a = new Acesso();
                a.setId(rs.getInt("acesso_id"));
                a.setLocId(rs.getInt("local_id"));
                a.getPesId(rs.getInt("pessoa_id"));
                a.setTipoAcesso(rs.getString("acesso_tipo"));
                a.setStatusAcesso(rs.getString("acesso_status"));

                //Conversão de Data
                a.setData(rs.getDate("acesso_data") == null ? null : new java.util.Date(rs.getDate("acesso_data").getTime()));

                // Adiciona a pessoa
                listaAcesso.add(a);
            }
            return listaAcesso;
        }
    }


    // Consultar por Pessoa (Objeto)
    public Acesso consultarAcessoPessoa(Pessoas pessoas) throws SQLException {

        String sql = "SELECT *  FROM ACESSOS WHERE pessoa_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {

                // cursor mostra a linha n-1
                while (rs.next()) {
                    Acesso a = new Acesso();
                    a.setId(rs.getInt("acesso_id"));
                    a.setLocId(rs.getInt("local_id"));
                    a.getPesId(rs.getInt("pessoa_id"));
                    a.setTipoAcesso(rs.getString("acesso_tipo"));
                    a.setStatusAcesso(rs.getString("acesso_status"));

                    //Conversão de Data
                    a.setData(rs.getDate("acesso_data") == null ? null : new java.util.Date(rs.getDate("acesso_data").getTime()));

                    // Adiciona o Acesso
                    listaAcesso.add(a);
                }
                return listaAcesso;
            }
        }
    }

    // Consultar por Locais (Objeto)
    public Acesso consultarAcessoLocaisControlados(LocaisControlados locaisControlados) throws SQLException {

        String sql = "SELECT *  FROM ACESSOS WHERE local_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {

                // cursor mostra a linha n-1
                while (rs.next()) {
                    Acesso a = new Acesso();

                    a.setId(rs.getInt("acesso_id"));
                    a.setLocId(rs.getInt("local_id"));
                    a.getPesId(rs.getInt("pessoa_id"));
                    a.setTipoAcesso(rs.getString("acesso_tipo"));
                    a.setStatusAcesso(rs.getString("acesso_status"));
                    //Conversão de Data
                    a.setData(rs.getDate("acesso_data") == null ? null : new java.util.Date(rs.getDate("acesso_data").getTime()));

                    // Adiciona o Acesso
                    listaAcesso.add(a);
                }
                return listaAcesso;
            }
        }
    }

    // Consultar Acesso Ordenados por Data - Mais recente Primeiro
    public List<Acesso> consultarAcessoGeralOrdenado() throws SQLException {

        List<Acesso> listaAcesso = new ArrayList<>();

        String sql = "SELECT * FROM ACESSOS ORDER BY acesso_data DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // cursor mostra a linha n-1
            while (rs.next()) {
                Acesso a = new Acesso();
                a.setId(rs.getInt("acesso_id"));
                a.setLocId(rs.getInt("local_id"));
                a.setPesId(rs.getInt("pessoa_id"));
                a.setTipoAcesso(rs.getString("acesso_tipo"));
                a.setStatusAcesso(rs.getString("acesso_status"));

                //Conversão de Data
                a.setData(rs.getDate("acesso_data") == null ? null : new java.util.Date(rs.getDate("acesso_data").getTime()));

                // Adiciona a pessoa
                listaAcesso.add(a);
            }
            return listaAcesso;
        }
    }

}