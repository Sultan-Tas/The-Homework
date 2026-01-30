package edu.narxoz.galactic.dispatcher;

public record Result(boolean ok, String result) {
    @Override
    public String toString() {
        return ok ? "\tSuccess!" : ("\tFAIL, reason: " + result);
    }
}
