package DAO_MySql;

/**
 * Exception sp�cifique aux probl�mes d'acc�s aux donn�es via un DAO.
 * @author Eric
 */
public class DAOException extends Exception {
    
	private static final long serialVersionUID = 309200220164832142L;

	public DAOException() {
        super();
    }
    
    public DAOException(String message) {
        super(message);
    }
}
