import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.main.LaunchResult;
import io.quarkus.test.junit.main.QuarkusMainLauncher;
import io.quarkus.test.junit.main.QuarkusMainTest;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusMainTest
public class HelloTest {

  @Test
  public void testManualLaunch(QuarkusMainLauncher launcher) {
    LaunchResult result = launcher.launch();
    assertThat(result.exitCode()).isEqualTo(0);
    assertThat(result.getOutput()).contains(">>>>>null<<<<<");
  }
}
