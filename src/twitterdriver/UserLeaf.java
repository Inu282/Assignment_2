package twitterdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


//Composite, subject and observer pattern
public class UserLeaf extends UserSubject implements Composite, UserObservers{

   private String userID;
   private List<UserLeaf> following = new ArrayList<>();
   private ObservableList<UserLeaf> followingList = FXCollections.observableList(following);
   private List<String> myTweets = new ArrayList<>();
   private List<String> newsFeed = new ArrayList<>(Arrays.asList());
   private ObservableList<String> newsFeedList = FXCollections.observableList(newsFeed);
   private long createdUser;
   private long lastUpdatedTime=0;
    

    public UserLeaf(String newID) {
        this.userID = newID;
        this.createdUser=System.currentTimeMillis();
    }
    
    @Override
    public String getID() {
        return userID;
    }
    
    @Override
    public String toString() {
        return userID;
    }

    public long getCreationTime(){
        return createdUser;
    }
    public long getLastUpdatedTime(){
        return lastUpdatedTime;
    }

    @Override
    public void accept(UserGroup visitor) {
        visitor.visitUser(this);
    }

    @Override
    public void update(UserSubject subject, String tweet) {
        if (subject instanceof UserLeaf) {
            this.newsFeedList.add("-" + ((UserLeaf) subject).getID() + " : " + tweet);
            lastUpdatedTime=System.currentTimeMillis();
            this.newsFeedList.add("Last Updated: " + lastUpdatedTime);
        }
    }
    public ObservableList<UserLeaf> getFollowingList() {
        return followingList;
    }
    public void addFollowingList(UserLeaf user) {
        followingList.add(user);
    }

    public List<String> getMyTweets() {
        return myTweets;
    }

    public ObservableList<String> getNewsFeedList() {
        return newsFeedList;
    }
    
    public void tweetMessage (String tweet){
        myTweets.add(tweet);
        newsFeedList.add("-" + this.userID + " : " + tweet);
        lastUpdatedTime=System.currentTimeMillis();
        this.newsFeedList.add("Last Updated: " + lastUpdatedTime);
        updateFollowers(tweet);
    }
}