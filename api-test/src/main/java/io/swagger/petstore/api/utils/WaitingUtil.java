package io.swagger.petstore.api.utils;

import lombok.experimental.UtilityClass;
import org.awaitility.Awaitility;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@UtilityClass
public class WaitingUtil {

  public void waitFor(int time, TimeUnit timeUnit) {
    waitUntil(time, timeUnit, () -> true);
  }

  public void waitUntil(int time, TimeUnit timeUnit, Callable<Boolean> condition) {
    Awaitility.waitAtMost(time, timeUnit)
        .pollInSameThread()
        .pollInterval(time / 10, timeUnit)
        .until(condition);
  }
}
