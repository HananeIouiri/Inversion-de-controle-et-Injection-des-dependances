package Part2;

import Part1.Dao.DaoImp;
import Part1.Metier.IMetier;
import Part1.Metier.MetierImp;

public class App {
    public static void main(String[] args) {
        IoCContainer container = new IoCContainer();

        //Saving the beans in a container
        container.registerBean("dao", new DaoImp());
        container.registerBean("metier", new MetierImp(new DaoImp()));

        // Injection of dependencies
        container.doConstructorInjection();
        container.doSetterInjection();
        container.doFieldInjection();

        //Using our beans
        IMetier metier = (IMetier) container.getBean("metier");
        metier.calcul();
    }
}