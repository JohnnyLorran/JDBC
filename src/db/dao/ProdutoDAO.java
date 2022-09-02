package db.dao;

import model.Categoria;
import model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection){
        this.connection = connection;
    }

    public void salvarProduto (Produto produto) {
        String sql = "INSERT INTO PRODUTO (NOME,DESCRICAO) VALUES (?,?)";

        try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());

            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()){
                while (rst.next()){
                    produto.setId(rst.getInt(1));
                }
            }
        }catch (Exception e){

        }
    }

    public void salvarProdutoComCategoria (Produto produto){
        String sql = "INSERT INTO PRODUTO (NOME,DESCRICAO,CATEGORIA_ID) VALUES (?,?,?)";

        try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.setInt(3,produto.getCategoria_id());

            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()){
                while (rst.next()){
                    produto.setId(rst.getInt(1));
                }
            }
        }
        catch (Exception e){

        }
    }

    public List<Produto> listarProdutos() {
        try {
            List<Produto> produtos = new ArrayList<Produto>();

            String sql = "Select * from PRODUTO";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        Produto produto =
                                new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
                        produtos.add(produto);
                    }
                }
            }
            return produtos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> buscarProdutoPorCategoria(Categoria c) {
        try {
            List<Produto> produtos = new ArrayList<Produto>();

            String sql = "Select * from PRODUTO where categoria_id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setInt(1, c.getId());
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        Produto produto =
                                new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
                        produtos.add(produto);
                    }
                }
            }
            return produtos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Integer id){
        try(PreparedStatement stm = connection.prepareStatement("Delete from produto where id = ?")) {
            stm.setInt(1,id);
            stm.execute();
        }catch (Exception e){

        }
    }

    public void alterar(String nome, String descricao, Integer id) {
        try(PreparedStatement stm = connection.prepareStatement("UPDATE produto p set p.nome = ? , p.descricao = ? where id = ?")){
            stm.setString(1,nome);
            stm.setString(2,descricao);
            stm.setInt(3,id);
            stm.execute();
        }catch (Exception e){

        }
    }

    private void trasformarResultSetEmProduto(List<Produto> produtos, PreparedStatement pstm){
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

                produtos.add(produto);
            }
        }catch (Exception e){

        }
    }

}
