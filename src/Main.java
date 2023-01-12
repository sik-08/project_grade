import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        Professor professor = null;
        Student student = null;

        GradeManagement.printNotice();
        GradeManagement.loadData();

        String[] role = {"PROFESSOR", "STUDENT"};
        switch(role[GradeManagement.selectMenu() - 1]){
            case "PROFESSOR":
                professor = Professor.accessProfessor();
                break;

            case "STUDENT":
                student = Student.accessStudent();
                break;
        }

        if(Objects.nonNull(professor))  GradeManagement.startProfessor(professor);
        else                            GradeManagement.startStudent(student);

        if(GradeManagement.check)       GradeManagement.outputData();
    }
}