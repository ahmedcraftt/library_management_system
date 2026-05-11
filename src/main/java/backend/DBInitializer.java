package backend;

import java.sql.Connection;
import java.sql.Statement;

public class DBInitializer {

    public static void init() {
        String sql = """
            CREATE TABLE IF NOT EXISTS books (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                author TEXT NOT NULL,
                isBorrowed INTEGER DEFAULT 0
            );
        """;

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
