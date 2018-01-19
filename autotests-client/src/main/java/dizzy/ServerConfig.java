package dizzy;

/**
 * Created by dizzy on 19.01.18.
 */

import org.aeonbits.owner.Config;

@Config.Sources ({"classpath:ServerConfig.properties"})


public interface ServerConfig extends Config {
    @DefaultValue("login")
    String vkLogin();

    @DefaultValue("password")
    String vkPassword();

    @DefaultValue("username")
    String sendToUser();
}
