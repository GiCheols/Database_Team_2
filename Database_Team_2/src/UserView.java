import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserView {
    private int userid;
    private ArrayList<ArrayList<Object>> Diagnosis = new ArrayList<>();
    private ArrayList<String> Disease = new ArrayList<>();
    private ArrayList<ArrayList<Object>> Medicine = new ArrayList<>();
    private ArrayList<ArrayList<Object>> Injection = new ArrayList<>();




    public void setId(int userid){
        this.userid = userid;
    }
    public int getId(){
        return userid;
    }
    public ArrayList<ArrayList<Object>> setDiagnosis(Connection c, int userid){

        String sqlQuery = "SELECT * FROM diagnosis d JOIN patient p ON d.patient_id = p.id WHERE p.id = ?";
        try (PreparedStatement preparedStatement = c.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, userid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {

                    ArrayList<Object> column = new ArrayList<>();
                    column.add(resultSet.getInt("diagnosis_id"));
                    column.add(resultSet.getInt("doctor_id"));
                    column.add(resultSet.getInt("patient_id"));
                    java.sql.Date dateColumn = resultSet.getDate("date");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(dateColumn);
                    column.add(formattedDate);

                    Diagnosis.add(column);
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return Diagnosis;
    }

    public ArrayList<String> setDisease(Connection c, int diagnosisid){

        ArrayList<Integer> column = new ArrayList<>();
        String sqlQuery = "SELECT di.id FROM disease di JOIN diagnosis d ON di.diagnosis_id = d.diagnosis_id WHERE d.diagnosis_id = ?";
        try (PreparedStatement preparedStatement = c.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, diagnosisid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    column.add(resultSet.getInt("id"));
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        sqlQuery = "SELECT dc.disease_name FROM disease_code dc JOIN disease d ON dc.disease_id = d.id WHERE d.id = ?";
        for (Integer diseaseid : column){
            try (PreparedStatement preparedStatement = c.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, diseaseid);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Disease.add(resultSet.getString("disease_name"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

        } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Disease;
    }

    public ArrayList<ArrayList<Object>> setMedicine(Connection c, int diagnosisid){

        String sqlQuery = "SELECT * FROM medicine m JOIN diagnosis d ON m.medicine_d_id = d.diagnosis_id WHERE d.diagnosis_id = ?";
        try (PreparedStatement preparedStatement = c.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, diagnosisid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {

                    ArrayList<Object> column = new ArrayList<>();
                    column.add(resultSet.getString("medicine_name"));
                    column.add(resultSet.getInt("medicine_d_id"));
                    java.sql.Date dateColumn = resultSet.getDate("date_medichine");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(dateColumn);
                    column.add(formattedDate);
                    column.add(resultSet.getInt("daily_dosage"));
                    column.add(resultSet.getInt("once_dosage"));
                    column.add(resultSet.getInt("total_dosage"));

                    Injection.add(column);
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Injection;
    }

    public ArrayList<ArrayList<Object>> setInjection(Connection c, int diagnosisid){

        String sqlQuery = "SELECT * FROM injection i JOIN diagnosis d ON i.injection_d_id = d.diagnosis_id WHERE d.diagnosis_id = ?";
        try (PreparedStatement preparedStatement = c.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, diagnosisid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {

                    ArrayList<Object> column = new ArrayList<>();
                    column.add(resultSet.getString("injection_name"));
                    column.add(resultSet.getInt("injection_d_id"));
                    java.sql.Date dateColumn = resultSet.getDate("date_injection");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(dateColumn);
                    column.add(formattedDate);
                    column.add(resultSet.getInt("daily_dosage"));
                    column.add(resultSet.getInt("once_dosage"));
                    column.add(resultSet.getInt("total_dosage"));

                    Medicine.add(column);
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Medicine;
    }


}
