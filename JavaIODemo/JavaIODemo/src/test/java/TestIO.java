import com.isoft.bean.StudentSystem;
import org.junit.Test;

import java.io.IOException;

public class TestIO {
    public static void main(String[] args) throws IOException, InterruptedException {
        StudentSystem studentSystem = new StudentSystem();
        studentSystem.login();
    }
}
