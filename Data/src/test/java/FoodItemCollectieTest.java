import models.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
class FoodItemCollectieTest {

    @Mock
    ItemValidator itemValidator = Mockito.mock(ItemValidator.class);

    ItemValidator test = new ItemValidator100g();

    @Spy
    @InjectMocks
    ItemCollectie itemCollectie = new FoodItemCollectie(itemValidator);




    @Test
    void itShouldFoodItemAanmaken() {
        Mockito.when(itemValidator.valideerWaardes(anyString(), anyDouble(),  anyDouble(),  anyDouble(), anyDouble())).thenReturn(true);
        Item result = itemCollectie.foodItemAanmaken("test", 100, 10, 20 ,30);
        //Then
        assertTrue(result instanceof FoodItem);

    }


}