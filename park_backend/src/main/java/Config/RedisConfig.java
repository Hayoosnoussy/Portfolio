package Config;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;

@Configuration
@PropertySource("classpath:application.properties")
public class RedisConfig {
   @Autowired
  private Environment env;	
	
   @Bean
   public JedisConnectionFactory redisConnectionFactory() {
	RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
	redisConf.setHostName(env.getProperty("spring.redis.host"));
	redisConf.setPort(Integer.parseInt(env.getProperty("spring.redis.port")));
	redisConf.setPassword(RedisPassword.of(env.getProperty("spring.redis.password")));	    
        return new JedisConnectionFactory(redisConf);
  }
   @Bean
   public RedisCacheConfiguration cacheConfiguration() {
       ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

      Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
       serializer.setObjectMapper(objectMapper);

       RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
              .entryTtl(Duration.ofSeconds(900))
               .disableCachingNullValues()
              .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));

       return cacheConfig;
   }

   @Bean
   public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
       RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
               .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

      return RedisCacheManager.builder(redisConnectionFactory)
               .cacheDefaults(cacheConfig)
              .transactionAware()
               .build();
   }

} 

