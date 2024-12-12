package com.example.sysbiblioteca.controller;


import com.dlsc.formsfx.model.structure.DateField;
import com.example.sysbiblioteca.model.entity.Livro;
import com.example.sysbiblioteca.model.services.LivroServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class LivroViewModalController extends LivroController {
    @FXML
    TextField txtTitulo;
    @FXML
    TextField txtAnoDeLancamento;
    @FXML
    TextField txtAutor;
    @FXML
    TextField txtGenero;
    @FXML
    Button btnSalvar;
    @FXML
    Button btnExcluir;
    @FXML
    Button btnCancelar;

    @Setter
    private LivroServices livroServices;

    private Livro livro;
    //*****************************************************************************************************************
    // INICIALIZAÇÃO
    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    //*****************************************************************************************************************
    // TRATAMENTO DE EVENTOS
    @FXML
    public void btnSalvarOnAction() {
        try {
            if (livro == null) {
                this.livro = new Livro();
                lerCampos();
                livroServices.addLivro(livro);
                fecharModal();
            } else {
                lerCampos();
                livroServices.updateLivro(livro);
                fecharModal();
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void btnExcluirOnAction() {
        lerCampos();
        livroServices.removeLivro(livro.getId());
        fecharModal();
    }

    @FXML
    public void btnCancelarOnAction() {
        fecharModal();
    }

    //*****************************************************************************************************************
    // METODOS COMPLEMENTARES
    public void fecharModal() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
        if (livro != null) {
            preencherCampos();
        }
    }

    private void preencherCampos() {
        if (livro != null) {
            txtTitulo.setText(livro.getTitulo());
            txtAnoDeLancamento.setUserData(livro.getAnoDeLancamento());
            txtAutor.setText(livro.getAutor());
            txtGenero.setText(livro.getGenero());

        }
    }

    private void lerCampos() {
        this.livro.setTitulo(txtTitulo.getText());
        this.livro.setAnoDeLancamento((Date) txtAnoDeLancamento.getUserData());
        this.livro.setAutor(txtAutor.getText());
        this.livro.setGenero(txtGenero.getText());
    }

}
