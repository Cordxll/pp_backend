package ProductivePeople.Service;

import ProductivePeople.Model.User;
import ProductivePeople.Repository.UserSpringDataJPA;
import ProductivePeople.Security.AuthenticationRequest;
import ProductivePeople.Security.AuthenticationService;
import ProductivePeople.Security.RegisterRequest;
import ProductivePeople.Security.UpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserSpringDataJPA repository;
    private final PasswordEncoder passwordEncoder;

    public Result login(AuthenticationRequest request) {
        Result result = new Result();
        Result passwordResult = validatePassword(request.getPassword());

        if(repository.findByUsername(request.getUsername()).isEmpty()) {
            result.addErrorMessage("No user found");
            result.success = false;

        }

        return result;
    }

    public Result create(RegisterRequest request) {
        Result result = validate(request.getFullName(),request.getEmail());
        Result passwordResult = validatePassword(request.getPassword());
        Result usernameResult = validateUsername(request.getUsername());

        if(passwordResult.getMessages().size() > 0) {
            for (String message : passwordResult.getMessages()) {
                result.addErrorMessage(message);
            }
        }
        if(usernameResult.getMessages().size() > 0) {
            for (String message : usernameResult.getMessages()) {
                result.addErrorMessage(message);
            }
        }


        if(result.getMessages().size() > 0) {
            result.success = false;
            return result;
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        result.setPayload(user);
        return result;
    }

    public Result update(UpdateRequest request) {
        Result result = new Result();

        User user = repository.findById(request.getId()).orElseThrow();
        if(!Objects.equals(user.getUsername(),request.getUsername())){
            result = validateUsername(request.getUsername());
            user.setUsername(request.getUsername());
        }

        Result result2 = validate(request.getFullName(), request.getEmail());

        if(result2.getMessages().size() > 0) {
            for (String message : result2.getMessages()) {
                result.addErrorMessage(message);
            }
        }

        if(result.getMessages().size() > 0) {
            result.success = false;
            return result;
        }

        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPictureUrl(request.getPictureUrl());
        user.setPassword(user.getPassword());


        result.setPayload(user);
        return result;
    }

    public Result validatePassword(String password) {
        Result result = new Result();
        if (Objects.equals(password, "") || password.length() < 5) {
            result.addErrorMessage("Password should be at least 5 characters");
        }

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        boolean containsNum = false;
        for (int i = 0; i < nums.length; i++) {
            if ((!Objects.equals(password, "")) && password.contains(String.valueOf(nums[i]))) {
                containsNum = true;
                break;
            }
        }
        if (!containsNum) {
            result.addErrorMessage("Password must contain at least one number");
        }

        return result;

    }

    public Result validateUsername(String username) {
        Result result = new Result();
        if(Objects.equals(username, "")){
            result.addErrorMessage("Username is required");
        }
        if (username.length() < 5) {
            result.addErrorMessage("Username should be at least 5 characters");
        }

        if(repository.findAll().stream().anyMatch(u -> u.getUsername().equals(username))) {
            result.addErrorMessage("This username already exists");
        }
        return result;
    }



    public Result validate(String fullName,String email) {
        Result result = new Result();
        if(Objects.equals(fullName, "")){
            result.addErrorMessage("We’re big on real names around here.");
        }
        if(Objects.equals(email, null) || !(email.endsWith(".com") && email.contains("@"))) {
            result.addErrorMessage("Not a valid email address");
        }
        return result;
    }

}
