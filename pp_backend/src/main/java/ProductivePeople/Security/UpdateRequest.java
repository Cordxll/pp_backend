package ProductivePeople.Security;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {
    private Integer id;
    private String fullName;
    private String username;
    private String email;
    private String pictureUrl;
//    private String password;

}
