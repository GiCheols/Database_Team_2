import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

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


        UserView user = new UserView();
        //유저 아이디로 해당 Diagnosis 검색
        System.out.println("유저 아이디로 진단서 검색");
        int i = 1;
        for (ArrayList<Object> entry : user.setDiagnosis(connection,17)) {
            System.out.print(i+":\t");
            for (Object value : entry) {
                System.out.print(value + "\t");
            }
            System.out.println();
            i++;
        }

        int d = (int) user.setDiagnosis(connection,17).get(0).get(0);
        i = 1;
        //diagnosis_id 처방 지병명 검색
        System.out.println("유저 아이디로 병명 검색");

        for (String entry : user.setDisease(connection,d)) {
            System.out.println(i+":\t"+entry);
            i++;
        }

        //diagnosis_id로 처방 받은 약 검색
        System.out.println("진단서 아이디로 처방약 검색");
        i = 1;
        for (ArrayList<Object> entry : user.setMedicine(connection,d)) {
            System.out.print(i+":\t");
            for (Object value : entry) {
                System.out.print(value + "\t");
            }
            System.out.println();
            i++;
        }

        //diagnosis_id로 처방 받은 주사제 검색
        System.out.println("진단서 아이디로 주사제 검색");
        i = 1;
        for (ArrayList<Object> entry : user.setInjection(connection,d)) {
            System.out.print(i+":\t");
            for (Object value : entry) {
                System.out.print(value + "\t");
            }
            System.out.println();
            i++;
        }

        
    }
}
