package com.trafigura.equity.position;

import com.trafigura.equity.position.service.impl.TransactionsServicesImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

/**
 * @ClassName JedisTest
 * @Description TODO
 * @Author cheng
 **/
public class JedisTest {

    @Test
    public void test(){
        Jedis jedis = new Jedis("192.168.42.103",6379);
        jedis.auth("1234");
//        jedis.set("aa","aa");
        jedis.hset("INF1","INSERT","1");
    }

    @Test
    public void patternTest(){
        Assert.assertTrue(TransactionsServicesImpl.isNumber("50"));
        Assert.assertTrue(TransactionsServicesImpl.isNumber("-20"));
        Assert.assertFalse(TransactionsServicesImpl.isNumber("aa"));
    }
}
