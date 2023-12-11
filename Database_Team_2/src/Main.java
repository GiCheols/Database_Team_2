<<<<<<< HEAD
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
=======
import DAO.ConnectionDB;
import DAO.ReservationDAO;
import DAO.ViewDAO;
import response.ReservationResponse;
import response.SelectDoctorResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
>>>>>>> main

public class Main {
    public static void main(String[] args) {
        ReservationUI reservationUI = new ReservationUI();
        reservationUI.show();

        /** Test for Reservation Query */
//        ReservationDAO reservationDAO = new ReservationDAO();
//        try {
//            /* 시나리오 1: 부서 선택(UI) -> 의사 선택 */
//            List<SelectDoctorResponse> doctors = reservationDAO.selectDoctor(1);
//            for (SelectDoctorResponse r : doctors){
//                System.out.println("Doctor ID: " + r.getDoctorId() +
//                        ", Doctor Name: " + r.getDoctorName() +
//                        ", Phone Number: " + r.getPhoneNumber());
//            }
//
//            /* 시나리오 2: 의료진 예약 가능 시간 확인 */
//            List<ReservationResponse> res = reservationDAO.isCanReservation(2);
//            for (ReservationResponse r : res){
//                System.out.println("Doctor Name: " + r.getDoctorName()
//                + ", Schedule Time: " + r.getSchedule());
//            }
//
//            /* 시나리오 3, 4: 의료진 예약 및 예약한 시간 삭제 */
//            reservationDAO.insertReservation(2, 24, Timestamp.valueOf("2023-12-28 19:00:00"));
//
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
