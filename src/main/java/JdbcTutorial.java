import java.sql.*;
import java.util.Properties;

public class JdbcTutorial {
    Connection conn = null;
    String myConnectionURL = "jdbc:oracle:thin:@10.1.10.201:1521:ORCLPDB";

    /**
     *
     */
    private void getConnection(){
        Connection con = null;
        try {
            Properties props = new Properties();
            //props.put("DB_DRIVER","oracle.jdbc.OracleDriver");
            props.setProperty("user", "dtsdm");
            props.setProperty("password", "cL3ar#12");

            con = DriverManager.getConnection(myConnectionURL,props);
            System.out.println("Connection Successful");
        } catch (Exception e){
            e.printStackTrace();
        }
        this.conn = con;
    }


    void testOne() {

        /*String sql = "select AGNCY_WID, DPRTMNT_WID, AGNCY_CD\n" +
                "        AGNCY_DESCR, CURR_SW, INSERT_DATE\n" +
                "        UPDATE_DATE, DELETED_FLAG " +
                "from DTSDM.AGNCY_WH \n";
        */

        String sql = "Select * \n" +
                "from DTSDM.AGNCY_WH \n" ;


        try {
            try (PreparedStatement ps = this.conn.prepareStatement(sql)) {
                //ps.setInt(1, userId);
                try (ResultSet rs = ps.executeQuery();) {

                    int rowCount = 0;
                    while(rs.next()) {
                        int agncyWid =  rs.getInt("AGNCY_WID");
                        int dprtmntWid =  rs.getInt("DPRTMNT_WID");
                        String agncyCd =  rs.getString("AGNCY_CD");
                        String agncyDescr =  rs.getString("AGNCY_DESCR");
                        int currSw =  rs.getInt("CURR_SW");
                        Date insertDate =  rs.getDate("INSERT_DATE");
                        Date updateDate =  rs.getDate("UPDATE_DATE");
                        String deletedFlag =  rs.getString("DELETED_FLAG");

                        System.out.println(++rowCount + " " +
                                agncyWid + " " +
                                dprtmntWid + " " +
                                agncyCd + " " +
                                agncyDescr + " " +
                                currSw + " " +
                                insertDate + " " +
                                updateDate + " "  );

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

     }

    public static void main(String[] args) {
        System.out.println("Hello Start JdbcTutorial"); 
        // Create an new JdbcTutorial
        JdbcTutorial jt = new JdbcTutorial();
        jt.getConnection();
        jt.testOne();
        System.out.println("Goodbye - End JdbcTutorial");

    }
}
