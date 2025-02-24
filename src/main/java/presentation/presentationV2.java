package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class presentationV2 {
    public static void main(String[] args)  {
        try {

            Scanner scanner = new Scanner(new File("config.txt"));
            String daoClassename = scanner.nextLine();
            Class cDao= Class.forName(daoClassename);
            IDao dao=(IDao) cDao.getConstructor().newInstance();
            //DaoImplV2 d=new DaoImplV2();

            String metierClassname = scanner.nextLine();
            Class cMetier= Class.forName(metierClassname);
            IMetier metier=(IMetier) cMetier.getConstructor().newInstance();

            Method setDao= cMetier.getDeclaredMethod("setDao", IDao.class);
            setDao.invoke(metier,dao);


            System.out.println( "RES="+ metier.calcul());


        } catch (Exception e) {
            System.out.println(e.getMessage());


        }

    }
}