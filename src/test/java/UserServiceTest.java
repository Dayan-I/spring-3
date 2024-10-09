import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.vagapov.spring.Main;


@SpringBootTest(classes = Main.class)
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Test
    void createUserUser() {

    }

}