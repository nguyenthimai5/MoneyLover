package d2.moneylover.service.serviceimp;

import d2.moneylover.dto.RegisterResquest;
import d2.moneylover.model.entity.User;
import d2.moneylover.repository.UserRepository;
import d2.moneylover.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    public final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User registerUser(RegisterResquest registerResquest) {
        if (userRepository.findByName(registerResquest.getName()) != null) {
            throw new IllegalArgumentException("Tên người dùng đã tồn tại");
        }
        User user = new User();
        user.setName(registerResquest.getName());
        user.setPassword(passwordEncoder.encode(registerResquest.getPassword()));
        user.setFirstName(registerResquest.getFirstName());
        user.setLastName(registerResquest.getLastName());
        user.setEmail(registerResquest.getEmail());
        user.setBirthday(registerResquest.getBirthday());
        user.setCreateDate(new Date());
        user.setStatus(true);
        user.setRole("user");
        return userRepository.save(user);
    }


    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

/*    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                getAuthorities(user.getRole())
        );
    }*/

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }
}

