package tools;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rest")
public class RestActivator extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(ressourcesrest.RestOption.class);
        classes.add(ressourcesrest.RestEtudiant.class);
        return classes;
    }
}