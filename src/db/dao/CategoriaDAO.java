package db.dao;

import model.Categoria;
import model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private Connection connection;

    public CategoriaDAO(Connection connection)  {
        this.connection = connection;
    }

    public List<Categoria> listarCategorias() {
        try {
            List<Categoria> categorias = new ArrayList<Categoria>();

            String sql = "Select * from CATEGORIA";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        Categoria categoria =
                                new Categoria(rst.getInt(1), rst.getString(2));
                        categorias.add(categoria);
                    }
                }
            }
            return categorias;
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    public List<Categoria> listarCategoriasComProdutos()  {
        try {
            List<Categoria> categorias = new ArrayList<Categoria>();
            Categoria ultima = null;
            String sql = "Select c.id, c.nome, p.id, p.nome, p.descricao from CATEGORIA c inner join produto p on c.id = p.categoria_id";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (ultima == null || !ultima.getNome().equals(rst.getString(2))) {
                            Categoria categoria =
                                    new Categoria(rst.getInt(1), rst.getString(2));
                            ultima = categoria;
                            categorias.add(categoria);
                        }
                        Produto produto =
                                new Produto(rst.getInt(3), rst.getString(4), rst.getString(5));
                        ultima.adicionarProduto(produto);
                    }
                }
            }
            return categorias;
        } catch (Exception e){
            throw  new RuntimeException(e);

        }
    }



}
