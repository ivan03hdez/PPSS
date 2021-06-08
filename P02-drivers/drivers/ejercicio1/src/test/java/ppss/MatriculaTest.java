package ppss;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@Tag("noParametrizados") ///SI anotamos una clase con un tag, automaticamente todos sus test incluiran ese tag.
class MatriculaTest {
    boolean familiaNumerosa,repetidor;
    int edad;
    float expected,actual;
    @Tag("rapido")
    @Test
    public void C1_testCalculaTasaMatricula(){
        familiaNumerosa = false;
        repetidor = true;
        edad = 19;
        expected = 2000;
        Matricula m = new Matricula();
        actual = m.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        assertEquals(expected, actual);
    }
    @Test
    public void C2_testCalculaTasaMatricula(){
        familiaNumerosa = false;
        repetidor = true;
        edad = 68;
        expected = 250;
        Matricula m = new Matricula();
        actual = m.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        assertEquals(expected, actual);
    }
    @Test
    public void C3_testCalculaTasaMatricula(){
        familiaNumerosa = true;
        repetidor = true;
        edad = 19;
        expected = 250;
        Matricula m = new Matricula();
        actual = m.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        assertEquals(expected, actual);
    }
    @Test
    public void C4_testCalculaTasaMatricula(){
        familiaNumerosa = false;
        repetidor = false;
        edad = 19;
        expected = 500;
        Matricula m = new Matricula();
        actual = m.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        assertEquals(expected, actual);
    }
    @Test
    public void C5_testCalculaTasaMatricula(){
        familiaNumerosa = false;
        repetidor = false;
        edad = 61;
        expected = 400;
        Matricula m = new Matricula();
        actual = m.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        assertEquals(expected, actual);
    }
    @Test
    public void C6_testCalculaTasaMatricula(){
        familiaNumerosa = true;
        repetidor = true;
        edad = 60;
        expected = 400;
        Matricula m = new Matricula();
        actual = m.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        assertEquals(expected, actual);
    }


}