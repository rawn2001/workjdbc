package tn.edu.esprit.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.edu.esprit.entities.Personne;
import tn.edu.esprit.services.ServicePersonne;

public class PersonneApp extends Application {
    private ServicePersonne servicePersonne = new ServicePersonne();
    private ObservableList<Personne> personnesList;
    private ListView<String> listView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestion des Personnes");

        personnesList = FXCollections.observableArrayList();
        listView = new ListView<>();
        rafraichirListe();

        TextField nomField = new TextField();
        nomField.setPromptText("Nom");
        TextField prenomField = new TextField();
        prenomField.setPromptText("Prénom");

        Button ajouterBtn = new Button("Ajouter");
        ajouterBtn.setOnAction(e -> {
            if (!nomField.getText().isEmpty() && !prenomField.getText().isEmpty()) {
                servicePersonne.ajouter(new Personne(nomField.getText(), prenomField.getText()));
                rafraichirListe();
                nomField.clear();
                prenomField.clear();
            }
        });

        Button supprimerBtn = new Button("Supprimer");
        supprimerBtn.setOnAction(e -> {
            String selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                String[] parts = selected.split(" ");
                int id = Integer.parseInt(parts[0]);
                servicePersonne.supprimer(id);
                rafraichirListe();
            }
        });

        VBox layout = new VBox(10, listView, nomField, prenomField, ajouterBtn, supprimerBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void rafraichirListe() {
        personnesList.clear();
        for (Personne p : servicePersonne.getAll(null)) {
            personnesList.add(p);
        }
        listView.setItems(FXCollections.observableArrayList(personnesList.stream().map(p -> p.getId() + " " + p.getNom() + " " + p.getPrenom()).toList()));
    }
}

