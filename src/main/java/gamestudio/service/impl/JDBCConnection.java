package gamestudio.service.impl;

public final class JDBCConnection {

	public static final String SELECT_COMMAND = "SELECT * from pouzivatel where meno = '%s' AND heslo = '%s'";
	public static final String URL = "jdbc:postgresql://localhost/test";
	public static final String USER = "postgres";
	public static final String PASSWORD = "Postgres1234";
	
	
	private JDBCConnection() {}
	
}
