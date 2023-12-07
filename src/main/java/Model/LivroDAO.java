package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class LivroDAO {
    static Connection connection = null;

    public static void addLivro(Livro livro){
        PreparedStatement stmt = null;

        try{
            connection = ConexaoBD.ConexaoBD();
            String sql = "INSERT INTO livro(titulo, ano, editora) VALUES (?, ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getAno());
            stmt.setString(3, livro.getEditora());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Livro> getAllLivros(){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Livro> livrosEncontrados = new LinkedList<>();

        try{
            connection = ConexaoBD.ConexaoBD();
            String sql = "SELECT * FROM livro";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while(rs.next()){
                livrosEncontrados.add(new Livro(rs.getLong("id"), rs.getString("titulo"), rs.getInt("ano"), rs.getString("editora")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return livrosEncontrados;
    }

    public static void deleteLivro(Long id){
        PreparedStatement stmt = null;

        try{
            connection = ConexaoBD.ConexaoBD();
            String sql = "DELETE FROM livro WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateLivro(Livro livro){
        PreparedStatement stmt = null;

        try{
            connection = ConexaoBD.ConexaoBD();
            String sql = "UPDATE livro SET titulo = ?, ano = ?, editora = ? where id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getAno());
            stmt.setString(3, livro.getEditora());
            stmt.setLong(4, livro.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
