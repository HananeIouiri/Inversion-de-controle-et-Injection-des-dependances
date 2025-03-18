package Part1.Presentation;

import Part1.Dao.DaoImp;
import Part1.Metier.MetierImp;

public class presentationV1 {
    public static void main(String[] args) {

        DaoImp dao = new DaoImp();
        MetierImp metier = new MetierImp(dao);

        String days = metier.calcul() + " jours restants";
        System.out.println("resultat avec injection statique = "
                + days);
    }
}
