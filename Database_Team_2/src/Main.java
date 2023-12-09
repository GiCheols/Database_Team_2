import DAO.ConnectionDB;
import DAO.ReservationDAO;
import DAO.ViewDAO;
import response.ReservationResponse;
import response.SelectDoctorResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /** Test for Reservation Query */
        ReservationDAO reservationDAO = new ReservationDAO();
        try {
            /* 시나리오 1: 부서 선택(UI) -> 의사 선택 */
            List<SelectDoctorResponse> doctors = reservationDAO.selectDoctor(1);
            for (SelectDoctorResponse r : doctors){
                System.out.println("Doctor ID: " + r.getDoctorId() +
                        ", Doctor Name: " + r.getDoctorName() +
                        ", Phone Number: " + r.getPhoneNumber());
            }

            /* 시나리오 2: 의료진 예약 가능 시간 확인 */
            List<ReservationResponse> res = reservationDAO.isCanReservation(2);
            for (ReservationResponse r : res){
                System.out.println("Doctor Name: " + r.getDoctorName()
                + ", Schedule Time: " + r.getSchedule());
            }

            /* 시나리오 3, 4: 의료진 예약 및 예약한 시간 삭제 */
            reservationDAO.insertReservation(2, 24, Timestamp.valueOf("2023-12-28 19:00:00"));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}