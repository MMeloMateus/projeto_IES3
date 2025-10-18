package ClassesDAO;

import ClassesPuras.Pessoa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    // Gravar
    protected int incluirPessoa(Pessoa pessoa) throws SQLException {

        String sql = "INSERT INTO PESSOAS (pessoa_nome, pessoa_cpf, pessoa_email, pessoa_telefone, pessoa_data_nasc) VALUES (?,?,?,?,?)";

        // Abre e fecha o conector
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

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

    // Consultar Geral - Retorna um arrayList
    public Pessoa[] consultarPessoasGeral( ) throws SQLException {

        String sql = "SELECT *  FROM PESSOAS";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {

                List<Pessoa> listaPessoas = new ArrayList<>();

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

    // Consultar por CPF - Retorna um objeto do tipo pessoa
    public Pessoa consultarPessoaCpf(String cpf) throws SQLException {

        String sql = "SELECT *  FROM PESSOAS WHERE pessoa_cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

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
                    p.setDataNasc(d);
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
                    p.setDataNasc(d);
                    return p;
                }
                return null;
            }
        }
    }

    public Pessoa[] consultarPessoasPorTipo(String tipo){

        String sql;

        if(tipo.equalsIgnoreCase("MORADOR")){
            sql = "SELECT A.* FROM PESSOAS A INNER JOIN MORADOR B ON A.pessoa_id = B.pessoa_id";
        } else if (tipo.equalsIgnoreCase("PRESTADOR")){
            sql = "SELECT A.* FROM PESSOAS A INNER JOIN PRESTADOR B ON A.pessoa_id = B.pessoa_id";
        } else if(tipo.equalsIgnoreCase("VISITANTE")){
            sql = "SELECT A.* FROM PESSOAS A INNER JOIN VISITANTE B ON A.pessoa_id = B.pessoa_id";
        } else {
            throw new IllegalArgumentException("Tipo de pessoa inválido: " + tipo);
        }

        try (Connection conn = ConnectionFactory.getConnection();

             PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {

                List<Pessoa> listaPessoas = new ArrayList<>();

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Atualizar - Retorna boolean
    protected boolean atualizarPessoa(Pessoa pessoa) throws SQLException {

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

        String sql = "UPDATE PESSOAS SET pessoa_ativa = false WHERE pessoa_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pessoa.getId());
            return stmt.executeUpdate() > 0;
        }

//        String sql = "DELETE FROM PESSOAS WHERE pessoa_id = ?";
//        try (Connection conn = ConnectionFactory.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setInt(1,pessoa.getId());
//            return stmt.executeUpdate() > 0;
//        }
    }

    public boolean reincluirPessoa(Pessoa pessoa) throws SQLException{
        String sql = "UPDATE PESSOAS SET pessoa_ativa = true WHERE pessoa_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pessoa.getId());
            return stmt.executeUpdate() > 0;
        }
    }
}