
package twitterdriver;

public interface UserGroup {
    public void visitUser(UserLeaf user);
    public void visitGroup(GroupContainer group);
    
}
