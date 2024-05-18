package modelsform;

import exceptions.IncorrectInput;
import exceptions.InvalidForm;

public abstract class Form<F> {
    public abstract F build() throws IncorrectInput, InvalidForm;
}
