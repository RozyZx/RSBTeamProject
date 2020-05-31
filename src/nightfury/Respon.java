package nightfury;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

@ManagedBean
@ApplicationScoped
@RequestScoped
public class Respon {
	
	// db action
	/* CREATE TABLE survey_data(
	    name VARCHAR(64) NOT NULL,
	    addr VARCHAR(256) NOT NULL,
	    phone VARCHAR(12) NOT NULL,
	    email VARCHAR(128) NOT NULL,
	    date VARCHAR(16) NOT NULL,
	    how VARCHAR(64) NOT NULL,
	    rec VARCHAR(64) NOT NULL,
	    fav VARCHAR(64) NOT NULL,
	    comment VARCHAR(128) NOT NULL);
	 */
	
	private boolean addDB() {
		String password = "rsbk_survey";
	    String user = "rsbk_survey";
	    String database_name = "rsbk_survey";
	    String url_base = "db4free.net:3306/";
	    Connection c;
	    Statement script;
	    PreparedStatement pst;
		// prepare data
		String name = fname + " " + lname;
		String addr = addrst + ", " + addrcity + "," + addrctr + "," + addrpos;
		String _fav = "";
		for(String val : fav) {
			_fav += val + ",";
		}
		
		try {
			
			c = DriverManager.getConnection(
                    "jdbc:mysql://"+
                            url_base+
                            database_name+
                            "?zeroDateTimeBehavior=CONVERT_TO_NULL",
                    user,
                    password);
            script = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            
			String sql = "insert into survey_data values(?,?,?,?,?,?,?,?,?)";
			pst = c.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, addr);
			pst.setString(3, phone);
			pst.setString(4, email);
			pst.setString(5, date);
			pst.setString(6, how);
			pst.setString(7, rec);
			pst.setString(8, _fav);
			pst.setString(9, comment);
			pst.execute();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	// end of db action
	
	//Respondent credential
	private String fname, lname, addrst, addrcity,
		addrctr, addrpos, phone, email, date, how, rec;
	private String[] fav;
	private String comment;

	public String getComment() {
		return comment;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public String getAddrst() {
		return addrst;
	}

	public String getAddrcity() {
		return addrcity;
	}

	public String getAddrctr() {
		return addrctr;
	}

	public String getAddrpos() {
		return addrpos;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getDate() {
		return date;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setAddrst(String addrst) {
		this.addrst = addrst;
	}

	public void setAddrcity(String addrcity) {
		this.addrcity = addrcity;
	}

	public void setAddrctr(String addrctr) {
		this.addrctr = addrctr;
	}

	public void setAddrpos(String addrpos) {
		this.addrpos = addrpos;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	//end
	
	//response_fav
	private Items[] rfav;
	
	public String[] getFav() {
		return fav;
	}

	public void setFav(String[] fav) {
		this.fav = fav;
	}

	public Items[] getFavValue() {
		rfav = new Items[6];
		rfav[0] = new Items("Siswa", "0");
		rfav[1] = new Items("Lokasi", "1");
		rfav[2] = new Items("Kampus", "2");
		rfav[3] = new Items("Suasana", "3");
		rfav[4] = new Items("Kamar Asrama", "4");
		rfav[5] = new Items("Olahraga", "5");
		return rfav;
	}
	//end
	
	// response_how
	private Items[] rhow;

	public String getHow() {
		return how;
	}

	public void setHow(String how) {
		this.how = how;
	}

	public Items[] getHowValue() {
		rhow = new Items[4];
		rhow[0] = new Items("Teman", "0");
		rhow[1] = new Items("Televisi", "1");
		rhow[2] = new Items("Internet", "2");
		rhow[3] = new Items("Lainnya", "3");
		return rhow;
	}
	// end
	
	// response_recommend
	private Items[] rrec;

	public String getRec() {
		return rec;
	}

	public void setRec(String rec) {
		this.rec = rec;
	}

	public Items[] getRecValue() {
		rrec = new Items[3];
		rrec[0] = new Items("Sangat Mungkin", "0");
		rrec[1] = new Items("Mungkin", "1");
		rrec[2] = new Items("Tidak Mungkin", "2");
		return rrec;
	}
	// end
	
	public String doSubmit() {
		if(addDB()) return "confirm-registration";
		else return "failed";
	}
}
