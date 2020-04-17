package nightfury;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="converter")
public class CustomConverter implements Converter{
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
		if (modelValue == null) {
	        return "";
	    }

	    if (modelValue instanceof Items) {
	        return String.valueOf(((Items) modelValue).getResponValue());
	    } else {
	        throw new ConverterException(new FacesMessage(modelValue + " is not a valid Warehouse"));
	    }
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		if (submittedValue == null || submittedValue.isEmpty()) {
	        return null;
	    }

	    try {
	    	return null; //to be change
	    } catch (NumberFormatException e) {
	        throw new ConverterException(new FacesMessage(submittedValue + " is not a valid Warehouse ID"), e);
	    }
	}
}
