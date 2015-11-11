package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * controller需要登录 unless可以填写例外的action方法名称
 * 
 * @author zp
 * 
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Login {
	/**
	 * 例外
	 * 
	 * @return
	 */
	String[] unless() default {};
}
