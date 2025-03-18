package Part1.Presentation;

import Part1.Metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXML {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IMetier metier = (IMetier) context.getBean("metier");
        String days = metier.calcul() + " jours restants";
        System.out.println("resultat avec Spring XML = " + days);
        ((ClassPathXmlApplicationContext) context).close();
    }
}
