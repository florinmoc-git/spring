package methodsec.methodSec.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthorizeConditionEvaluator {

    public boolean checkName(String param){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return param.equals(auth.getName());
    }
}
