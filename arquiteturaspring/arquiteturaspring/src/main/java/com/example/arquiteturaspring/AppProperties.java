package com.example.arquiteturaspring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.config")
public class AppProperties {
    private String variaval;
    private Integer valor1;

    public String getVariaval() {
        return variaval;
    }

    public void setVariaval(String variaval) {
        this.variaval = variaval;
    }

    public Integer getValor1() {
        return valor1;
    }

    public void setValor1(Integer valor1) {
        this.valor1 = valor1;
    }
}
