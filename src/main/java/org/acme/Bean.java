package org.acme;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Bean {
  private Long value;

  public Long getValue() {
    return value;
  }

  public void setValue(Long value) {
    this.value = value;
  }

  public Bean(Long value) {
    this.value = value;
  }
}
