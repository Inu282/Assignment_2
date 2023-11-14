package twitterdriver;
//Visitor pattern 
public class TotalMessage implements UserGroup{
    
    private int TotalMessage=0;
    
    @Override
    public void visitUser(UserLeaf user) {

        setMessageTotal(getMessageTotal() + user.getMyTweets().size());
    }

    @Override
    public void visitGroup(GroupContainer group) {
       
    }

    public int getMessageTotal() {
        return TotalMessage;
    }

    public void setMessageTotal(int totalMessage) {
        TotalMessage = totalMessage;
    }
}