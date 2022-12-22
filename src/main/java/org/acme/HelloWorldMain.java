package org.acme;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class HelloWorldMain implements QuarkusApplication {
  public static void main(String... args) throws Exception {
    Quarkus.run(HelloWorldMain.class, args);
  }

  @Override
  public int run(String... args) throws Exception {
    Class<?> beanClass = Bean.class;
    Object beanObject = createBeanInstance(beanClass);

    Method method = beanClass.getMethod("getValue");
    MethodHandle mh = MethodHandles.publicLookup().unreflect(method);

    // Not related to "unreflect" specifically, since this has the same result:
    // MethodHandle mh =
    //    MethodHandles.publicLookup()
    //        .findVirtual(beanClass, "getValue", MethodType.methodType(Long.class));

    // Call to MH returns 0 instead of null
    Long out = callMethodHandle(mh, beanObject);

    System.out.print(">>>>>");
    System.out.print(out);
    System.out.println("<<<<<");
    return 0;
  }

  private static Object createBeanInstance(Class<?> beanClass) throws ReflectiveOperationException {
    return beanClass.getConstructor(Long.class).newInstance((Long) null);
  }

  private static Long callMethodHandle(MethodHandle mh, Object obj) {
    try {
      return (Long) mh.invoke(obj);
    } catch (Throwable throwable) {
      throw new RuntimeException("", throwable);
    }
  }
}
