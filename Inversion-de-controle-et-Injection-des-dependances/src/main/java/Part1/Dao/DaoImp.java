package Part1.Dao;

import org.springframework.stereotype.Component;

@Component("dao")
public class DaoImp implements IDao{
    @Override
    public String getDate() {
        return new java.util.Date().toString();
    }
}
