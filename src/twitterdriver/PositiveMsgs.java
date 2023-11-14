package twitterdriver;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Visitor pattern 
public class PositiveMsgs implements UserGroup{
    private double positiveCount = 0;
    private double totalMessages = 0;
    private double positivePercentage = 0;
    private List<String> positiveWords= new ArrayList<>
        (Arrays.asList("well done","exciting", "good", "lit", "awesome", "great", "excellent", "nice","interesting", "banging", "happy"));

    @Override
    public void visitUser(UserLeaf user) {
        for (String message : user.getMyTweets()){
 
            totalMessages +=1;
            for (String positive : positiveWords){
                if (message.toLowerCase().contains(positive.toLowerCase())){
                    positiveCount +=1;
                    break;
                }
            }
        }
    }

    @Override
    public void visitGroup(GroupContainer group) {

    }

    public double getPositivePercentage() {
        if (totalMessages ==0) {
            return positivePercentage;
        }

        setPositivePercentage((positiveCount/totalMessages)*100.0);
        return (positivePercentage);
    }

    public void setPositivePercentage(double positivePercentage) {
        this.positivePercentage = positivePercentage;
    }
}

