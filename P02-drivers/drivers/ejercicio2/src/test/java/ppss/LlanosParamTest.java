package ppss;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays; ///para usar Arrays.asList()
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Tag("parametrizados")
public class LlanosParamTest {
    private static Object Argument;

    @ParameterizedTest()
    @MethodSource("casosDePrueba")
    public void testCalculaLlano(Tramo expected,ArrayList<Integer> list){
        Llanos l = new Llanos();
        assertEquals(expected,l.buscarTramoLlanoMasLargo(list));
    }
    public static Stream<Arguments> casosDePrueba(){
        return Stream.of(Arguments.of(new Tramo(0,0),new ArrayList(Arrays.asList(3))),
                Arguments.of(new Tramo(0,0),new ArrayList(Arrays.asList(-1))),
                Arguments.of(new Tramo(0,1),new ArrayList(Arrays.asList(100,100))),
                Arguments.of(new Tramo(0,3),new ArrayList(Arrays.asList(-1,-1,-1,-1))),
                Arguments.of(new Tramo(0,2),new ArrayList(Arrays.asList(180,180,180))),
                Arguments.of(new Tramo(2,2),new ArrayList(Arrays.asList(120,140,-10,-10,-10))));
    }
}
