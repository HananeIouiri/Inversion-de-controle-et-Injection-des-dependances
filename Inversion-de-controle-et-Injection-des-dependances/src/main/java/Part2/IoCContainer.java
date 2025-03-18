package Part2;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD})
@interface Autowired {
}

public class IoCContainer {
    private Map<String, Object> beans = new HashMap<>();

    public void registerBean(String beanName, Object bean) {
        beans.put(beanName, bean);
    }

    public Object getBean(String beanName) {
        return beans.get(beanName);
    }

    public void doConstructorInjection() {
        for (Object bean : beans.values()) {
            Class<?> beanClass = bean.getClass();
            for (Constructor<?> constructor : beanClass.getConstructors()) {
                if (constructor.isAnnotationPresent(Autowired.class)) {
                    Class<?>[] parameterTypes = constructor.getParameterTypes();
                    Object[] parameters = new Object[parameterTypes.length];
                    for (int i = 0; i < parameterTypes.length; i++) {
                        parameters[i] = beans.get(parameterTypes[i].getSimpleName());
                    }
                    try {
                        constructor.newInstance(parameters);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void doSetterInjection() {
        for (Object bean : beans.values()) {
            Class<?> beanClass = bean.getClass();
            for (Method method : beanClass.getMethods()) {
                if (method.getName().startsWith("set") && method.isAnnotationPresent(Autowired.class)) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length != 1) {
                        continue; // Ignorer les méthodes setter avec plus d'un paramètre
                    }
                    Object dependency = beans.get(parameterTypes[0].getSimpleName());
                    try {
                        method.invoke(bean, dependency);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void doFieldInjection() {
        for (Object bean : beans.values()) {
            Class<?> beanClass = bean.getClass();
            for (Field field : beanClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object dependency = beans.get(field.getType().getSimpleName());
                    field.setAccessible(true);
                    try {
                        field.set(bean, dependency);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}