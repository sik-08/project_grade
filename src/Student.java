import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Student {
    private String studentNum; // 학번
    private String name; // 이름
    private char year; // 학년
    private ArrayList<Score> score; // 점수 정보

    public Student() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("------------------------------------------------------");
        System.out.println("[신규 등록]");
        System.out.println("***     학생 정보를 입력하여주십시오.    ***");
        System.out.print("- 학번 : "); studentNum = br.readLine();
        System.out.print("- 이름 : "); name = br.readLine();
        System.out.print("- 학년 : "); year = (char)br.read();
        System.out.println("- 수강 과목 (ex. 데이터 구조,웹 프로그래밍,...)");
        System.out.println("','를 기준으로 나열하여 주세요. (최대 개수 = 5개)");
        System.out.print("-> ");
        StringTokenizer st = new StringTokenizer(br.readLine(), ",");
        // Save
        br.close();

        System.out.println("***     등록이 완료되었습니다.    ***");
        System.out.println("------------------------------------------------------");
    }

    public void printPoint(){
        // 수강 과목이 존재한다면 점수 정보 출력
        System.out.println("------------------------------------------------------");
        if(score.size() != 0){
            for(int i = 0; i < score.size(); i++){
                System.out.println("| " + score.get(i) + " |"
                        + "| " + score.get(i) + " |"
                        + "| " + score.get(i) + " |");
            }
        }else{
            System.out.println("수강 정보가 없습니다.");
        }
        System.out.println("------------------------------------------------------");
    }

    public void printProfile(){
        System.out.println("------------------------------------------------------");
        System.out.println("[" + name + " 학생 정보]");
        System.out.println("| " + studentNum + " |" + "| " + name + " |" + "| " + year + "학년 |");
        System.out.println("------------------------------------------------------");
    }

}
