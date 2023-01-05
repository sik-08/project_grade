import java.util.ArrayList;

public class Score{
    // 최대 수강 과목 5개로 제한
    private String studentNum;
    public ArrayList<String> subject = new ArrayList<>(5); // 수강 과목
    public ArrayList<Character> grade = new ArrayList<>(5); // 등급
    public ArrayList<Integer> score = new ArrayList<>(5); // 점수

    Score(String studentNum){
        this.studentNum = studentNum;
    }
}
