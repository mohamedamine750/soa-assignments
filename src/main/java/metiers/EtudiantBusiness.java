package metiers;

import entities.Option;
import entities.Etudiant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EtudiantBusiness {
    private static List<Etudiant> etudiants;
    private static OptionBusiness optionBusiness = new OptionBusiness();

    public EtudiantBusiness() {
        if (etudiants == null) {
            etudiants = new ArrayList<Etudiant>();
            etudiants.add(new Etudiant("I001", "Doe", "Jean",
                    optionBusiness.getOptionByCode(1), 2023, "jean.doe@example.com"));
            etudiants.add(new Etudiant("I002", "Smith", "Alice",
                    optionBusiness.getOptionByCode(1), 2022, "alice.smith@example.com"));
            etudiants.add(new Etudiant("I003", "Durand", "Pierre",
                    optionBusiness.getOptionByCode(2), 2023, "pierre.durand@example.com"));
        }
    }

    // Ajouter un étudiant
    public boolean addEtudiant(Etudiant etudiant) {
        if (etudiant == null || etudiant.getOption() == null) return false;
        int codeOption = etudiant.getOption().getCodeOption();
        Option option = optionBusiness.getOptionByCode(codeOption);
        if (option == null) return false;
        etudiant.setOption(option);
        if (getEtudiantByIdentifiant(etudiant.getIdentifiant()) != null) return false;
        return etudiants.add(etudiant);
    }

    // Récupérer un étudiant par identifiant
    public Etudiant getEtudiantByIdentifiant(String identifiant) {
        for (Etudiant e : etudiants) {
            if (e.getIdentifiant().equals(identifiant)) return e;
        }
        return null;
    }

    // Récupérer tous les étudiants
    public List<Etudiant> getAllEtudiants() {
        return etudiants;
    }

    // Récupérer les étudiants par option (int codeOption)
    public List<Etudiant> getEtudiantsByOption(int codeOption) {
        List<Etudiant> result = new ArrayList<>();
        for (Etudiant e : etudiants) {
            if (e.getOption() != null && e.getOption().getCodeOption() == codeOption) {
                result.add(e);
            }
        }
        return result;
    }

    // Surcharge : par objet Option
    public List<Etudiant> getEtudiantsByOption(Option option) {
        if (option == null) return new ArrayList<>();
        return getEtudiantsByOption(option.getCodeOption());
    }

    // Mettre à jour un étudiant
    public boolean updateEtudiant(String identifiant, Etudiant updatedEtudiant) {
        for (int i = 0; i < etudiants.size(); i++) {
            if (etudiants.get(i).getIdentifiant().equals(identifiant)) {
                // Re-lier l'option si nécessaire
                if (updatedEtudiant.getOption() != null) {
                    Option o = optionBusiness.getOptionByCode(
                            updatedEtudiant.getOption().getCodeOption());
                    if (o != null) updatedEtudiant.setOption(o);
                }
                etudiants.set(i, updatedEtudiant);
                return true;
            }
        }
        return false;
    }

    // Supprimer un étudiant
    public boolean deleteEtudiant(String identifiant) {
        Iterator<Etudiant> iterator = etudiants.iterator();
        while (iterator.hasNext()) {
            Etudiant e = iterator.next();
            if (e.getIdentifiant().equals(identifiant)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}