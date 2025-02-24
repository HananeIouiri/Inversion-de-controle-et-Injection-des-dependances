package dao;



public class IDaoImpl implements IDao {

    @Override
    public double getData() {
        System.out.println("version base de donne");
        double data= 25;
        return  data;
    }
}
