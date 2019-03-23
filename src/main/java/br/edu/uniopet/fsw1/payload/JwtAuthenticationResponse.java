package br.edu.uniopet.fsw1.payload;


import br.edu.uniopet.fsw1.security.UserPrincipal;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor

//Resposta do Back para o Front
public class JwtAuthenticationResponse {
    @NonNull
    private String accessToken;

    private String tokenType = "Bearer";

    @NonNull
    private UserPrincipal principal;
}
