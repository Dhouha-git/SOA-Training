package graphql;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entities.Module;
import entities.UniteEnseignement;
import repository.ModuleBusiness;
import repository.UniteEnseignementBusiness;

import java.util.List;

public class QueryResolver implements GraphQLRootResolver {

    public UniteEnseignementBusiness helper;
    public ModuleBusiness moduleHelper;

    public QueryResolver() {
        helper = new UniteEnseignementBusiness();
        moduleHelper = new ModuleBusiness();
    }

    // ----- Modules -----
    public List<Module> getallmodules() {
        return moduleHelper.getAllModules();
    }

    public List<Module> modulesByType(Module.TypeModule type) {
        return moduleHelper.getModulesByType(type);
    }

    public Module moduleByMatricule(String matricule) {
        return moduleHelper.getModuleByMatricule(matricule);
    }

    // ----- Unités d'enseignement -----
    public List<UniteEnseignement> allUEs() {
        return helper.getListeUE();
    }

    public UniteEnseignement ueByCode(int code) {
        return helper.getUEByCode(code);
    }

    public List<UniteEnseignement> uesByDomaine(String domaine) {
        return helper.getUEByDomaine(domaine);
    }

    public List<UniteEnseignement> uesBySemestre(int semestre) {
        return helper.getUEBySemestre(semestre);
    }
}