package fpt.intern.fa_api.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TemporaryCodeService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public String generateAndSaveTemporaryCode(String code) {
       
        redisTemplate.opsForValue().set(code, code, 5, TimeUnit.MINUTES);
        return code;
    }
    public boolean isCodeValid(String code) {
        return redisTemplate.hasKey(code);
    }


    public void removeCode(String code) {
        redisTemplate.delete(code);
    }
}
