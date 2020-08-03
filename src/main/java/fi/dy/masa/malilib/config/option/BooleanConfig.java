package fi.dy.masa.malilib.config.option;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import fi.dy.masa.malilib.LiteModMaLiLib;
import fi.dy.masa.malilib.config.ConfigType;

public class BooleanConfig extends BaseConfig<Boolean>
{
    protected final boolean defaultValue;
    protected boolean value;
    protected boolean lastSavedValue;

    public BooleanConfig(String name, boolean defaultValue, String comment)
    {
        this(name, defaultValue, comment, name);
    }

    public BooleanConfig(String name, boolean defaultValue, String comment, String prettyName)
    {
        super(ConfigType.BOOLEAN, name, comment, prettyName);

        this.defaultValue = defaultValue;
        this.value = defaultValue;

        this.cacheSavedValue();
    }

    public boolean getBooleanValue()
    {
        return this.value;
    }

    public boolean getDefaultBooleanValue()
    {
        return this.defaultValue;
    }

    public void setBooleanValue(boolean value)
    {
        boolean oldValue = this.value;
        this.value = value;

        if (oldValue != this.value)
        {
            this.onValueChanged(value, oldValue);
        }
    }

    public void toggleBooleanValue()
    {
        this.setBooleanValue(! this.getBooleanValue());
    }

    @Override
    public boolean isModified()
    {
        return this.value != this.defaultValue;
    }

    @Override
    public boolean isDirty()
    {
        return this.lastSavedValue != this.value;
    }

    @Override
    public void cacheSavedValue()
    {
        this.lastSavedValue = this.value;
    }

    @Override
    public void resetToDefault()
    {
        this.setBooleanValue(this.defaultValue);
    }

    @Override
    public void setValueFromJsonElement(JsonElement element, String configName)
    {
        try
        {
            if (element.isJsonPrimitive())
            {
                this.value = element.getAsBoolean();
                this.onValueLoaded(this.value);
            }
            else
            {
                LiteModMaLiLib.logger.warn("Failed to set config value for '{}' from the JSON element '{}'", configName, element);
            }
        }
        catch (Exception e)
        {
            LiteModMaLiLib.logger.warn("Failed to set config value for '{}' from the JSON element '{}'", configName, element, e);
        }

        this.cacheSavedValue();
    }

    @Override
    public JsonElement getAsJsonElement()
    {
        return new JsonPrimitive(this.value);
    }
}