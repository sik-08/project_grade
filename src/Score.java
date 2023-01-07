public class Score{
    public String subject;
    public String grade;
    public String score;

    Score(String subject){
        this.subject = subject;
        this.grade = "미입력";
        this.score = "미입력";
    }

    Score(String subject, String grade, String score){
        this.subject = subject;
        this.grade = grade;
        this.score = score;
    }


    public static void printCourse(){
        System.out.println("\n2023년도 1학기 강의 목록입니다.");

        System.out.println("[데이터 구조] "
                + "[웹 프로그래밍] "
                + "[시스템 프로그래밍] "
                + "[컴퓨터 네트워크] "
                + "[객체지향 프로그래밍]\n");
    }

    public void printScore(){
        System.out.printf("|%-20s|%5s|%5s|\n",subject,score,grade);
    }
}
