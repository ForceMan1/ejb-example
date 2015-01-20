package igor;
import javax.inject.*;

import java.lang.annotation.*;


@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
public @interface Em {}
