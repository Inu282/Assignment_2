package twitterdriver;

public class LastUpdatedID implements UserGroup{
    String lastUpdatedUser = "None";
    long recentUpdatedTime = 0;

    @Override
    public void visitUser(UserLeaf user){
        if (user.getLastUpdatedTime() > recentUpdatedTime){
            recentUpdatedTime = user.getLastUpdatedTime();
            lastUpdatedUser = user.getID();
    }
}
    @Override
    public void visitGroup(GroupContainer group) {

    }

    public String getLastUpdateUser() {
        return lastUpdatedUser;
    }
}
