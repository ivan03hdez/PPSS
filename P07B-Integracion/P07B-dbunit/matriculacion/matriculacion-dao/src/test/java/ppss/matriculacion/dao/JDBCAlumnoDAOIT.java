package ppss.matriculacion.dao;

import org.apache.log4j.BasicConfigurator;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ppss.matriculacion.to.AlumnoTO;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Integracion-fase1")
class JDBCAlumnoDAOIT {

    //
    // I) ¿Que estrategia de integración esta siguiendo?
    // Ejecuta por orden los plugins añadidos del padre (pow.xml). Primero surefire y luego failsafe
    //

    private JDBCAlumnoDAO _alumnoDAO;
    private IDatabaseTester databaseTester;
    private final String cadena_conexionDB = "jdbc:mysql://localhost:3306/matriculacion?useSSL=false";

    @BeforeAll
    public static void only_once() {
        BasicConfigurator.configure();
    }

    @BeforeEach
    public void setUp() throws Exception {
        databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver",
                cadena_conexionDB, "root", "ppss");
    }

    @Test
    void testA1() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("33333333C");
        alumno.setNombre("Elena Aguirre Juarez");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.YEAR, 1985);
        cal.set(Calendar.MONTH, 1); //Nota: en la clase Calendar, el primer mes es 0
        cal.set(Calendar.DATE, 22);
        alumno.setFechaNacimiento(cal.getTime());

        IDatabaseConnection connection = databaseTester.getConnection();

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        FactoriaDAO factoria = new FactoriaDAO();
        factoria.getAlumnoDAO().addAlumno(alumno);

        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla3.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    void testA2() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("11111111A");
        alumno.setNombre("Alfonso Ramirez Ruiz");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.YEAR, 1982);
        cal.set(Calendar.MONTH, 1); //Nota: en la clase Calendar, el primer mes es 0
        cal.set(Calendar.DATE, 22);
        alumno.setFechaNacimiento(cal.getTime());

        IDatabaseConnection connection = databaseTester.getConnection();

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        FactoriaDAO factoria = new FactoriaDAO();
        Throwable exception = assertThrows(DAOException.class,
                () -> factoria.getAlumnoDAO().addAlumno(alumno));

        assertEquals("Error al conectar con BD", exception.getMessage());
    }

    @Test
    void testA3() throws Exception{
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("44444444D");
        alumno.setNombre(null);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.YEAR, 1982);
        cal.set(Calendar.MONTH, 1); //Nota: en la clase Calendar, el primer mes es 0
        cal.set(Calendar.DATE, 22);
        alumno.setFechaNacimiento(cal.getTime());

        IDatabaseConnection connection = databaseTester.getConnection();

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        FactoriaDAO factoria = new FactoriaDAO();
        Throwable exception = assertThrows(DAOException.class,
                () -> factoria.getAlumnoDAO().addAlumno(alumno));

        assertEquals("Error al conectar con BD", exception.getMessage());
    }

    @Test
    void testA4() throws Exception{
        AlumnoTO alumno = null;

        IDatabaseConnection connection = databaseTester.getConnection();

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        FactoriaDAO factoria = new FactoriaDAO();
        Throwable exception = assertThrows(DAOException.class,
                () -> factoria.getAlumnoDAO().addAlumno(alumno));

        assertEquals("Error al conectar con BD", exception.getMessage());

    }

    @Test
    void testA5() throws Exception{
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif(null);
        alumno.setNombre("Pedro Garcia Lopez");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.YEAR, 1982);
        cal.set(Calendar.MONTH, 1); //Nota: en la clase Calendar, el primer mes es 0
        cal.set(Calendar.DATE, 22);
        alumno.setFechaNacimiento(cal.getTime());

        IDatabaseConnection connection = databaseTester.getConnection();

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        FactoriaDAO factoria = new FactoriaDAO();
        Throwable exception = assertThrows(DAOException.class,
                () -> factoria.getAlumnoDAO().addAlumno(alumno));

        assertEquals("Error al conectar con BD", exception.getMessage());
    }

    @Test
    void testB1() throws Exception{
        IDatabaseConnection connection = databaseTester.getConnection();

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        FactoriaDAO factoria = new FactoriaDAO();
        factoria.getAlumnoDAO().delAlumno("11111111A");

        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla4.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    void testB2() throws Exception{
        IDatabaseConnection connection = databaseTester.getConnection();

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        FactoriaDAO factoria = new FactoriaDAO();
        Throwable exception = assertThrows(DAOException.class,
                () -> factoria.getAlumnoDAO().delAlumno("33333333C"));

        assertEquals("No se ha borrado ningun alumno", exception.getMessage());

    }

}