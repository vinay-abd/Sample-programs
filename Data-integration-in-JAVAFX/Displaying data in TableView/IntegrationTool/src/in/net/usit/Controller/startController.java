package in.net.usit.Controller;

import java.awt.Event;
import java.lang.reflect.Field;
import java.net.URL;
import java.security.spec.RSAPublicKeySpec;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import com.sun.glass.ui.Window;

import in.net.usit.Connector.MySqlConnector;
import in.net.usit.Connector.OracleConnector;
import in.net.usit.Model.Columns;
import in.net.usit.Model.Employee;
import in.net.usit.Model.OracleModel;
import in.net.usit.Model.SqlModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;

public class startController implements Initializable {
	@FXML
	private ComboBox<String> sc;

	@FXML
	private ComboBox<String> tab;

	@FXML
	private ListView<String> listView;

	@FXML
	private ComboBox<String> sc1;

	@FXML
	private ComboBox<String> tab1;

	@FXML
	private ListView<String> listView1;

	@FXML
	private Button sql_oracle;

	@FXML
	private Label sqlCount;

	private Date startDate = new Date();

	private Date endDate = new Date();
	@FXML
	private Label time;

	@FXML
	private Button oracle_sql;
	String name2 = "";
	String name1 = "";
	int count=0;

	int count1 = 0;
	

	@FXML
	private Label oracleCount;

	private List<String> sqlDataTypeList = new ArrayList<String>();

	private List<String> oracleDataTypeList = new ArrayList<String>();

	public Statement st;
	Columns col1 = null;
	Employee column = null;

	private ResultSet rs;

	String p1, p2 = "";
	Columns col = null;
	@FXML
	private TableView<Employee> table;

	@FXML
	private TableColumn<Employee, Integer> tc1;

	@FXML
	private TableColumn<Employee, String> tc2;
	@FXML
	private TableColumn<Employee, String> tc3;

	@FXML
	private TableView<Employee> table1;

	@FXML
	private TableColumn<Employee, Integer> tc11;

	@FXML
	private TableColumn<Employee, String> tc22;
	@FXML
	private TableColumn<Employee, String> tc33;
	
	

	private int sqlColumnCount, oracleColumnCount = 0;

	private List<Employee> resultList = new ArrayList<Employee>();

	private String choosen = null;

	private HashMap<String, String> columnDetails = new HashMap<String, String>();
	private HashMap<String, String> columnDetails1 = new HashMap<String, String>();
	String selected = "mysql";
	ObservableList<String> sl = FXCollections.observableArrayList();
	ObservableList<String> tl = FXCollections.observableArrayList();
	ObservableList<Columns> cl = FXCollections.observableArrayList();

	ObservableList<String> sl1 = FXCollections.observableArrayList();
	ObservableList<String> tl1 = FXCollections.observableArrayList();
	ObservableList<Columns> cl1 = FXCollections.observableArrayList();
	ObservableList<Employee> el = FXCollections.observableArrayList();
	ObservableList<Employee> el1 = FXCollections.observableArrayList();
	SqlModel sql = new SqlModel();
	OracleModel oracle = new OracleModel();

	List<String> schemList = new ArrayList<String>();
	List<String> schemList1 = new ArrayList<String>();
	private List<Columns> sqlFieldList = new ArrayList<Columns>();
	private List<Columns> oracleFieldList = new ArrayList<Columns>();

	public String selectedSchema = "mysql";
	public String selectedSchema1 = "vinay";

	static String driver, name = "root";

	static String pass = "root";

	static String url;

	static String sel;
	static Connection con = null;
	static Connection con1 = null;
	private String selTable = null;
	private String selSchema = null;
	private String selTable1 = null;
	private String selSchema1 = null;

	// String sql ="";

	public startController() throws SQLException, ClassNotFoundException {
		System.out.println("Inside startController()...");
		

		if (selected.equals("mysql")) {
			con = MySqlConnector.isConnected();
			System.out.println("Inside if mysql");
			driver = "com.mysql.jdbc.Driver";
			name = "root";
			pass = "root";
			System.out.println("after password name Inside if mysql...");
			getschemaList();
			System.out.println("after Inside if mysql...");
		}
		/*
		 * name = "root"; pass = "root";
		 */

		System.out.println("Driver in startController : " + driver);
		Class.forName(driver);

		// sc.setItems(sl);

		System.out.println("in constructor");
		// TODO Auto-generated constructor stub

		System.out.println("in constructor end");

	}

	public void getschemaList1() throws SQLException {
		// TODO Auto-generated method stub
		schemList1.clear();
		sqlFieldList.clear();
		if (!sl1.isEmpty()) {
			// schemList.clear();
			sl1.removeAll(schemList1);
			sl1 = FXCollections.observableArrayList();

		}

		System.out.println("**********");
		System.out.println("connection ===>" + con);
		System.out.println("connection1 ===>" + con1);
		con1 = DriverManager.getConnection(initializeDatabaseUrlForOracle(), "system", "system");
		System.out.println("connection1 ===>" + con1);
		if (con1 != null) {
			System.out.println("connected!!!!!!!!");
			st = con1.createStatement();
			DatabaseMetaData meta = con1.getMetaData();
			ResultSet rs = meta.getSchemas();
			while (rs.next()) {
				schemList1.add(rs.getString(1));
				// sl.add(rs.getString(1));

			}
			System.out.println("-------->" + schemList1);
		}
		sl1.addAll(schemList1);

	}

	public void getSchema() {
		System.out.println("you are selecting item is " + sc1.getSelectionModel().getSelectedItem());
		selectedSchema1 = sc1.getSelectionModel().getSelectedItem();
	}

	private String initializeDatabaseUrlForMysql() {
		// TODO Auto-generated method stub
		// System.out.println("Inside initializeDatabaseUrlForMysql() selectedSchema :
		// "+selectedSchema);
		return "jdbc:mysql://localhost:3306/" + selectedSchema;
	}

	private String initializeDatabaseUrlForOracle() {
		// TODO Auto-generated method stub
		return "jdbc:oracle:thin:@localhost:1521:XE";
	}

	public void selectedMethod() throws SQLException {
		System.out.println("1");

		try {
			if (selectedSchema != null) {
				selected = "mysql";
				System.out.println("selected ==>" + selected);
				con = MySqlConnector.isConnected();
				driver = "com.mysql.jdbc.Driver";
				name = "root";
				pass = "root";
				System.out.println("after password name Inside if mysql...");
				if (sl.size() > 0) {
					schemList.clear();
					sl = FXCollections.observableArrayList();
				}
				getschemaList();
				sc.setItems(sl);
				// sc.setItems(sl);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	public void selectedMethod1() throws SQLException {
		if (selectedSchema1 != null) {
			selected = "oracle";
			System.out.println("selected in oracle ==>" + selected);

			System.out.println("Inside if Oracle");
			con1 = OracleConnector.isConnected();
			driver = "oracle.jdbc.driver.OracleDriver";
			name = "system";
			pass = "system";
			if (sl1.size() > 0) {
				schemList1.clear();
				sl1 = FXCollections.observableArrayList();
			}
			getschemaList1();
			sc1.setItems(sl1);

		}
	}

	public void getschemaList() throws SQLException {
		oracleFieldList.clear();
		System.out.println("in SQL ==>" + initializeDatabaseUrlForMysql());
		System.out.println("con is ::" + con);
		con = DriverManager.getConnection(initializeDatabaseUrlForMysql(), "root", "root");
		System.out.println("Con : " + con);
		if (con != null) {
			System.out.println("connected!!!!!!!!");
			st = con.createStatement();

			DatabaseMetaData meta = con.getMetaData();
			ResultSet rs = meta.getCatalogs();
			while (rs.next()) {
				String string = rs.getString(1);
				schemList.add(string);
				// sl.add(rs.getString(1));
			}
		}
		sl.addAll(schemList);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		table.setEditable(true);
		table1.setEditable(true);

		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// call();
		// con.createStatement();

	}

	public void selectSchema() throws SQLException {
		selectedSchema = sc.getSelectionModel().getSelectedItem();
		System.out.println("selected schema name is ::" + selectedSchema);
		setTables();
	}

	public void selectSchema1() throws SQLException {
		selectedSchema1 = sc1.getSelectionModel().getSelectedItem();
		System.out.println("selected schema name is on oracle ::" + selectedSchema1);
		setTables1();
	}

	public void setTables() throws SQLException {
		sqlColumnCount = 0;
		int count = 0;
		tl.clear();
		con = DriverManager.getConnection(initializeDatabaseUrlForMysql(), name, pass);
		st = con.createStatement();
		if (sc.getSelectionModel().getSelectedItem() != null) {
			selectedSchema = sc.getSelectionModel().getSelectedItem();
			DatabaseMetaData meta = con.getMetaData();
			ResultSet rs = meta.getTables(selectedSchema, selectedSchema, "%", null);

			// ResultSet rs1 = st.executeQuery("show tables from " + schema);
			while (rs.next()) {
				count++;
				tl.add(rs.getString(3));
			}
			tab.setItems(tl);

		}
		// showColoumns();
	}

	public void setTables1() throws SQLException {
		oracleColumnCount = 0;
		System.out.println("in showing table  " + selectedSchema1);
		int count = 0;
		tl1.clear();

		if (sc1.getSelectionModel().getSelectedItem() != null) {
			selectedSchema1 = sc1.getSelectionModel().getSelectedItem();

			DatabaseMetaData meta = con1.getMetaData();

			ResultSet rs1 = meta.getTables(selectedSchema1, selectedSchema1, "%", null);

			while (rs1.next()) {
				tl1.add(rs1.getString(3));
			}
			tab1.setItems(tl1);
		}
	}

	public void showColoumns() throws SQLException {
		int count = 0;
		cl.clear();
		el.clear();
		List<String> columnName=new ArrayList<String>();
		sqlFieldList.clear();
		sqlDataTypeList.clear();
		con = DriverManager.getConnection(initializeDatabaseUrlForMysql(), name, pass);
		st = con.createStatement();
		System.out.println("*************");
		System.out.println("*************");
		if (tab.getSelectionModel().getSelectedItem() != null) {
			selTable = tab.getSelectionModel().getSelectedItem();
			DatabaseMetaData meta = con.getMetaData();
			ResultSet rs1 = meta.getColumns(selectedSchema, selectedSchema, selTable, "%");
			System.out.println("---in setting coloumns");
			// ResultSet rs1 = st.executeQuery("desc " + table);
		
			while (rs1.next()) {
				columnName.add(rs1.getString(4).toUpperCase());
				cl.add(new Columns(rs1.getString(4).toUpperCase(), rs1.getString(6).toUpperCase()));
				System.out.println("---in setting coloumns" + cl);
				if (rs1.getString(6).toUpperCase().contains("INT")) {
					sqlDataTypeList.add("NUMBER");
				} else if (rs1.getString(6).toUpperCase().contains("CHAR")) {
					sqlDataTypeList.add("VARCHAR");
				} else if (rs1.getString(6).toUpperCase().contains("TIME")
						|| rs1.getString(6).toUpperCase().contains("DATE")) {
					sqlDataTypeList.add("TIMESTAMP");
				}

				System.out.println("all columns are ===>"+columnName);
				

			}
			for (int i = 0; i < columnName.size(); i++) {
				table.getColumns().get(i).setText(columnName.get(i));
			}
			
			/*
			 * table.getColumns().get(1).setText("NAME");
			 * table.getColumns().get(2).setText("ADDRESS");
			 */
			Statement s = con.createStatement();
			// ResultSet rs=s.executeQuery("select * from
			// "+tab.getSelectionModel().getSelectedItem());

			ResultSet rs = st.executeQuery("select *  from " + tab.getSelectionModel().getSelectedItem());

			while (rs.next()) {
				count++;
				Employee emp = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3));
				el.add(emp);
				// resultList.add(emp);
				/*
				 * Employee e = new Employee(); e.setId(rs.getInt(1));
				 * e.setName(rs.getString(2)); e.setAddress(rs.getString(3));
				 */
				resultList.add(emp);

				sqlCount.setText(""+count);
			}
			// System.out.println("coloumn types are====>" + sqlDataTypeList);

			// System.out.println(cl.toString());
			tc1.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
			tc2.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
			tc3.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));

			table.setItems(el);
			// tc1.setCellFactory(TextFieldTableCell.forTableColumn());
			sqlColumnCount = cl.size();

		}
	}

	public void showColoumns1() throws SQLException {
		cl1.clear();
		el1.clear();
		List<String> columnName1=new ArrayList<String>();
		oracleFieldList.clear();
		oracleDataTypeList.clear();

		if (tab1.getSelectionModel().getSelectedItem() != null) {
			selTable1 = tab1.getSelectionModel().getSelectedItem();
			DatabaseMetaData meta = con1.getMetaData();
			ResultSet rs1 = meta.getColumns(selectedSchema1, selectedSchema1, selTable1, "%");
			System.out.println("---in setting coloumns" + rs1.toString());
			while (rs1.next()) {
				columnName1.add(rs1.getString(4).toUpperCase());
				cl1.add(new Columns(rs1.getString(4).toUpperCase(), rs1.getString(6)));
				System.out.println("---in setting coloumns" + rs1.getString(4));
				oracleDataTypeList.add(rs1.getString(6).toUpperCase());
			}

			Statement s = con1.createStatement();
			// ResultSet rs=s.executeQuery("select * from
			// "+tab.getSelectionModel().getSelectedItem());
			for (int i = 0; i < columnName1.size(); i++) {
				table1.getColumns().get(i).setText(columnName1.get(i));
			}
			
			ResultSet rs = st.executeQuery("select *  from " + tab1.getSelectionModel().getSelectedItem());

			while (rs.next()) {
				count1++;
				// el1.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3)));
				Employee emp = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3));
				el1.add(emp);
				// resultList.add(emp);
				/*
				 * Employee e = new Employee(); e.setId(rs.getInt(1));
				 * e.setName(rs.getString(2)); e.setAddress(rs.getString(3));
				 */
				// resultList.add(emp);

				sqlCount.setText(new Integer(count1).toString());
			}

			System.out.println("oracleDataTypeList types are====>" + oracleDataTypeList);

			System.out.println("coloumn in tables are shown below====>" + cl1);
			tc11.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
			tc22.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
			tc33.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
			table1.setItems(el1);
			// tc11.setCellFactory(TextFieldTableCell.forTableColumn());
			oracleColumnCount = cl1.size();
			System.out.println("oracle column list size is :" + cl1.size());
		}
		oracleCount.setText(""+count+count1);
	}

	/*
	 * public void addToOracleList() { List<Columns> selectedList; //selectedList =
	 * table.getSelectionModel().getSelectedItems();
	 * System.out.println("transferring to ORACLE" + selectedList);
	 * 
	 * for (Columns field : selectedList) { if
	 * (sc1.getSelectionModel().getSelectedItem() != null &&
	 * tab1.getSelectionModel().getSelectedItem() != null) { if
	 * (!cl1.contains(field)) { System.out.println("not present in oracle " +
	 * field); columnMapping("mysql", field); oracleFieldList.add(new
	 * Columns(field.getName(), field.getDataType())); oracleColumnCount++;
	 * cl1.add(new Columns(field.getName(), field.getDataType())); } else { Alert a
	 * = new Alert(AlertType.INFORMATION);
	 * a.setContentText("field is already present"); a.show(); }
	 * 
	 * } else { Alert a = new Alert(AlertType.INFORMATION);
	 * a.setContentText("Please select schema and table first"); a.show(); }
	 * //table1.setItems(cl1);
	 * 
	 * } System.out.println("final oracle list count is:;::" + oracleColumnCount);
	 * 
	 * }
	 */

	/*
	 * public void addToSqlList() { List<Columns> selectedList; //selectedList =
	 * table1.getSelectionModel().getSelectedItems();
	 * System.out.println("transferring to SQL" + selectedList); for (Columns field
	 * : selectedList) { if (sc.getSelectionModel().getSelectedItem() != null &&
	 * tab.getSelectionModel().getSelectedItem() != null) {
	 * 
	 * if (!cl.contains(field)) { System.out.println("not present in oracle " +
	 * field); // columnMapping("oracle", field); sqlFieldList.add(new
	 * Columns(field.getName(), field.getDataType())); sqlColumnCount++; cl.add(new
	 * Columns(field.getName(), field.getDataType()));
	 * 
	 * } else { Alert a = new Alert(AlertType.INFORMATION);
	 * a.setContentText("You are already added.."); a.show(); } } else { Alert a =
	 * new Alert(AlertType.INFORMATION);
	 * a.setContentText("Please select schema and table first"); a.show(); }
	 * //table.setItems(cl); }
	 * System.out.println("final sqlColumnCount list count is:;::" +
	 * sqlColumnCount); }
	 */

	public void columnMapping(String selected, Columns field) {
		if (selected.equals("mysql")) {
			if (field.getDataType().contains("INT")) {
				field.setDataType("NUMBER");
			}
			if (field.getDataType().contains("CHAR")) {
				field.setDataType("VARCHAR");
			}
			if (field.getDataType().contains("TIME")) {
				field.setDataType("TIMESTAMP");
			}
		}
		if (selected.equals("oracle")) {
			if (field.getDataType().contains("NUM")) {
				field.setDataType("INT");
			}

			if (field.getDataType().contains("CHAR")) {
				field.setDataType("VARCHAR(40)");
			}
		}

	}

	public void saveAllData() throws SQLException {

		int count = 0, batchSize = 20;

		try {
			System.out.println("new columns adding to sql is==>" + sqlFieldList);
			System.out.println("-----------------------------------------------");
			System.out.println("new columns adding to oracle is==>" + oracleFieldList);
			Statement st = con.createStatement();
			System.out.println("changing columns are saving.........");
			for (int i = 0; i < sqlFieldList.size(); i++) {
				System.out.println("provided sql is----> " + "alter table " + tab.getSelectionModel().getSelectedItem()
						+ " add (" + sqlFieldList.get(i).getName() + " "
						+ sqlFieldList.get(i).getDataType().toLowerCase() + ");");
				count++;
				st.addBatch("alter table " + tab.getSelectionModel().getSelectedItem() + " add ("
						+ sqlFieldList.get(i).getName() + " " + sqlFieldList.get(i).getDataType().toLowerCase() + ");");
				if (count % batchSize == 0) {
					st.executeBatch();
				}
				st.executeBatch();
			}

			Statement st1 = con1.createStatement();
			for (int i = 0; i < oracleFieldList.size(); i++) {
				count++;
				System.out.println("provided oracle query is is----> " + "alter table "
						+ tab1.getSelectionModel().getSelectedItem() + " add (" + oracleFieldList.get(i).getName() + " "
						+ oracleFieldList.get(i).getDataType().toLowerCase() + ");");
				st1.addBatch("alter table " + tab1.getSelectionModel().getSelectedItem() + " add "
						+ oracleFieldList.get(i).getName() + " " + oracleFieldList.get(i).getDataType().toLowerCase()
						+ "(10)");
				if (count % batchSize == 0) {
					System.out.println("inside if block...count");
					st1.executeBatch();
				}
				System.out.println("outside if block...count");

				System.out.println("After execute Batch...");
			}
			st1.executeBatch();
			System.out.println("totally " + count + " columns are added in oracle ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void migrateData() throws SQLException {
		sqlCount.setText("0");
		oracleCount.setText("0");
		int count = 0, batchSize = 100;
		count1 = 0;
		int totalColumn = 0;
		resultList.clear();
		/*
		 * if (sqlColumnCount <= oracleColumnCount) { totalColumn = oracleColumnCount -
		 * sqlColumnCount; }
		 */

		if (tab.getSelectionModel().getSelectedItem().equalsIgnoreCase(tab1.getSelectionModel().getSelectedItem())) {
			System.out.println("Both tables are having same name===>" + tab.getSelectionModel().getSelectedItem());
			{
				if (sqlColumnCount == oracleColumnCount) {
					System.out.println("Both are having same no of coloumns....." + sqlColumnCount);
					for (int i = 0; i < sqlDataTypeList.size(); i++) {
						for (int j = 0; j < oracleDataTypeList.size(); j++) {
							if (sqlDataTypeList.get(i).contains(oracleDataTypeList.get(j))) {
								System.out.println("all items are MATCHED");
								if (con != null && con1 != null) {
									st = con.createStatement();
									startDate = new Date();
									System.out.println("Migration started:::::::::" + startDate);

									int batch = 10; // Statement ss = con1.createStatement();

									PreparedStatement ps = con1.prepareStatement("insert into "
											+ tab1.getSelectionModel().getSelectedItem() + " values(?,?,?)");
									for (int k = 0; k < resultList.size(); k++) {
										count1++;
										ps.setInt(1, resultList.get(k).getId());
										ps.setString(2, resultList.get(k).getName());
										ps.setString(3, resultList.get(k).getAddress());

										ps.executeQuery();
										if (count1 % batch == 0) {

										}
									}
									oracleCount.setText(""+count+count1);

									System.out.println("all data are migrated...");
									endDate = new Date();
									long diff = endDate.getTime() - startDate.getTime();
									long diffSeconds = diff / 1000 % 60;
									long diffMinutes = diff / (60 * 1000) % 60;
									long diffHours = diff / (60 * 60 * 1000);
									long mili = TimeUnit.MILLISECONDS.toMillis(diff);
									System.out.println("mili second is :" + mili);
									System.out.println("total time to transfer data" + diffHours + ":" + diffMinutes
											+ ":" + diffSeconds);
									time.setText(diffHours + ":" + diffMinutes + ":" + diffSeconds + ":" + mili);

									System.out.println("Migration ended:::::::::" + new Date().toString());

								}

							}
						}

					}
				}
			}

		}

	}

	public void editingColumns() {

		choosen = "sql";

	}

	public void editingColumns1() {

		choosen = "oracle";

	}

	/*
	 * public void onColumnChange(TableColumn.CellEditEvent<Columns, String>
	 * productStringCellEditEvent) throws SQLException {
	 * 
	 * int index = 0;
	 * 
	 * if (choosen.equals("sql")) { column =
	 * table.getSelectionModel().getSelectedItem(); name1 = column.getName(); for
	 * (int i = 0; i < cl.size(); i++) { if
	 * (cl.get(i).getName().equals(column.getName())) { index = i; // col = column;
	 * } }
	 * 
	 * //tc1.setCellValueFactory( new PropertyValueFactory<Columns,
	 * String>(productStringCellEditEvent.getNewValue())); // cl.set(index, col); p1
	 * = productStringCellEditEvent.getNewValue();
	 * 
	 * // Statement st=con.createStatement(); String alter_query = "alter table " +
	 * tab.getSelectionModel().getSelectedItem() + " change " + name1 + " " + p1 +
	 * " " + column.getDataType() + "(20)";
	 * 
	 * st.executeUpdate(alter_query);
	 * 
	 * } else if (choosen.equals("oracle")) { col1 =
	 * table1.getSelectionModel().getSelectedItem(); name2 = col1.getName();
	 * 
	 * // alter table employee rename column empname to empname1;
	 * 
	 * tc11.setCellValueFactory( new PropertyValueFactory<Columns,
	 * String>(productStringCellEditEvent.getNewValue())); p2 =
	 * productStringCellEditEvent.getNewValue();
	 * 
	 * Statement st = con1.createStatement(); String alter_query = "alter table " +
	 * tab1.getSelectionModel().getSelectedItem() + " rename column " + name2 +
	 * " to " + p2;
	 * 
	 * st.executeQuery(alter_query); }
	 * 
	 * }
	 */

}
