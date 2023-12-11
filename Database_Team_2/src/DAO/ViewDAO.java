package DAO;

import response.ViewResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewDAO {
    Connection connection = ConnectionDB.getConnection();   // DB 연결
    Statement statement = null;
    ResultSet resultSet = null;

    public List<ViewResponse> getDiagnosis(int patientId){
        List<ViewResponse> viewResponses = new ArrayList<>();
        try {
            String query = "select diagnosis_id, p.name, doc.name, date "
                    + "from diagnosis d, patient p, doctor doc "
                    + "where d.patient_id = " + patientId
                    + " and d.patient_id = p.id "
                    + "and doc.id = d.doctor_id";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                ViewResponse vr = ViewResponse.builder()
                        .diagnosisId(resultSet.getInt("diagnosis_id"))
                        .patientName(resultSet.getString("p.name"))
                        .doctorName(resultSet.getString("doc.name"))
                        .date(resultSet.getDate("date")).build();
                viewResponses.add(vr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viewResponses;
    }
}
