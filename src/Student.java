import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Student {
    private final String studentNum; // 학번
    public String name; // 이름
    public int year; // 학년
    private ArrayList<Score> score = new ArrayList<>(5); // 점수 정보

    public Student() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("------------------------------------------------------");
        System.out.println("[성적 산출 프로그램 신규 등록]");
        System.out.println("***     학생 정보를 입력하여주십시오.    ***");

        System.out.print("- 학번 : "); studentNum = br.readLine();

        System.out.print("- 이름 : "); name = br.readLine();

        System.out.print("- 학년 : "); year = Integer.parseInt(br.readLine());

        Score.printCourse();

        System.out.println("수강 과목을 ','를 기준으로 나열하여 주세요.\n" +
                            "(ex. 데이터 구조,웹 프로그래밍,...) (최대 개수 = 5개)");
        System.out.print("-> ");

        ArrayList<String> subject = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), ",");

        while(st.hasMoreTokens())
            subject.add(st.nextToken());

        Collections.sort(subject);
        for(String s : subject)
            score.add(new Score(s));

        br.close();

        System.out.println("***     등록이 완료되었습니다.    ***");
        System.out.println("------------------------------------------------------");
    }

    public void printProfile(){
        System.out.println("------------------------------------------------------");
        System.out.println("[" + name + " 학생 정보]");
        System.out.println("| " + studentNum + " | " + name + " | " + year + "학년 |\n");

        System.out.println("[" + name + " 학생 수강 목록]");
        for(Score s : score) System.out.println("| " + s.subject + " |");
        System.out.println("------------------------------------------------------");
    }

    public ArrayList<Score> getScore() {
        return score;
    }

    public String getStudentNum() {
        return studentNum;
    }
}
