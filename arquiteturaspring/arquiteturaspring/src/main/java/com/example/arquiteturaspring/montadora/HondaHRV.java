package com.example.arquiteturaspring.montadora;

public class HondaHRV extends Carro {
    
    public HondaHRV(Motor motor) {
        super(motor);
        setModelo("HRV");
        setCor(Color.BLACK);
        setMontadora(Montadora.HONDA);
    }
}
