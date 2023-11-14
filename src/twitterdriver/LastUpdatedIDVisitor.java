package twitterdriver;

public class LastUpdatedIDVisitor implements UserGroup {
    String lastUpdatedUser = "No one";
    long recentUpdatedTime = 0;

    @Override
    public void visitUser(UserLeaf user) {

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