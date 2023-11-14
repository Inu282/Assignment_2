package twitterdriver;
//Visitor pattern 
public class TotalGroup implements UserGroup{

    private int totalGroup=0;
    
    public void visitUser(UserLeaf user){

    }
    @Override
    public void visitGroup(GroupContainer group) {
        setGroupTotal(getGroupTotal() + 1);
    }

    public int getGroupTotal() {
        return totalGroup;
    }

    public void setGroupTotal(int totalUser) {
        this.totalGroup = totalUser;
    }
}
    

