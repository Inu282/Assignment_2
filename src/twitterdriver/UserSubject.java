package twitterdriver;
import java.util.ArrayList;
import java.util.List;

//Observer pattern
public class UserSubject {
    private List<UserObservers> followers = new ArrayList<>();

    public void attach(UserObservers observer) {
        followers.add(observer);
    }

    public void updateFollowers(String message) {
        for(UserObservers observer : this.followers) {
            observer.update(this, message);
        }
    }

}