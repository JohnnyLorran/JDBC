package controller;

import db.ConnectionBD.ConnectionFactory;
import db.dao.CategoriaDAO;
import model.Categoria;

import java.sql.Connection;
import java.util.List;

public class CategoriaController {

    private CategoriaDAO categoriaDAO;

    public CategoriaController(){
            Connection connection = new ConnectionFactory().newConnectionBD();
            this.categoriaDAO = new CategoriaDAO(connection);
    }
    public List<Categoria> listar() {
        List<Categoria> categorias = categoriaDAO.listarCategorias();
        return categorias;
    }

}
