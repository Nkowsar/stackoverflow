package stackOverFlow.security.dtos;

import lombok.Data;

@Data
public class SignupDto {

    private Long id;
    private String FullName;
    private String Email;
    private String Password;
}
