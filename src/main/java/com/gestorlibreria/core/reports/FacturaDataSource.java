package com.gestorlibreria.core.reports;

import com.gestorlibreria.core.beans.Root;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.ArrayList;

public class FacturaDataSource implements JRDataSource{

    private ArrayList<Root> lista = null;
    private int index = -1;

    public FacturaDataSource(ArrayList<Root> lista) {
        this.lista = lista;
    }

    @Override
    public boolean next() throws JRException {
        return ++index < lista.size();
    }

    @Override
    public Object getFieldValue(JRField campo) throws JRException {
        Object resultado = "";
        if("fecha".equals(campo.getName())){
            resultado = this.lista.get(index).getFecha();
        }else if("totalAPagar".equals((campo.getName()))){
            resultado = this.lista.get(index).getTotalAPagar();
        }
        return resultado;
    }
}
