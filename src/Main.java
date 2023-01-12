import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        GradeManagement.printNotice();
        GradeManagement.loadData();

        if(Objects.nonNull(professor))  GradeManagement.startProfessor(professor);
        else                            GradeManagement.startStudent(student);

        if(GradeManagement.check)       GradeManagement.outputData();
    }
}