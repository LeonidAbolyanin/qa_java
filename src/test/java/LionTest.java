import com.example.Feline;
import com.example.Lion;
import com.example.Predator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class LionTest {

    private Predator mockFeline; // Мок объект для Feline
    private Lion lion; // Тестируемый объект Lion

    // Параметры для тестирования пола льва
    @Parameterized.Parameter
    public String sex;

    @Parameterized.Parameter(1)
    public boolean expectedHasMane;

    @Before
    public void setUp() throws Exception {
        mockFeline = mock(Feline.class); // Создаем мок для Feline
        lion = new Lion(sex, mockFeline); // Инъекция зависимости
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"Самец", true},  // Проверка: самец должен иметь гриву
                {"Самка", false}   // Проверка: самка не должна иметь гривы
        };
    }

    @Test(expected = Exception.class)
    public void testInvalidSex() throws Exception {
        new Lion("Неправильный пол", mockFeline); // Проверка на исключение при неправильном вводе
    }

    @Test
    public void testDoesHaveMane() {
        assertEquals(expectedHasMane, lion.doesHaveMane()); // Проверка наличия гривы
    }

    @Test
    public void testGetKittens() {
        when(mockFeline.getKittens()).thenReturn(3); // Мокируем ответ
        int kittensCount = lion.getKittens();
        assertEquals(3, kittensCount); // Проверка на количество котят
    }

    @Test
    public void testGetFood() throws Exception {
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба"); // Ожидаемый результат
        when(mockFeline.eatMeat()).thenReturn(expectedFood); // Мокируем ответ
        List<String> actualFood = lion.getFood();
        assertEquals(expectedFood, actualFood); // Проверка на списке пищи
    }
}