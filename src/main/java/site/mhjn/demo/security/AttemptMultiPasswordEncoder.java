package site.mhjn.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class AttemptMultiPasswordEncoder implements PasswordEncoder {
    private DelegatingPasswordEncoder delegatingPasswordEncoder;

    public AttemptMultiPasswordEncoder() {
        String idForEncode = "bcrypt";
        Map<String, PasswordEncoder> maps = new HashMap<>();
        maps.put(idForEncode, new BCryptPasswordEncoder());
        maps.put("noop", new DirectPasswordEncoder());
        delegatingPasswordEncoder = new DelegatingPasswordEncoder(idForEncode, maps);
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(maps.get(idForEncode));
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return "";
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }
}
