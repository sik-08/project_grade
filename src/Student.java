import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Student {
    private String studentNum; // 학번
    private String name; // 이름
    private int year; // 학년
    private ArrayList<Score> score = new ArrayList<>(5); // 점수 정보

    public Student(){

    }
    public Student(int code) throws IOException {
        if(code != 2) System.exit(1);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("------------------------------------------------------");
        System.out.println("[학생 신규 등록]");
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

    public static Student insertData(StringTokenizer st){
        Student student = new Student();
        student.studentNum = st.nextToken();
        student.name = st.nextToken();
        student.year = Integer.parseInt(st.nextToken());

        do{
            student.score.add(new Score(st.nextToken(), st.nextToken(), st.nextToken()));
        }while(st.hasMoreTokens());

        return student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Score> getScore() {
        return score;
    }

    public void setScore(ArrayList<Score> score) {
        this.score = score;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
