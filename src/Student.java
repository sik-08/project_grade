import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Student {
    public static final int ACCESS = 2;
    protected String studentNum; // 학번
    private String name; // 이름
    private char year; // 학년
    private Score score;

    public Student() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("------------------------------------------------------");
        System.out.println("[신규 등록]");
        System.out.println("***     학생 정보를 입력하여주십시오.    ***");
        System.out.print("학번 : "); studentNum = br.readLine();
        System.out.print("이름 : "); name = br.readLine();
        System.out.print("학년 : "); year = (char)br.read();

        br.close();

        score = new Score(studentNum);

        System.out.println("***     등록이 완료되었습니다.    ***");
        System.out.println("------------------------------------------------------");
    }


    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void print(){
        System.out.println("------------------------------------------------------");
        System.out.println("[" + name + " 학생 정보]");
        System.out.println("| " + studentNum + " |" + "| " + name + " |" + "| " + year + " |");

        // 수강 과목이 존재한다면 점수 정보 출력
        if(score.subject.size() != 0){
            for(int i = 0; i < score.subject.size(); i++){
                System.out.println("| " + score.subject.get(i) + " |"
                        + "| " + score.score.get(i) + " |"
                        + "| " + score.grade.get(i) + " |");
            }
        }else{
            System.out.println("수강 정보가 없습니다.");
        }
        System.out.println("------------------------------------------------------");
    }
}
