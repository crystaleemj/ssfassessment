package vttp2023.batch3.ssf.frontcontroller.respositories;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationRepository {

	// TODO Task 5
	// Use this class to implement CRUD operations on Redis
 
    @Autowired
    private RedisTemplate<String, Object> template;

    public void save(String Login){

        //opsForValue = to .set/.get value to/fro database 
        template.opsForValue().set(Login, "user is disabled for 30 min", Duration.ofMinutes(30));
        
    }

    public boolean isUserDisabled(String user){

        String loginuser = (String) template.opsForValue().get(user);
       if (loginuser != null){
        return true;
       } else {
        return false;
       }
    }
}
