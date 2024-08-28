
import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class FelineTest {

    private Feline feline;

    @Parameterized.Parameter
    public String animalKind;

    @Parameterized.Parameter(1)
    public List<String> expectedFood;

    @Parameterized.Parameter(2)
    public boolean throwsException;

    @Before
    public void setUp() {
        feline = new Feline();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Хищник", Arrays.asList("Животные", "Птицы", "Рыба"), false},
                {"Травоядное", Arrays.asList("Трава", "Различные растения"), false},
                {"Неизвестное", null, true} // ожидаем исключение
        });
    }

    @Test
    public void testGetFood() throws Exception {
        if (throwsException) {
            Exception exception = null;
            try {
                feline.getFood(animalKind);
            } catch (Exception e) {
                exception = e;
            }
            assertNotNull(exception);
            assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", exception.getMessage());
        } else {
            List<String> actualFood = feline.getFood(animalKind);
            assertNotNull(actualFood);
            assertEquals(expectedFood, actualFood);
        }
    }

    @Test
    public void testGetFamily() {
        String expectedFamily = "Кошачьи";
        String actualFamily = feline.getFamily();
        assertEquals(expectedFamily, actualFamily);
    }

    @Test
    public void testGetKittensReturnsDefaultValue() {
        int expectedKittensCount = 1;
        int actualKittensCount = feline.getKittens();
        assertEquals(expectedKittensCount, actualKittensCount);
    }

    @Test
    public void testGetKittensWithParameter() {
        int inputKittensCount = 5; // Пример значения для передачи
        int expectedKittensCount = 5;
        int actualKittensCount = feline.getKittens(inputKittensCount);
        assertEquals(expectedKittensCount, actualKittensCount);
    }

    @Test
    public void testEatMeat() throws Exception {
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        List<String> actualFood = feline.eatMeat();
        assertNotNull(actualFood);
        assertEquals(expectedFood, actualFood);
    }
}