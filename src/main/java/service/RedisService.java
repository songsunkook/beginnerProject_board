package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisService {

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOps;

    public String setStr(String key, String value) {
        valueOps.set(key, value);
        return valueOps.get(key);
    }

    public String getStr(String key) {
        String result = valueOps.get(key);
        return result;
    }
}