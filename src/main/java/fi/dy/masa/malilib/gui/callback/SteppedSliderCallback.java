package fi.dy.masa.malilib.gui.callback;

import fi.dy.masa.malilib.gui.callback.SliderCallback;

public interface SteppedSliderCallback extends SliderCallback
{
    /**
     * Returns the step size the underlying config value should snap to
     * @return
     */
    double getStepSize();

    /**
     * Sets the step size the underlying config value should snap to
     * @param step
     */
    void setStepSize(double step);
}
