package servlet;

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
}
