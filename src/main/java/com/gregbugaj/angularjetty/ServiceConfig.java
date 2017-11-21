package com.gregbugaj.angularjetty;

/**
 * Configuration options
 */
public class ServiceConfig
{
    private Integer servicePortNumber = 80;

    private String bindingInterface = "0.0.0.0";

    /**
     * @return the servicePortNumber
     */
    public Integer getServicePortNumber()
    {
        return servicePortNumber;
    }

    /**
     * @param servicePortNumber the servicePortNumber to set
     */
    public void setServicePortNumber(final Integer servicePortNumber)
    {
        this.servicePortNumber = servicePortNumber;
    }

    /**
     * @return the bindingInterface
     */
    public String getBindingInterface()
    {
        return bindingInterface;
    }

    /**
     * @param bindingInterface the bindingInterface to set
     */
    public void setBindingInterface(final String bindingInterface)
    {
        this.bindingInterface = bindingInterface;
    }
}
