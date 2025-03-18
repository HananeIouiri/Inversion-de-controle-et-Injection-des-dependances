package Part1.Metier;

import Part1.Dao.IDao;
import org.springframework.stereotype.Component;

@Component("metier")
public class MetierImp implements IMetier{

    private IDao dao; //couplage faible

    public MetierImp(IDao dao) {
        this.dao = dao;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }
    public int calcul() {
        String date = dao.getDate();

        return 30 - Integer.parseInt(date.substring(8, 10));
    }

// Injection dans la variable dao un objet d'une class qui implémente l'interface IDao
    public void SetDao(IDao dao) {
        this.dao = dao;
    }

}
