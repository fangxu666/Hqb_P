package com.bestnet.hf.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 说明：redis工具类
 *
 * 作者：胡志刚
 *
 * 时间：2019-03-22
 *
 * */
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String,Object> redisTemplate ;

    //制定缓存失效时间
    public boolean expire(String key ,long time){
        try{
            if(time>0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //根据key获取失效时间
    public long getExpire(String key){
        return redisTemplate.getExpire(key);
    }

    //判断key是否存在
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //删除缓存 - 可以传一个值 或多个
    @SuppressWarnings("unchecked")
    public void del(String ... key){
        if(key!=null&&key.length>0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key,Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public Object get(String key){
        return key==null? null:redisTemplate.opsForValue().get(key);
    }

    /**
     * 模糊查询key
     * @Title: keys
     * @param @param key
     * @return Set<String> key的集合
     */
    public Set<String> keys(String key) {
        return redisTemplate.keys(key);
    }

    /**
     * 普通缓存多个获取
     * @Title: mGet
     * @param key  String类型，以","号分割的多个键
     * @return Object   值
     */
    public Object mGet(String key){
        if (null == key || key.isEmpty()) {
            return null;
        }
        String[] keys = key.split(",");
        return mGet(keys);
    }

    /**
     * 普通缓存多个获取
     * @Title: mGet
     * @param keys  String数组类型的多个键
     * @return Object   值
     */
    public Object mGet(String[] keys){
        if (null == keys || 0 == keys.length) {
            return null;
        }
        return mGet(Arrays.asList(keys));
    }

    /**
     * 普通缓存多个获取
     * @Title: mGet
     * @param keys  List类型的多个键
     * @return Object   值
     */
    public Object mGet(List<String> keys){
        if (null == keys || 0 == keys.size()) {
            return null;
        }
        return redisTemplate.opsForValue().multiGet(keys);
    }


    public static void main(String[] args) {
        RedisUtil redisUntil = new RedisUtil();
        String a = (String)redisUntil.get("oh-Ub6K23k-dfjEZGRbcqJKZr9vw");
        System.out.println(a);
    }
}
