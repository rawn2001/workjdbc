 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in t
 package workshopjdbc;
  */
 package workshopjdbc;
import tn.edu.esprit.entities.Personne;
import tn.edu.esprit.services.ServicePersonne;

public class WorkshopJDBC {
    public static void main(String[] args) {
        ServicePersonne sp = new ServicePersonne();

        // ✅ Ajouter une personne
        Personne p1 = new Personne("boutheina", "rjab");
        sp.ajouter(p1);
        Personne p3 = new Personne("chayma", "ben chekeya");
        sp.ajouter(p3);

        // ✅ Modifier une personne (supposons que l'ID = 1)
        Personne p2 = new Personne(1, "Ali", "Bouazizi");
        sp.modifier(p2);

        // ✅ Supprimer une personne (ID = 1)
        sp.supprimer(1);

        // ✅ Afficher toutes les personnes
        System.out.println(sp.getAll(null));
    }
}