package java.ClassesDAO;

import java.ClassesPuras.Pessoa;
import java.sql.*;

public abstract class BasePessoaDAO {

    // Gravar
    public int incluirPessoa(Pessoa pessoa) throws SQLException {

        String sql = "INSERT INTO PESSOAS (pessoa_nome, pessoa_cpf, pessoa_email, pessoa_telefone, pessoa_data_nasc) VALUES (?,?,?,?,?)";

        // Abre e fecha o conector
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCpf());
            stmt.setString(3, pessoa.getEmail());
            stmt.setString(4, pessoa.getTelefone());
            stmt.setDate(5, new java.sql.Date(pessoa.getDataNasc().getTime()));

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

    // Consultar Geral - Consulta todas as pessoa
    // Obs: Vai dar ruim... Eu tô avisando... Isso não vai ser escalável...
    public List<Pessoa> consultarPessoaGeral() throws SQLException {

        List<Pessoa> listaPessoas = new ArrayList<>();

        String sql = "SELECT *  FROM PESSOAS ";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

                // cursor mostra a linha n-1
                while (rs.next()) {
                    Pessoa p = new Pessoa();
                    p.setId(rs.getInt("pessoa_id"));
                    p.setNome(rs.getString("pessoa_nome"));
                    p.setCpf(rs.getString("pessoa_cpf"));
                    p.setEmail(rs.getString("pessoa_email"));
                    p.setTelefone(rs.getString("pessoa_telefone"));

                    //Conversão de Data
                    p.setDataNasc(rs.getDate("pessoa_data_nasc") == null ? null : new java.util.Date(rs.getDate("pessoa_data_nasc").getTime()));

                    // Adiciona a pessoa
                    listaPessoas.add(p);
                }
                return listaPessoas;
            }
        }


    // Consultar por CPF - Retorna um objeto do tipo pessoa
    public Pessoa consultarPessoaCpf(int cpf) throws SQLException {

        String sql = "SELECT *  FROM PESSOAS WHERE pessoa_cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {

                // cursor mostra a linha n-1
                if (rs.next()) {
                    Pessoa p = new Pessoa();
                    p.setId(rs.getInt("pessoa_id"));
                    p.setNome(rs.getString("pessoa_nome"));
                    p.setCpf(rs.getString("pessoa_cpf"));
                    p.setEmail(rs.getString("pessoa_email"));
                    p.setTelefone(rs.getString("pessoa_telefone"));
                    Date d = rs.getDate("pessoa_data_nasc");
                    return p;
                }
                return null;
            }
        }
    }

    // Consultar por Nome - Retorna um objeto do tipo pessoa
    public Pessoa consultarPessoaNome(String pessoa_nome) throws SQLException {

        String sql = "SELECT *  FROM PESSOAS WHERE pessoa_nome = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pessoa_nome);

            try (ResultSet rs = stmt.executeQuery()) {

                // cursor mostra a linha n-1
                if (rs.next()) {
                    Pessoa p = new Pessoa();
                    p.setId(rs.getInt("pessoa_id"));
                    p.setNome(rs.getString("pessoa_nome"));
                    p.setCpf(rs.getString("pessoa_cpf"));
                    p.setEmail(rs.getString("pessoa_email"));
                    p.setTelefone(rs.getString("pessoa_telefone"));
                    Date d = rs.getDate("pessoa_data_nasc");
                    return p;
                }
                return null;
            }
        }
    }

    // Consultar por Tipo - Retorna um objeto do tipo pessoa
    // TODO
    public void consultarPessoaTipo(int cpf) throws SQLException {

    }

    // Atualizar - Retorna boolean
    public boolean atualizarPessoa(Pessoa pessoa) throws SQLException {

        String sql = "UPDATE PESSOAS SET pessoa_nome=?, pessoa_cpf=?, pessoa_email=?, pessoa_telefone=?, pessoa_data_nasc=? WHERE pessoa_id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCpf());
            stmt.setString(3, pessoa.getEmail());
            stmt.setString(4, pessoa.getTelefone());
            stmt.setDate(5, new java.sql.Date(pessoa.getDataNasc().getTime()));
            stmt.setInt(6, pessoa.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    // Deletar - Retorna boolean
    public boolean deletarPessoa(Pessoa pessoa) throws SQLException {
        String sql = "DELETE * FROM PESSOAS WHERE pessoa_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1,pessoa.getId());
            return stmt.executeUpdate() > 0;
        }
    }
}