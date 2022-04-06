package br.com.ifrndsc.CRUDCliente.service;

import br.com.ifrndsc.CRUDCliente.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(usuarioRepository.findByUsername(username) != null){
            return usuarioRepository.findByUsername(username);
        }else{
            return (UserDetails) new UsernameNotFoundException("Usuário não encontrado");
        }
    }
}



