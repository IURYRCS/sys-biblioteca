package com.example.sysbiblioteca.controller;

import com.example.sysbiblioteca.utils.*;
import com.example.sysbiblioteca.MainApp;
import com.example.sysbiblioteca.model.entity.Livro;
import com.example.sysbiblioteca.model.services.LivroServices;
import com.example.sysbiblioteca.utils.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

public class LivroController implements Initializable {
    @FXML
    VBox vBoxLivro;
    @FXML
    Label lbLivro;
    @FXML
    TableView<Livro> tbLivro;
    @FXML
    TableColumn<Livro, Integer> columnIdLivro;
    @FXML
    TableColumn <Livro, String> columnTituloLivro;
    @FXML
    TableColumn <Livro, String> columnAnoDeLancamentoLivro;
    @FXML
    TableColumn <Livro, String> columnAutorLivro;
    @FXML
    TableColumn <Livro, String> columnGeneroLivro;
    @FXML
    Button btnNovo;

    protected Livro livro;
    private String pathLivro;
    @Setter
    protected LivroServices livroServices;

    //******************************************************************************************************************
    //INITIALIZER

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarGUI();
    }
    public void iniciarGUI(){

        // Vincula as celulas de cada coluna com os campos da classe model
        columnIdLivro.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTituloLivro.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        columnAnoDeLancamentoLivro.setCellValueFactory(new PropertyValueFactory<>("ano"));
        columnAutorLivro.setCellValueFactory(new PropertyValueFactory<>("autor"));
        columnGeneroLivro.setCellValueFactory(new PropertyValueFactory<>("genero"));

        tbLivro.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                this.livro = tbLivro.getSelectionModel().getSelectedItem();
                if(this.livro != null) {
                    System.out.println("Duplo clique em: " + this.livro.getTitulo());
                    modalView("Livro","\\LivroViewModal.fxml");
                    updateTableView();
                }
            }
        });

    }
    //******************************************************************************************************************
    // MODAL VIEW
    public void modalView(String title, String arquivoFXML){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(new FileInputStream(PathFXML.pathBase() + arquivoFXML));
            LivroViewModalController controller = fxmlLoader.getController();
            controller.setLivroServices(new LivroServices());
            controller.setLivro(this.livro);
            Stage modal = new Stage();
            modal.setTitle(title);
            modal.setScene(new Scene(root));
            modal.initModality(Modality.WINDOW_MODAL);
            modal.initOwner(MainApp.getScene().getWindow());
            modal.showAndWait();

        } catch (RuntimeException | IOException e) {
            Alerta.exibirAlerta("Error","Erro ao carregar a view",e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    //******************************************************************************************************************
    // UPDATE TABLE
    public void updateTableView(){
        if (livroServices == null){
            throw  new IllegalStateException("Sercice LivroService não instanciado: nullpoint exception");
        }

        List<Livro> lista = livroServices.getAllLivro();
        lista.forEach( (obj) -> System.out.println(obj.getId() +", "+ obj.getTitulo()+", "+obj.getAutor() + ", " + obj.getAnoDeLancamento() + ", "+obj.getGenero()));
        ObservableList<Livro> observableList = FXCollections.observableList(lista);
        tbLivro.setItems(observableList);
    }

    //******************************************************************************************************************
    // TRATAMENTO DE EVENTOS
    @FXML
    public void onBtnNovoAction(){
        this.livro = null;
        modalView("Manter Veiculos","\\VeiculoViewModal.fxml");
        updateTableView();}

}