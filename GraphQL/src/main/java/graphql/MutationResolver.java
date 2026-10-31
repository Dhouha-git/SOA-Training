package graphql;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entities.Module;
import entities.UniteEnseignement;
import repository.ModuleBusiness;
import repository.UniteEnseignementBusiness;

public class MutationResolver implements GraphQLRootResolver {

    public UniteEnseignementBusiness helper;
    public ModuleBusiness moduleHelper;

    public MutationResolver() {
        helper = new UniteEnseignementBusiness();
        moduleHelper = new ModuleBusiness();
    }

    // ----- Modules -----
    public boolean addModule(String matricule, String libelle, int coef, int volumeHoraire,
                             Module.TypeModule type, int codeUE) {
        UniteEnseignement ueRef = new UniteEnseignement(codeUE, null, null, 0, 0);
        Module module = new Module(matricule, libelle, coef, volumeHoraire, type, ueRef);
        return moduleHelper.addModule(module); // false si l'UE n'existe pas
    }

    public Module updateModule(String matricule, String libelle, Integer coef, Integer volumeHoraire,
                               Module.TypeModule type, Integer codeUE) {
        Module m = moduleHelper.getModuleByMatricule(matricule);
        if (m == null) return null;

        if (libelle != null)       m.setNom(libelle);
        if (coef != null)          m.setCoefficient(coef);
        if (volumeHoraire != null) m.setVolumeHoraire(volumeHoraire);
        if (type != null)          m.setType(type);
        if (codeUE != null) {
            UniteEnseignement ue = helper.getUEByCode(codeUE);
            if (ue != null) m.setUniteEnseignement(ue);
        }
        moduleHelper.updateModule(matricule, m);
        return m;
    }

    public String deleteModule(String matricule) {
        return moduleHelper.deleteModule(matricule) ? "deleted successfully" : "module not found";
    }

    // ----- Unités d'enseignement -----
    public boolean addUniteEnseignement(int code, String domaine, String responsable, int credits, int semestre) {
        UniteEnseignement ue = new UniteEnseignement(code, domaine, responsable, credits, semestre);
        return helper.addUniteEnseignement(ue);
    }

    public UniteEnseignement updateUniteEnseignement(int code, String domaine, String responsable,
                                                     Integer credits, Integer semestre) {
        UniteEnseignement ue = helper.getUEByCode(code);
        if (ue == null) return null;

        if (domaine != null)     ue.setDomaine(domaine);
        if (responsable != null) ue.setResponsable(responsable);
        if (credits != null)     ue.setCredits(credits);
        if (semestre != null)    ue.setSemestre(semestre);
        helper.updateUniteEnseignement(code, ue);
        return ue;
    }

    public String deleteUniteEnseignement(int code) {
        return helper.deleteUniteEnseignement(code) ? "deleted successfully" : "UE not found";
    }
}