package DAO;

import response.ReservationResponse;
import response.SelectDoctorResponse;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    Connection connection = ConnectionDB.getConnection();   // DB 연결
    PreparedStatement preparedStatement = null;
    Statement state = null;
    ResultSet result = null;

    /* 시나리오 1: 유저가 부서를 선택한 뒤 진료 받을 의사를 선택한다. */
    public List<SelectDoctorResponse> selectDoctor(int deptId) {
        String sql = "select d.id, d.name, phone_number " +
                "from department, doctor d " +
                "where d.department_id = " + deptId
                + " and department.dept_id = d.department_id";
        List<SelectDoctorResponse> doctors = new ArrayList<>();
        try {
            state = connection.createStatement();
            result = state.executeQuery(sql);

            while (result.next()) {
                SelectDoctorResponse response = SelectDoctorResponse
                        .builder()
                        .doctorId(result.getInt("d.id"))
                        .doctorName(result.getString("d.name"))
                        .phoneNumber(result.getString("phone_number")).build();
                doctors.add(response);
            }
            return doctors;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* 시나리오 2: 선택한 의사의 아이디를 바탕으로 예약 가능한 시간을 확인 */
    public List<ReservationResponse> isCanReservation(int doctorId) {
        String sql = "select d.name, schedule_time " +
                "from schedule, doctor d " +
                "where doctor_id = " + doctorId +
                " and d.id = schedule.doctor_id" +
                " and schedule.reservation_status = 0";
        List<ReservationResponse> res = new ArrayList<>();
        try {
            state = connection.createStatement();
            result = state.executeQuery(sql);

            while (result.next()) {
                ReservationResponse response = ReservationResponse
                        .builder()
                        .doctorName(result.getString("d.name"))
                        .schedule(result.getTimestamp("schedule_time"))
                        .build();
                res.add(response);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean insertSchedule(int doctorId, Timestamp scheduleTime){

        String sqlReservation = "insert into schedule (doctor_id, schedule_time) " +
                "values (?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sqlReservation);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setTimestamp(2, scheduleTime);
            if (preparedStatement.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "예약되었습니다.\n" +
                        "예약 날짜: " + scheduleTime , "예약 성공", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "해당 시간은 예약이 차 있습니다.\n" +
                    "다른 날짜를 선택해주세요." , "예약 실패", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
            return false;
        }
    }

    /* 시나리오 3: 예약 */
    public void insertReservation(int doctorId, int patientId, Timestamp scheduleTime) {
        try {
            String sql = "insert into reservation (doctor_id, patient_id, schedule_time) " +
                    "values (?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setInt(2, patientId);
            preparedStatement.setTimestamp(3, scheduleTime);
            preparedStatement.executeUpdate();

            removeSchedule(doctorId, scheduleTime);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* 시나리오 4: 예약한 시간을 schedule 테이블에서 제거*/
    public void removeSchedule(int doctorId, Timestamp scheduleTime) {
        String sql = "update schedule set reservation_status = " + 1 +
                " where doctor_id = ?" +
                " and schedule_time = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setTimestamp(2, scheduleTime);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* 예약할 부서 정보 가져오기 */
    public List<String> getDepartments(){
        String sql = "select department_name from department";
        List<String> departments = new ArrayList<>();

        try {
            state = connection.createStatement();
            result = state.executeQuery(sql);
            while (result.next()){
                String dept = result.getString("department_name");
                departments.add(dept);
            }

            return departments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* 예약할 부서 정보의 이름으로 아이디 찾기 */
    public int getDeptIdForName(String deptName){
        String sql = "select dept_id from department " +
                "where department_name = " + deptName;
        try {
            state = connection.createStatement();
            result = state.executeQuery(sql);
            if (result.next())
                return result.getInt("dept_id");
            return 0;
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDoctorIdForDoctorName(String doctorName){
        String sql = "select id from doctor " +
                "where name = " + doctorName;

        try {
            state = connection.createStatement();
            result = state.executeQuery(sql);
            if(result.next())
                return result.getInt("id");
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPatientIdForPatientName(String patientName){
        String sql = "select id from patient " +
                "where name = " + patientName;

        try {
            state = connection.createStatement();
            result = state.executeQuery(sql);
            if(result.next())
                return result.getInt("id");
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
