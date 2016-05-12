package org.myrest.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ParticleVariableResponse {
  private String name;
  private double result;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getResult() {
    return result;
  }

  public void setResult(double result) {
    this.result = result;
  }
}
