package rs.bg.ac.student.ivana.MavenClient.controller;

import rs.bg.ac.student.ivana.MavenClient.communication.Communication;
import rs.bg.ac.student.ivana.MavenClient.cordinator.Cordinator;
import rs.bg.ac.student.ivana.MavenCommon.domain.Admin;
import rs.bg.ac.student.ivana.MavenClient.form.FrmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Map;
//import com.google.code.gson;
//import rs.bg.ac.student.ivana.MavenClient.main.JsonObject;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;

public class MainController {
	private FrmMain frmMain;

	private static final String BASE_URL = "http://api.currencylayer.com";
	private static final String API_KEY = "2e4baadf5c5ae6ba436f53ae5558107f";
	private static final String SOURCE = "USD";
	private static String[] CURRENCIES = new String[] { "EUR", "RSD" };

	public static Map<String,Object> jsonToMap(String str){
		Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String,Object>>() {}.getType());
		return map;
	}
	public MainController(FrmMain frmMain) {
		this.frmMain = frmMain;
		addActionListener();
	}

	public void openForm() {
		frmMain.getLblUsername().setText((String) Cordinator.getInstance().getParam("ADMIN"));
		frmMain.setVisible(true);
		String urlString = "https://api.openweathermap.org/data/2.5/weather?id=792680&appid=cc8947c03622e461fc7787b4f0fe4ad5&units=metric";
		try{
			StringBuilder result = new StringBuilder();
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while((line=rd.readLine())!=null) {
				result.append(line);
			}
			rd.close();
			Map<String,Object> respMap = jsonToMap(result.toString());
			Map<String,Object> mainMap = jsonToMap(respMap.get("main").toString());
			Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());
			frmMain.getlblTemp().setText(mainMap.get("temp").toString()+" ??C");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		frmMain.getTxtUSD().setText(Double.toString(1.00) + " $");

		for (int i = 0; i < CURRENCIES.length; i++) {
			try {
				Gson gson = new Gson();

				URL url = new URL(
						BASE_URL + "/live?access_key=" + API_KEY + "&source=" + SOURCE + "&currencies" + CURRENCIES[i]);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");

				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

				JsonObject result = gson.fromJson(reader, JsonObject.class);

				DecimalFormat df = new DecimalFormat("###.##");
				if (result.get("success").getAsBoolean()) {

					if (CURRENCIES[i] == "EUR") {
						double kurs = result.get("quotes").getAsJsonObject().get("USDEUR").getAsDouble();
						frmMain.gettxtEUR().setText(df.format(kurs) + " ???");
					} else {
						double kurs = result.get("quotes").getAsJsonObject().get("USDRSD").getAsDouble();

						frmMain.getTxtRSD().setText(df.format(kurs) + " RSD");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	private void addActionListener() {

		frmMain.addBtnMenuItemClaim(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rs.bg.ac.student.ivana.MavenClient.cordinator.Cordinator.getInstance().openFrmClaim();
			}
		});

		frmMain.addBtnMenuItemClient(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rs.bg.ac.student.ivana.MavenClient.cordinator.Cordinator.getInstance().openFrmClient();
			}
		});

		frmMain.addBtnMenuItemRiskType(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rs.bg.ac.student.ivana.MavenClient.cordinator.Cordinator.getInstance().openFrmRiskType();
			}
		});

	}

	public FrmMain getFrmMain() {
		return frmMain;
	}

}
