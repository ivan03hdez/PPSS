package ppss;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Tag("parametrizados")
public class MatriculaParamTest {
    /*
    Si el código de varios tests es idéntico a excepción de los valores concretos del caso de
    prueba que cada uno implementa, podemos sustituirlos por un test parametrizado.

    Se trata de implementar un único test, que anotaremos con @ParameterizedTest, y
    que tendrá como parámetros los valores concretos en los que se diferencian los tests a
    los que sustituye.

    Si el test parametrizado solamente necesita un parámetro, de tipo primitivo o String,
    usaremos la anotación @ValueSource para indicar los valores para ese parámetro
     */
    boolean familiaNumerosa,repetidor;
    int edad;
    float expected,actual;
    @Tag("rapido")
    @ParameterizedTest
    @MethodSource("casosDePrueba")
    public void testCalculaTasaMatricula(boolean familiaNumerosa,boolean repetidor,int edad,double expected){
        Matricula m = new Matricula();
        assertEquals(expected, m.calculaTasaMatricula(edad,familiaNumerosa,repetidor));
    }
    public static Stream<Arguments> casosDePrueba(){
        return Stream.of(
                Arguments.of(false,true,19,2000),
            Arguments.of(false,true,68,250),
            Arguments.of(true,true,19,250),
            Arguments.of(false,false,19,500),
            Arguments.of(false,false,61,400),
            Arguments.of(true,true,60,400));
    }
}
