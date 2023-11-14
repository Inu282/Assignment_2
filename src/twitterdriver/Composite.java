package twitterdriver;

//Composite pattern
public interface Composite {
    public String getID();
    
    public String toString();
    
    public void accept(UserGroup visitor);
}
