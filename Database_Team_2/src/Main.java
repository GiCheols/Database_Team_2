import java.sql.Connection;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) {
        ConnectionDB c = new ConnectionDB(
                "jdbc:mysql://localhost:3306/db_2?serverTimezone=Asia/Seoul&useSSL=false&useUnicode=true&characterEncoding=utf8"
                , "root", "1234"
        );
        Connection connection = c.getConnection();

        ReservationUI frame = new ReservationUI();
        frame.setVisible(true);

        /* 연결 확인을 위한 새 유저 INSERT */
//        String SQL = "INSERT INTO PATIENT (name, ssn, phone_number, age) VALUES (?, ?, ?, ?)";
//        try {
//            PreparedStatement ps;
//            ps = connection.prepareStatement(SQL);
//            ps.setString(1, "test");
//            ps.setString(2, "010331-0000000");
//            ps.setString(3, "010-4567-1234");
//            ps.setInt(4, 23);
//            ps.executeUpdate();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}