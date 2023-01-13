import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Professor {
    // 교수 접근 권한 키
    private static final String ACCESS_KEY = "abc3323";
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public void update() throws IOException {
        Student student = setStudent();

        ArrayList<Score> score = student.getScore();

        String subject;

        int index;

        student.printProfile();

        System.out.println("성적을 열람하시겠습니까?");
        System.out.println("[1] 예 [2] 아니오");
        System.out.print("-> ");

        if(br.readLine().equals("1"))
            for(Score s : score) s.printScore();

        System.out.println("\n성적을 입력 또는 수정할 과목의 이름을 입력해주세요.");
        System.out.print("-> ");
        subject = br.readLine();

        for(index = 0; index < score.size(); index++)
            if(score.get(index).getSubject().equals(subject)) break;

        System.out.println("\n[ " + student.getName() + " 학생의 " + score.get(index).getSubject() + " 성적 입력 ]\n");

        do{
            System.out.print("점수 : ");
            score.get(index).setScore(br.readLine());
            System.out.print("등급 : ");
            score.get(index).setGrade(br.readLine());

            System.out.println("\n성적을 확정하시겠습니까?");
            System.out.println("[1] 예 [2] 아니오");
            System.out.print("-> ");
        }while(br.readLine().equals("2"));
    }

    public static Professor accessProfessor() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("교직원 접근 권한이 필요합니다.");
        System.out.print("ACCESS KEY 입력 : ");
        if(br.readLine().equals(ACCESS_KEY)){
            System.out.println("\n환영합니다.\n");
            return new Professor();
        }
        return null;
    }

    private Student setStudent() throws IOException{
        System.out.println("성적을 입력할 학생의 이름을 입력해주세요.");
        System.out.print("-> ");

        return GradeManagement.searchStudent(br.readLine());
    }
}
