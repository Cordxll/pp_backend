package ProductivePeople.Service;

import ProductivePeople.Model.User;
import ProductivePeople.Repository.UserSpringDataJPA;
import ProductivePeople.Security.AuthenticationRequest;
import ProductivePeople.Security.AuthenticationService;
import ProductivePeople.Security.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserSpringDataJPA repository;

    public Result login(AuthenticationRequest request) {
        Result result = new Result();
        if(repository.findByUsername(request.getUsername()).isEmpty() || !repository.findByUsername(request.getUsername()).get().getPassword().equals(request.getPassword())) {
            result.addErrorMessage("No user found or incorrect password");
            result.success = false;
        }
        return result;
    }

    public Result create(RegisterRequest request) {
        Result result = validate(request.getUsername(), request.getPassword(), request.getEmail());

        if(result.getMessages().size() > 0) {
            result.success = false;
            return result;
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        result.setPayload(user);
        return result;
    }

    public Result validate(String username, String password, String email) {
        Result result = new Result();

        if(repository.findAll().stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(username))) {
            result.addErrorMessage("This username already exists");
        } else if(password.length() < 5) {
            result.addErrorMessage("Password should be at least 5 characters");
        } else if(!email.endsWith(".com") && !email.contains("@")) {
            result.addErrorMessage("Not a valid email address");
        }

        int[] nums = new int[9];
        boolean containsNum = false;
        for (int i = 0; i < nums.length; i++) {
            if(password.contains(String.valueOf(nums[i]))) {
                containsNum = true;
                break;
            }
        }

        if(!containsNum) {
            result.addErrorMessage("Password must contain at least one number");
        }

        return result;

    }

}
