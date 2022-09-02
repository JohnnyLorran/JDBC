package controller;

import db.ConnectionBD.ConnectionFactory;
import db.dao.ProdutoDAO;
import model.Produto;

import java.sql.Connection;
import java.util.List;

public class ProdutoController {

    private ProdutoDAO produtoDAO;
    public ProdutoController(){
        Connection connection = new ConnectionFactory().newConnectionBD();
        this.produtoDAO = new ProdutoDAO(connection);
    }
    public void deletar(Integer id) {
        System.out.println("Deletando produto");
        produtoDAO.deletar(id);
    }

    public void salvar(Produto produto) {
        System.out.println("Salvando produto");
        produtoDAO.salvarProduto(produto);
    }

    public List<Produto> listar() {
        List<Produto> produtos = produtoDAO.listarProdutos();
        return produtos;
    }

    public void alterar(String nome, String descricao, Integer id) {
        System.out.println("Alterando produto");
        produtoDAO.alterar(nome,descricao,id);
    }
}
