package leonardo.ezio.personal.security.service;

import leonardo.ezio.personal.security.domain.DbUserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description : 基于数据库的用户实现 ，需注入用户实现类
 * @Author : LeonardoEzio
 * @Date: 2022-09-01 12:16
 */
@Service
public class DbUserDetailService implements UserDetailsService {

    private static final Map<String, DbUserInfo> USER_INFO_MAP = new HashMap<String, DbUserInfo>() {
        {
            DbUserInfo user1 = new DbUserInfo();
            user1.setUserName("user1");
            user1.setPassWord("$2a$10$8EHvfEoK1a6yDM1ighbCkufgZsGHjJJMQhMBHWzY34f02YwVviqWC");
            put(user1.getUsername(), user1);
            DbUserInfo user2 = new DbUserInfo();
            user2.setUserName("user2");
            user2.setPassWord("$2a$10$dZx6gMf4VGQHuih58JnKH.IZfI5Sy83u5TYXZbsrWmBsRxrqKSmZy");
            put(user2.getUsername(), user2);
        }
    };

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return USER_INFO_MAP.get(s);
    }
}
