import java.util.ArrayList;

public class Professor {

    private ArrayList<Score> score;

    public void update(Student student){
        score = student.getScore();
    }

    public void modify(Student student){

    }
}
