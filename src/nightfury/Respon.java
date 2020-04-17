package nightfury;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
	
	public void storeData() {
		System.out.println("storeData called");
		csvInit();
		try {
			CSVWriter wr = new CSVWriter(new FileWriter("data.csv", true));
			StringBuffer sb = new StringBuffer();
			for(String var : fav) {
				sb.append(var + "|");
			}
			String[] entries = {
					fname, lname, addrst, addrcity,
					addrctr, addrpos, phone, email, date,
					sb.toString(), how, rec
			};
			wr.writeNext(entries);
		} catch (IOException e) {
			e.printStackTrace();
			csvInit();
		}
	}
	
	public String doSubmit() {
		return "confirm-registration";
	}
	
	private void csvInit() {
		System.out.println("csvInit called");
		String[] header = { 
				"fname","lname","addrst",
				"addrcity","addrctr","addrpos",
				"phone","email","date",
				"fav","how","rec"};
		File file = new File("data.csv");
		try {
			FileWriter writer = new FileWriter(file);
			CSVWriter wr = new CSVWriter(writer);
			wr.writeNext(header);
			writer.close();
		} catch(Exception e) {e.printStackTrace();}
	}
}
