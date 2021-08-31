package oleg.bot.models;

public class Answer {

    private final String constr = "Вы сказали: ";

    private String answerBody;

    public String getAnswerBody() {
        return answerBody;
    }

    public void setMessageBody(String answerBody) {
        this.answerBody = answerBody;
    }

    public String modifyAnswer(){
        return constr + answerBody;
    }
}
