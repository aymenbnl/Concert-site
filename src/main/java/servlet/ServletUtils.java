package servlet;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ServletUtils {
	public static Integer getIdInPath(String path) {
		String[] pathVariables = path.split("/");
		if(pathVariables.length == 1)  {
			Integer id = null;
			try {
				id = Integer.parseInt(pathVariables[0]);
				return id;
				
			} catch (NumberFormatException e) {
				
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedPassword) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
