package vttp2023.batch3.ssf.frontcontroller.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationRepository {

	// // TODO Task 5
	// // Use this class to implement CRUD operations on Redis
 
    // @Autowired
    // private RedisTemplate<String, Object> template;

    // public void save(Order order){

    //     //opsForValue = to .set/.get value to/fro database 
    //     template.opsForValue().set(order.getOrderId(), order.toJson().toString());
        
    // }

    // public Order getOrder(String orderId){
    //     String json = (String) template.opsForValue().get(orderId);
    //     return Order.create(json);
    // }
}
