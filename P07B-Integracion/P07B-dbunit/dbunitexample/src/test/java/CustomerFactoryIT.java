import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.apache.log4j.BasicConfigurator;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.Customer;
import ppss.CustomerFactory;

import static org.junit.Assert.assertEquals;

// B)
// _customerFactory -> clase a probar, se instanciará para contener nuestro SUT.
// databaseTester -> Necesitamos una instancia de IDatabaseTester para acceder a la BD

// C)
// table -> representa una colección de datos tabulares
// dataset-> representa una colección de tablas

// Se utiliza dataset para sacar todas las tablas que contiene nuestra base de datos y
// table para seleccionar de ese conjunto dataset una tabla en específico para poder realizar assert.

public class CustomerFactoryIT {
  
  private CustomerFactory _customerFactory;
  private IDatabaseTester databaseTester;
  private final String cadena_conexionDB = "jdbc:mysql://localhost:3306/DBUNIT?useSSL=false";

  @BeforeAll
  public static void only_once() {
    // Para evitar el mensaje
    // SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
    // SLF4J: Defaulting to no-operation (NOP) logger implementation
    BasicConfigurator.configure();
    
  }
    
  @BeforeEach
  public void setUp() throws Exception {
    databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver",
            cadena_conexionDB, "root", "ppss");
    
    //Como cada test requiere una inicialización diferente de la BD, en lugar
    //de inicializar la BD aquí lo haremos en cada uno de los tests
    /*
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/customer-init.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();
     */
    _customerFactory = CustomerFactory.getInstance();
  }
	
  @Test
  public void testInsert() throws Exception {
    Customer customer = _customerFactory.create(1,"Jony", "Smith");
    customer.setStreet("1 Main Street");
    customer.setCity("Anycity");
      

    IDatabaseConnection connection = databaseTester.getConnection();
    // configuramos la conexión como de tipo mysql
    // para evitar el mensaje: 
    // WARN org.dbunit.dataset.AbstractTableMetaData - Potential problem found: 
    //      The configured data type factory 'class org.dbunit.dataset.datatype.DefaultDataTypeFactory' 
    //      might cause problems with the current database 'MySQL' (e.g. some datatypes may not be supported properly). 
    //      ...
    DatabaseConfig dbconfig = connection.getConfig();
    dbconfig.setProperty("http://www.dbunit.org/properties/datatypeFactory", new MySqlDataTypeFactory());
      
    //Inicializamos la tabla customer de la BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/customer-init.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    _customerFactory.insert(customer);

    IDataSet databaseDataSet = connection.createDataSet();
    ITable actualTable = databaseDataSet.getTable("customer");

    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/customer-expected.xml");
      
    ITable expectedTable = expectedDataSet.getTable("customer");

    Assertion.assertEquals(expectedTable, actualTable);

   }

  @Test
  public void testDelete() throws Exception {
    Customer customer = _customerFactory.create(1,"Jony", "Smith");
    customer.setStreet("1 Main Street");
    customer.setCity("Anycity");
    
    IDatabaseConnection connection = databaseTester.getConnection();
      
    //para evitar un warning al acceder a la BD
    DatabaseConfig dbconfig = connection.getConfig();
    dbconfig.setProperty("http://www.dbunit.org/properties/datatypeFactory", new MySqlDataTypeFactory());

    //Inicializamos la tabla customer de la BD
      IDataSet dataSet = new FlatXmlDataFileLoader().load("/customer-expected.xml");

    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    _customerFactory.delete(customer);
      
    IDataSet databaseDataSet = connection.createDataSet();
    int rowCount = databaseDataSet.getTable("customer").getRowCount();
	        
    Assertions.assertEquals(0, rowCount);
  }


    @Test
    public void testUpdate() throws Exception {
        IDatabaseConnection connection = databaseTester.getConnection();

        DatabaseConfig dbconfig = connection.getConfig();
        dbconfig.setProperty("http://www.dbunit.org/properties/datatypeFactory", new MySqlDataTypeFactory());

        //Inicializamos la tabla customer de la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/customer-expected.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        Customer customer = _customerFactory.create(1,"John", "Smith");
        customer.setStreet("Other Street");
        customer.setCity("NewCity");

        _customerFactory.update(customer);

        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("customer");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/customer-expected2.xml");

        ITable expectedTable = expectedDataSet.getTable("customer");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testRetieve() throws Exception {
        Customer customer = _customerFactory.create(1,"Jony", "Smith");
        customer.setStreet("1 Main Street");
        customer.setCity("Anycity");

        IDatabaseConnection connection = databaseTester.getConnection();
        DatabaseConfig dbconfig = connection.getConfig();
        dbconfig.setProperty("http://www.dbunit.org/properties/datatypeFactory", new MySqlDataTypeFactory());

        //Inicializamos la tabla customer de la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/customer-init.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        _customerFactory.insert(customer);

        Customer actualcustomer = _customerFactory.retrieve(1);

        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("customer");

        Assertions.assertEquals(actualcustomer.getId(), customer.getId());
        Assertions.assertEquals(actualcustomer.getCity(), customer.getCity());
        Assertions.assertEquals(actualcustomer.getFirstName(), customer.getFirstName());
        Assertions.assertEquals(actualcustomer.getLastName(), customer.getLastName());
        Assertions.assertEquals(actualcustomer.getStreet(), customer.getStreet());


    }


}
