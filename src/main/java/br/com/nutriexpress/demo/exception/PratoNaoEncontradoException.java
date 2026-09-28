package br.com.nutriexpress.demo.exception;

public class PratoNaoEncontradoException extends RuntimeException {
    public PratoNaoEncontradoException(String message) {
        super(message);
    }
}
