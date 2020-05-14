package j4.lesson10ex_16A2467;

import java.util.ArrayList;

public class ServerInfo {
	Integer OnlineUser=0;
	Integer TotalUser=0;
	String BestSeller="";
	String BestColor="";
	String BestCar="";
	String BestFemaleCar="";
	String BestMaleCar="";
	ArrayList<String> log= new ArrayList();
	Integer[][] Sell={{0,0,0},{0,0,0},{0,0,0}}; 
	Integer[][] Male_Sell={{0,0,0},{0,0,0},{0,0,0}}; 
	Integer[][] Female_Sell={{0,0,0},{0,0,0},{0,0,0}}; 
	// BlackMINI   BlueMINI  RedMINI
	// BlackSedan BlueSedan RedSedan
	// BlackSUV    BlueSUV    RedSUV
	public Integer getOnlineUser() {
		return OnlineUser;
	}
	public void setOnlineUser(Integer onlineUser) {
		OnlineUser = onlineUser;
	}
	public Integer getTotalUser() {
		return TotalUser;
	}
	public void setTotalUser(Integer totalUser) {
		TotalUser = totalUser;
	}
	public String getBestSeller() {
		return BestSeller;
	}
	public void setBestSeller(String bestSeller) {
		BestSeller = bestSeller;
	}
	public String getBestColor() {
		return BestColor;
	}
	public void setBestColor(String bestColor) {
		BestColor = bestColor;
	}
	public String getBestCar() {
		return BestCar;
	}
	public void setBestCar(String bestCar) {
		BestCar = bestCar;
	}
	public String getBestFemaleCar() {
		return BestFemaleCar;
	}
	public void setBestFemaleCar(String bestFemaleCar) {
		BestFemaleCar = bestFemaleCar;
	}
	public String getBestMaleCar() {
		return BestMaleCar;
	}
	public void setBestMaleCar(String bestMaleCar) {
		BestMaleCar = bestMaleCar;
	}
	public ArrayList<String> getLog() {
		return log;
	}
	public void setLog(ArrayList<String> log) {
		this.log = log;
	}
	public Integer[][] getSell() {
		return Sell;
	}
	public void setSell(Integer[][] sell) {
		Sell = sell;
	}
	public Integer[][] getMale_Sell() {
		return Male_Sell;
	}
	public void setMale_Sell(Integer[][] male_Sell) {
		Male_Sell = male_Sell;
	}
	public Integer[][] getFemale_Sell() {
		return Female_Sell;
	}
	public void setFemale_Sell(Integer[][] female_Sell) {
		Female_Sell = female_Sell;
	}
	
	
}
