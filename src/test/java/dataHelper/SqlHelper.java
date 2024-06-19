package dataHelper;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHelper {
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private SqlHelper() {
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }

    public static void cleanDB() {
        try (Connection connect = getConnection()) {
            QUERY_RUNNER.execute(connect, "DELETE FROM credit_request_entity");
            QUERY_RUNNER.execute(connect, "DELETE FROM payment_entity");
            QUERY_RUNNER.execute(connect, "DELETE FROM order_entity ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getStatusFromPaymentEntity() {
        try (Connection connect = getConnection()) {
            String queryStatus = "SELECT status  FROM payment_entity";
            return QUERY_RUNNER.query(connect, queryStatus, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
            return "Payment status not found";
        }
    }

    public static String getStatusFromCreditEntity() {
        try (Connection connect = getConnection()) {
            String queryStatus = "SELECT status  FROM credit_request_entity";
            return QUERY_RUNNER.query(connect, queryStatus, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
            return "Credit status not found";
        }
    }
}