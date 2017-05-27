package asw1022.managers;

import asw1022.util.functional.IPredicate;
import asw1022.model.User;
import asw1022.repositories.IRepository;
import asw1022.repositories.UserRepository;
import java.util.List;

/**
 * This manager encapsulates complex queries on a user repository.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public class UserManager {
    
    private IRepository<User> repo;
    
    public UserManager(IRepository<User> repo){
        this.repo = repo;
    }
    
    public User findByUsernameAndPassword(final String username, final String password){
        return find(new IPredicate<User>() {
            @Override
            public boolean test(User obj) {
                return obj.getName().equals(username) && obj.getPassword().equals(password);
            }
        });
    }
    
    public User findByUsername(final String username){
        return find(new IPredicate<User>() {
            @Override
            public boolean test(User obj) {
                return obj.getName().equals(username);
            }
        });
    }
    
    public User find(IPredicate<User> pred){
        List<User> users = repo.readAll();
        for (User us : users) {
            if (pred.test(us)) {
                return us;
            }
        }
        return null;        
    }
    
    public void addUser(String username, String password){
        User newuser = new User();
        newuser.setName(username);
        newuser.setPassword(password);
        repo.add(newuser);
    }
    
    
}
