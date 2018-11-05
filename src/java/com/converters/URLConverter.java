/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.converters;

import java.net.URL;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "urlConverter")
public class URLConverter implements Converter {
    // Presentation -> Model
    @Override
    public Object getAsObject(FacesContext context,
            UIComponent component, String newValue) throws ConverterException {
        try {
            return new URL(newValue);
        } catch (Exception e) {
            throw new ConverterException("Hey, conversion error!");
        }
    }
// Model -> Presentation
    @Override
    public String getAsString(FacesContext context,
            UIComponent component, Object value) throws ConverterException {
        return String.valueOf(value);
    }
}
