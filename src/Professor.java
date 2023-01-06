import java.util.ArrayList;

public class Professor {

    // 교수 접근 권한 키
    private static final String ACCESS_KEY = "abc3323";
    private ArrayList<Score> score;

    public void update(Student student){
        score = student.getScore();
    }

    public void modify(Student student){

    }

    public static int access(String key){
        if(key.equals(ACCESS_KEY)) return 1;
        else return 0;
    }
}
