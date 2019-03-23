package br.edu.uniopet.fsw1.security;


import br.edu.uniopet.fsw1.model.User;
import br.edu.uniopet.fsw1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//Vai checar se o usuário existe no banco de dados
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
     //Buscar usuario por nome ou email
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    //Buscar o
    public UserDetails loadUserById(Long idUser) {
        User user = userRepository.findById(idUser).orElseThrow(
            () -> new UsernameNotFoundException("User not found with username or email : " + idUser)
        );

        return UserPrincipal.create(user);
    }
}