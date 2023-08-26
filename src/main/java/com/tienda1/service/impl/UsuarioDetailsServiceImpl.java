package com.tienda1.service.impl;

import com.tienda1.dao.UsuarioDao;
import com.tienda1.domain.Rol;
import com.tienda1.domain.Usuario;
import com.tienda1.service.UsuarioDetailsService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("usernameDetailsService")
public class UsuarioDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private HttpSession session;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        //si se ejecuta lo siguiente es porque SI existe el usuario con ese usuario

        session.removeAttribute("imagenUsuario");
        session.setAttribute("imagenUsuario", usuario.getRuta_imagen());

        //se recuperan los roles que tiene el usuario
        var roles = new ArrayList<GrantedAuthority>();
        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        // se retorna un user con la info para el sistema de seguridad spring

        return new User(usuario.getUsername(), usuario.getPassword(), roles);

    }

}
