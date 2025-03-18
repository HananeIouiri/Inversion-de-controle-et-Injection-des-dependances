package Part1.Presentation;

import Part1.Dao.IDao;
import Part1.Metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotation {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("Dao", "Metier");
        IMetier metier = context.getBean(IMetier.class);
        String days = metier.calcul() + " jours restants";
        System.out.println("resultat avec Spring Annotation  = " + days);

    }
}
