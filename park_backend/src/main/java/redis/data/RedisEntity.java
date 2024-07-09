//package redis.data;
//
//import java.io.Serializable;
//
//import java.util.Objects;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import lombok.Data;
//@JsonIgnoreProperties(ignoreUnknown = true)
//@Data
//public class RedisEntity implements Serializable {
//	
//    private String key;
//    private String value;
//    
//    
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof RedisEntity)) return false;
//        RedisEntity that = (RedisEntity) o;
//        return Objects.equals(getKey(), that.getKey());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getKey());
//    }
//
//    @Override
//    public String toString() {
//        return "RedisEntity{" +
//                "key='" + key + '\'' +
//                ", value='" + value + '\'' +
//                '}';
//    }
//}
