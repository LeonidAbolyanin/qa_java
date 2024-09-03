import com.example.Cat;
import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class CatTest {

    private Cat cat;
    private Feline mockFeline;

    // Параметры для тестирования метода getFood
    @Parameterized.Parameter
    public List<String> mockedFood;

    @Parameterized.Parameter(1)
    public boolean throwsException;

    @Before
    public void setUp() {
        // Создаем мок для Feline
        mockFeline = mock(Feline.class);
        cat = new Cat(mockFeline);
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {Arrays.asList("Животные", "Птицы", "Рыба"), false}, // Ожидаем, что метод УКАЗЫВАЕТ на правильный тип пищи
                {null, true} // Ожидаем исключение
        };
    }

    @Test
    public void testGetSound() {
        String expectedSound = "Мяу";
        String actualSound = cat.getSound();
        assertEquals(expectedSound, actualSound);
    }

    @Test
    public void testGetFood() throws Exception {
        if (throwsException) {
            when(mockFeline.eatMeat()).thenThrow(new Exception("Ошибка получения пищи"));
            Exception exception = assertThrows(Exception.class, cat::getFood);
            assertEquals("Ошибка получения пищи", exception.getMessage());
        } else {
            when(mockFeline.eatMeat()).thenReturn(mockedFood);
            List<String> actualFood = cat.getFood();
            assertNotNull(actualFood);
            assertEquals(mockedFood, actualFood);
        }
    }

    @Test
    public void testCatInitialization() {
        assertNotNull(cat);
        assertEquals(mockFeline, cat.predator);
    }
}