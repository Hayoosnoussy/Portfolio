package websocket.data;

import java.security.Principal;

/**
 * Created by kubanek.peter on 29. 3. 2022 for project AsynPoC.
 */
public class User implements Principal {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
