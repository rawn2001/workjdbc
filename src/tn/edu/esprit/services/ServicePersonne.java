/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import tn.edu.esprit.entities.Personne;
import tn.edu.esprit.tools.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePersonne implements IService<Personne> {
    private Connection cnx = DataSource.getInstance().getConnection();

    public ServicePersonne() {}

    // ✅ Ajouter une personne
    @Override
    public void ajouter(Personne t) {
        String req = "INSERT INTO personne (nom, prenom) VALUES (?, ?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.executeUpdate();
            System.out.println("✅ Personne ajoutée avec succès !");
        } catch (SQLException ex) {
            System.out.println("❌ Erreur lors de l'ajout : " + ex.getMessage());
        }
    }

    // ✅ Modifier une personne
    @Override
    public void modifier(Personne t) {
        String req = "UPDATE personne SET nom=?, prenom=? WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setInt(3, t.getId());
            pst.executeUpdate();
            System.out.println("✅ Personne modifiée avec succès !");
        } catch (SQLException ex) {
            System.out.println("❌ Erreur lors de la modification : " + ex.getMessage());
        }
    }

    // ✅ Supprimer une personne
    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM personne WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("✅ Personne supprimée avec succès !");
        } catch (SQLException ex) {
            System.out.println("❌ Erreur lors de la suppression : " + ex.getMessage());
        }
    }

    // ✅ Récupérer une seule personne
    @Override
    public Personne getOne(Personne t) {
        String req = "SELECT * FROM personne WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getId());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Personne(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"));
            }
        } catch (SQLException ex) {
            System.out.println("❌ Erreur lors de la récupération : " + ex.getMessage());
        }
        return null;
    }

    // ✅ Récupérer toutes les personnes
    @Override
    public List<Personne> getAll(Personne t) {
        List<Personne> personnes = new ArrayList<>();
        String req = "SELECT * FROM personne";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) {
                Personne p = new Personne(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"));
                personnes.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("❌ Erreur lors de la récupération : " + ex.getMessage());
        }
        return personnes;
    }
}