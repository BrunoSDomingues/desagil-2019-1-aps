package br.pro.hashi.ensino.desagil.aps.model;

public class HalfAdder extends Gate {
    private final NandGate nandLeft;
    private final NandGate nandTop;
    private final NandGate nandBottom;

    private final NandGate sum;
    private final NandGate carry;

    public HalfAdder() {
        super("Half-Adder", 2, 2);

        nandLeft = new NandGate();

        nandTop = new NandGate();
        nandTop.connect(0, nandLeft);

        nandBottom = new NandGate();
        nandBottom.connect(1, nandLeft);

        carry = new NandGate();
        carry.connect(0, nandLeft);
        carry.connect(1, nandLeft);

        sum = new NandGate();
        sum.connect(0, nandBottom);
        sum.connect(1, nandTop);
    }


    @Override
    public boolean read(int outputPin) {
        if (outputPin == 0) {
            return sum.read();
        } else if (outputPin == 1) {
            return carry.read();
        } else {
            throw new IndexOutOfBoundsException(outputPin);
        }
    }

    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandLeft.connect(0, emitter);
                nandBottom.connect(0, emitter);
                break;
            case 1:
                nandLeft.connect(1, emitter);
                nandTop.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}
