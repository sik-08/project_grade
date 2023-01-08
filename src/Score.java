public class Score{
    private String subject;
    private String score;
    private String grade;

    Score(String subject){
        this.subject = subject;
        this.score = "미입력";
        this.grade = "미입력";
    }

    Score(String subject, String score, String grade){
        this.subject = subject;
        this.score = score;
        this.grade = grade;
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
        System.out.printf("[ %s ] / [ %s ] / [ %s ]\n",subject,score,grade);
    }

    // Getter, Setter

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
