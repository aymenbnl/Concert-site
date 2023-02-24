import DAO_MySql.DAOException;
import DAO_MySql.DAO_Concert;

public class testDao {
    public static void main(String[] args) throws DAOException {
        DAO_Concert dao_concert = new DAO_Concert();
        System.out.println(dao_concert.getConcert());
    }
}
