package d2.moneylover.service;

import d2.moneylover.dto.RegisterResquest;
import d2.moneylover.model.entity.User;

public interface UserService {
    User registerUser(RegisterResquest registerResquest);


    User findByName(String name);
/*
UserDetails loadUserByUsername(String name);
*/

}
