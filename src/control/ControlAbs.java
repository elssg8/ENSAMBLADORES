package control;

public abstract class ControlAbs implements Control {

    protected Control padre;

    @Override
    public void setCP(Control c) {
        this.padre = c;

    }

}
