package twitterdriver;
//Visitor pattern
public class TotalUser implements UserGroup{
    
    private int totalUser=0;
    
    @Override

    public void visitUser(UserLeaf user) {
        setUserTotal(getUserTotal() + 1);
    }

    @Override
    public void visitGroup(GroupContainer group) {

    }

    public int getUserTotal() {
        return totalUser;
    }

    public void setUserTotal(int totalUser) {
        this.totalUser = totalUser;
    }
}