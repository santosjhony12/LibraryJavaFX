package com.example.bibliotecajavafx;

import Model.Alerts;
import Model.Livro;
import Model.LivroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class HelloController {
    @FXML
    private Button btnCadastrar;

    @FXML
    private TableColumn<Livro, String> colunaAno;

    @FXML
    private TableColumn<Livro, String> colunaEditora;

    @FXML
    private TableColumn<Livro, String> colunaLivro;

    @FXML
    private TableView<Livro> tableBiblioteca;

    @FXML
    private TextField textFieldAno;

    @FXML
    private TextField textFieldEditora;

    @FXML
    private TextField textFieldLivro;

    @FXML
    private TextField txtFieldLivroUE;
    @FXML
    private TextField textFieldEditoraUE;
    @FXML
    private TextField textFieldAnoUE;
    private Long id = null;

    private ObservableList<Livro> obsLivro = FXCollections.observableArrayList();
    public void initialize(){
        updateTable();
    }

    @FXML
    public void onCadastrar(ActionEvent event){
        String titulo = textFieldLivro.getText();
        String editora = textFieldEditora.getText();
        Integer ano = Integer.parseInt(textFieldAno.getText());

        LivroDAO.addLivro(new Livro(titulo, ano, editora));
        updateTable();
        Alerts.showAlert("SUCESSO!", "", "Livro cadastrado com sucesso", Alert.AlertType.CONFIRMATION);
    }
    public void updateTable(){
        tableBiblioteca.setItems(null);
        obsLivro.clear();
        List<Livro> listLivro = LivroDAO.getAllLivros();
        for(Livro livro : listLivro){
            obsLivro.add(livro);
        }
        colunaLivro.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colunaEditora.setCellValueFactory(new PropertyValueFactory<>("editora"));
        tableBiblioteca.setItems(obsLivro);
    }
    @FXML
    void onMouseClicked(MouseEvent event) {
        Livro livroSelecionado = tableBiblioteca.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            txtFieldLivroUE.setText(livroSelecionado.getTitulo());
            textFieldAnoUE.setText(String.valueOf(livroSelecionado.getAno()));
            textFieldEditoraUE.setText(livroSelecionado.getEditora());
            this.id = livroSelecionado.getId();
        } else {
            System.out.println("nenhum livro");
        }
    }
    @FXML
    public void onExcluir(ActionEvent event){
        if(this.id!=null){
            LivroDAO.deleteLivro(this.id);
            txtFieldLivroUE.setText("");
            textFieldAnoUE.setText("");
            textFieldEditoraUE.setText("");
            this.id = null;
            updateTable();
            Alerts.showAlert("SUCESSO", "", "Livro excluido com sucesso!", Alert.AlertType.CONFIRMATION);
        }

    }
    @FXML
    public void onAtualizar(ActionEvent event){
        String titulo = txtFieldLivroUE.getText();
        String editora = textFieldEditoraUE.getText();
        Integer ano = Integer.parseInt(textFieldAnoUE.getText());
        if(this.id!=null){
            LivroDAO.updateLivro(new Livro(this.id, titulo, ano, editora));
            txtFieldLivroUE.setText("");
            textFieldAnoUE.setText("");
            textFieldEditoraUE.setText("");
            this.id = null;
            updateTable();
            Alerts.showAlert("SUCESSO", "", "Livro atualizado com sucesso.", Alert.AlertType.CONFIRMATION);
        }

    }
}
