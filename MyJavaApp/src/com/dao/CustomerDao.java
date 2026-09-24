package com.dao;

import com.model.Employee;
import com.utility.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private DBConnection dbConnection = new DBConnection();
    List<Employee> list = new ArrayList<>();

    public List<Employee> getAllEmployees() {
       Connection conn =  dbConnection.dbConnect(); // we need the connection in dao
       // call your procedure here...
        String sql ="{CALL all_emp()}";
        try {
            // prepare the call
            CallableStatement callableStatement = conn.prepareCall(sql);
            // we execute the call/statement

            ResultSet rst = callableStatement.executeQuery();
            while(rst.next()){
                // as long as the rows exists , we will read them in objects
                // and save in list , after that we exit the while loop
                Employee employee = new Employee(
                        rst.getInt("id"),
                        rst.getString("name"),
                        rst.getString("branch"),
                        rst.getString("city"),
                        rst.getString("department")
                );
                list.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbConnection.dbClose();
       return list;
    }

    public List<Employee> getEmployeeByDepartment(String dept) throws SQLException {
        Connection conn = dbConnection.dbConnect();
        String sql = "{CALL emp_by_department(?)}";
        List<Employee> list = new ArrayList<>();

            CallableStatement callableStatement = conn.prepareCall(sql);
            callableStatement.setString(1, dept);

            ResultSet rst = callableStatement.executeQuery();
            while(rst.next()){
                // as long as the rows exists , we will read them in objects
                // and save in list , after that we exit the while loop
                Employee employee = new Employee(
                        rst.getInt("id"),
                        rst.getString("name"),
                        rst.getString("branch"),
                        rst.getString("city"),
                        rst.getString("department")
                );
                list.add(employee);
            }

        dbConnection.dbClose();
        return list;
    }

    public List<String> getEmployeeNamesByDepartment(String dept) {
        Connection conn = dbConnection.dbConnect();
        // call new proc from here


        dbConnection.dbClose();
        return null;
    }

    public int getEmpCountByDept(String dept) throws SQLException {
        Connection conn = dbConnection.dbConnect();
        String sql="{CALL cnt_emp_by_dept(?, ?)}";
        CallableStatement callableStatement = conn.prepareCall(sql);
        callableStatement.setString(1,dept);
        callableStatement.registerOutParameter(2, Types.INTEGER);

        callableStatement.execute();

        int count = callableStatement.getInt(2);
        dbConnection.dbClose();
        return count;
    }
}
