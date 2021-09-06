package ru.stqa.training.selenium.tests;


import org.testng.annotations.BeforeTest;
import ru.stqa.training.selenium.app.Application;

public class TestBase {

  public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
  public Application app;

  @BeforeTest
  public void start() {
    if (tlApp.get() != null) {
      app = tlApp.get();
      return;
    }

    app = new Application();
    tlApp.set(app);

    Runtime.getRuntime().addShutdownHook(
        new Thread(() -> {
          app.stop();
          app = null;
        }));
  }
}
